package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.example.driver.AppiumConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        driver = AppiumConfig.getDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.terminateApp("com.swaglabsmobileapp");
        AppiumConfig.quitDriver();
    }
}
