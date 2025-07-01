package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CheckoutPage extends BasePage{

    @FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='test-First Name']")
    private WebElement firstName;
    @FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='test-Last Name']")
    private WebElement lastName;
    @FindBy(how = How.XPATH, using = "//android.widget.EditText[@content-desc='test-Zip/Postal Code']")
    private WebElement zipCode;
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-CONTINUE']")
    private WebElement continueButton;

    public CheckoutPage(AndroidDriver driver) {
        super(driver);
    }

    public CheckoutPage typeFirstName(String name) {
        type(firstName, name);

        return this;
    }

    public CheckoutPage typeLastName(String lastName) {
        type(this.lastName, lastName);

        return this;
    }

    public CheckoutPage typeZipCode(String zipCode) {
        type(this.zipCode, zipCode);

        return this;
    }

    public BasketSummaryPage clickContinueButton() {
        click(continueButton);

        return new BasketSummaryPage(driver);
    }

}
