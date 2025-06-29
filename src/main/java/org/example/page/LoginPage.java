package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='standard_user']")
    private WebElement standardUserButton;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='LOGIN']")
    private WebElement loginButton;

    public LoginPage(AndroidDriver driver) {
        super(driver);
    }

    public ProductPage loginAsDefaultUser() {
        click(standardUserButton);
        click(loginButton);

        return new ProductPage(driver);
    }
}
