package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;


public class ProductPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-Cart']")
    private WebElement cartButton;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='test-Item title']")
    private List<WebElement> productList;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCartButton;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='test-Price']")
    private List<WebElement> itemPrice;

    public ProductPage(AndroidDriver driver) {
        super(driver);
    }

    public ProductPage clickOnItemByText(String text) {
        int elementIndex = getElementIndex(productList, text);
        click(addToCartButton.get(elementIndex));

        return this;
    }

    public double getItemPrice(String itemName) {
        int elementIndex = getElementIndex(productList, itemName);

        return Double.parseDouble(itemPrice.get(elementIndex).getText().replace("$", ""));
    }

    public BasketPage clickCartButton() {
        cartButton.click();

        return new BasketPage(driver);
    }
}
