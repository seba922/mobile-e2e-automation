package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrderConfirmationPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='CHECKOUT: COMPLETE!']")
    private WebElement title;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")
    private WebElement thankYouForYourOrderLabel;

    public OrderConfirmationPage(AndroidDriver driver) {
        super(driver);
    }

    public boolean isTitleDisplayed() {
        return title.isDisplayed();
    }

    public boolean isSuccessfulInformationDisplayed() {
        return title.isDisplayed();
    }
}
