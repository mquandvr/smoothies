package com.formos.smoothie.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    public static Properties properties = null;

    public static Properties getInstance(String path) {
        if (properties == null) {
            properties = new Properties();
            loadProp(path);
        }
        return properties;
    }

    private static Properties loadProp(String path) {
        try(InputStream input = new FileInputStream(path)) {
            properties.load(input);
            return properties;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
