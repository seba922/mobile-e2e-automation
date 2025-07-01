package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;

public class BasketSummaryPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[1]")
    private List<WebElement> itemName;
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-Price']/android.widget.TextView[1]")
    private List<WebElement> price;
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-FINISH']")
    private WebElement finishButton;

    public BasketSummaryPage(AndroidDriver driver) {
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

    public OrderConfirmationPage clickFinishButton() {
        swipeAndClick(finishButton);

        return new OrderConfirmationPage(driver);
    }
}
