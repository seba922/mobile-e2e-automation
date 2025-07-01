package org.example.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;
    public static String appName;
    public static String deviceName;

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            setProperties();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read config file", e);
        }
    }

    private static void setProperties() {
        appName = System.getenv("APP_NAME") != null ?
                System.getenv("APP_NAME") : properties.getProperty("app.name");
        deviceName = System.getenv("DEVICE_NAME") != null ?
                System.getenv("DEVICE_NAME") : properties.getProperty("device.name");
    }
}
