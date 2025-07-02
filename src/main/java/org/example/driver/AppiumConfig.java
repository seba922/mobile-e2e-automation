package org.example.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.example.config.ConfigReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumConfig {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            File file = new File(String.format("src/main/resources/%s", ConfigReader.getAppName()));

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName(ConfigReader.getDeviceName());
            options.setPlatformName("Android");
            options.setApp(file.getAbsolutePath());
            options.setNoReset(true);
            options.setAppPackage("com.swaglabsmobileapp");
            options.setAppActivity("com.swaglabsmobileapp.MainActivity");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
