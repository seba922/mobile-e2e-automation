package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class BasketPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]")
    private List<WebElement> itemName;
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView[1]")
    private List<WebElement> price;
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-CHECKOUT']")
    private WebElement checkoutButton;
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-REMOVE']")
    private WebElement removeItemFromBasket;

    public BasketPage(AndroidDriver driver) {
        super(driver);
    }

    public List<String> getItemsInBasket() {
        return itemName.stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }

    public List<String> getItemsPriceInBasket() {
        return price.stream()
                .map(webElement -> webElement.getText())
                .collect(Collectors.toList());
    }

    public CheckoutPage clickCheckoutButton() {
        checkoutButton.click();

        return new CheckoutPage(driver);
    }

    public BasketPage clickRemoveItemFromBasketButton() {
        removeItemFromBasket.click();

        return this;
    }
}
