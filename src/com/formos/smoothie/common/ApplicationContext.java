package com.formos.smoothie.common;

import com.formos.smoothie.common.annotation.Autowired;
import com.formos.smoothie.common.annotation.Component;
import com.formos.smoothie.common.annotation.ComponentScan;
import com.formos.smoothie.common.annotation.Controller;
import com.formos.smoothie.common.annotation.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationContext {

    private final Map<Class<?>, Object> beanRegistryMap = new HashMap<>();

    private final Class<?> clazz;

    public ApplicationContext(Class<?> clazz) {
        this.clazz = clazz;
//        this.beanRegistryMap.put(ApplicationContext.class, this);
        initializeContext();
//        initializeConfig();
    }

    private void initializeContext() {
        ComponentScan componentScan = this.clazz.getAnnotation(ComponentScan.class);
        String packageName = Optional.ofNullable(componentScan.value()).orElse(this.clazz.getCanonicalName());
        doFindAllPackage(packageName);
    }

//    private void initializeConfig() {
//
//    }

    private void doFindAllPackage(String packageName) {

        URI uri;
        URL url;
        Set<Class<?>> packageSet;
        try {
            CodeSource src = ApplicationContext.class.getProtectionDomain().getCodeSource();
            if (src != null) {
                uri = src.getLocation().toURI();
                url = src.getLocation();
                String urlStr = src.getLocation().getPath();
                String decodedPath = URLDecoder.decode(urlStr, "UTF-8");

                if (decodedPath.endsWith(".jar") || uri.getScheme().equals("jar")) {
                    packageSet = doFindPathJarResources(url, packageName);
                } else {
                    packageSet = doFindPathFileResources(packageName);
                }

                doInjectAnnotatedComponent(packageSet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Set<Class<?>> doFindPathJarResources(URL rootDirURL, String packageName) throws IOException {
        JarFile jarFile = null;
        String rootPath = "";
        boolean closeJarFile = false;
        Set<Class<?>> filePathSet = new LinkedHashSet<>();
        String urlFile = rootDirURL.getFile();
        try {
            jarFile = new JarFile(urlFile);
            rootPath = packageName.replaceAll("[.]", "/");

            closeJarFile = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (rootPath.length() > 0 && !rootPath.endsWith("/")) {
                rootPath = rootPath + "/";
            }

            assert jarFile != null;
            Enumeration<JarEntry> entries = jarFile.entries();

            while(entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String entryPath = entry.getName();
                if (entryPath.startsWith(rootPath)) {
                    if (entryPath.endsWith(".class")) {
                        String classname = entryPath
                                .replace(File.separatorChar, '.')
                                .replace('/', '.')
                                .substring(0, entryPath.lastIndexOf('.'));
                        Class<?> clazz = Class.forName(classname);
                        filePathSet.add(clazz);
                    }
                }
            }

            return filePathSet;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (closeJarFile) {
                jarFile.close();
            }

        }
    }

    private Set<Class<?>> doFindPathFileResources(String packageName) throws IOException, URISyntaxException, ClassNotFoundException {
        Set<Class<?>> result = new LinkedHashSet<>();
        Enumeration<URL> resources = ClassLoader
                .getSystemClassLoader()
                .getResources(packageName
                        .replaceAll("[.]", "/"));

        while(resources.hasMoreElements()) {
            URL url = resources.nextElement();
            File rootFile = Paths.get(url.toURI()).toFile();
            doFindClasses(result, packageName, rootFile);
        }

        return result;
    }

    private void doFindClasses(Set<Class<?>> result, String packageName, File rootFile) throws ClassNotFoundException {
        Pattern pattern = Pattern.compile(packageName + ".*");
        String relativePath = "";
        for (File contentFile : Objects.requireNonNull(rootFile.listFiles())) {
            String currentPath = contentFile.getAbsolutePath().replace(File.separatorChar, '.');
            Matcher match = pattern.matcher(currentPath);
            if (match.find()) {
                relativePath = match.group();
            }

            if (contentFile.isDirectory()) {
                String relativePackageName = relativePath + ".*";
                doFindClasses(result, relativePackageName, contentFile);
            } else if (relativePath.endsWith(".class")) {
                String classname = relativePath.substring(0, relativePath.lastIndexOf("."));
                Class<?> clazz = Class.forName(classname);
                result.add(clazz);
            }
        }
    }

    private <T> void doInjectAnnotatedField(T object, Field[] declaredFields) {
        for (Field field: declaredFields) {
            try {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Class<?> type = field.getType();
                    if (type.isInterface()) {
                        type = createInstance(type).getClass();
                    }
                    Object innerObject = beanRegistryMap.get(type);
                    if (innerObject == null) {
                        innerObject = type.getDeclaredConstructor().newInstance();
                    }

                    field.set(object, innerObject);
                    doInjectAnnotatedField(innerObject, type.getDeclaredFields());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getInstance(Class<T> clazz) throws Exception {
        T object = createInstance(clazz);

        return getInstance(object, clazz);
    }

    public <T> T createInstance(Class<T> clazz) throws Exception {
        T object = (T) beanRegistryMap.get(clazz);
        if (object == null) {
            object = clazz.getDeclaredConstructor().newInstance();
            beanRegistryMap.put(clazz, object);
        }

        return object;
    }

    private <T> T getInstance(T object, Class<T> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        doInjectAnnotatedField(object, declaredFields);

        return object;
    }

    private void doInjectAnnotatedComponent(Set<Class<?>> classSet) {
        for (Class<?> loadingClass : classSet) {
            try {
                if(loadingClass.isAnnotationPresent(Component.class) || loadingClass.isAnnotationPresent(Controller.class)) {
                    Constructor<?> constructor = loadingClass.getDeclaredConstructor();
                    Object newInstance = constructor.newInstance();
                    beanRegistryMap.put(loadingClass, newInstance);
                } else if (loadingClass.isAnnotationPresent(Service.class)) {
                    Constructor<?> constructor = loadingClass.getDeclaredConstructor();
                    Class<?>[] interfaceClass = loadingClass.getInterfaces();
                    Object newInstance = constructor.newInstance();
                    beanRegistryMap.put(interfaceClass[0], newInstance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        T t = (T) beanRegistryMap.get(clazz);
        return t;
    }
}
