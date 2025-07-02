package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Cannot find config file " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load configuration file " + CONFIG_FILE, e);
        }
    }

    private static String getOrEnv(String propertyKey, String envKey) {
        String envValue = System.getenv(envKey);
        return envValue != null ? envValue : properties.getProperty(propertyKey);
    }

    public static String getAppName() {
        return getOrEnv("app.name", "APP_NAME");
    }

    public static String getDeviceName() {
        return getOrEnv("device.name", "DEVICE_NAME");
    }
}
