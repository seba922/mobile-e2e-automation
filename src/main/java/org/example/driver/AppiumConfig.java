package org.example.driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumConfig {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() throws MalformedURLException {
        if (driver == null) {
            File file = new File("src/main/resources/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("emulator-5554");
            options.setPlatformName("Android");
            options.setApp(file.getAbsolutePath());
            options.setNoReset(true);

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
