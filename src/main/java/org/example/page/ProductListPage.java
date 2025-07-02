package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;
import java.util.stream.Collectors;


public class ProductListPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-Cart']")
    private WebElement cartButton;
    @FindBy(how = How.XPATH, using = "//android.view.ViewGroup[@content-desc='test-Modal Selector Button']")
    private WebElement filterButton;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Price (low to high)']")
    private WebElement sortByPriceLowToHigh;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Name (A to Z)']")
    private WebElement sortByAlphabetically;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='Name (Z to A)']")
    private WebElement sortByReversOrder;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='test-Item title']")
    private List<WebElement> productList;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCartButton;
    @FindBy(how = How.XPATH, using = "//android.widget.TextView[@content-desc='test-Price']")
    private List<WebElement> itemPrice;

    public ProductListPage(AndroidDriver driver) {
        super(driver);
    }

    public ProductListPage clickOnItemByText(String text) {
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

    public ProductListPage clickFilterButton() {
        filterButton.click();

        return this;
    }

    public ProductListPage clickPriceFromLowToHigh() {
        sortByPriceLowToHigh.click();

        return this;
    }

    public ProductListPage clickAlphabetically() {
        sortByAlphabetically.click();

        return this;
    }

    public ProductListPage clickReverseOrder() {
        sortByReversOrder.click();

        return this;
    }

    public List<String> getAllPrices() {
        return itemPrice.stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAllNames() {
        return productList.stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
