package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.example.helper.StringHelper.removeDollarSignFromText;

public class ProductListPage extends BasePage {

    private final SelenideElement cartButton = $x("//android.view.ViewGroup[@content-desc='test-Cart']");
    private final SelenideElement filterButton = $x("//android.view.ViewGroup[@content-desc='test-Modal Selector Button']");
    private final SelenideElement sortByPriceLowToHigh = $x("//android.widget.TextView[@text='Price (low to high)']");
    private final SelenideElement sortByAlphabetically = $x("//android.widget.TextView[@text='Name (A to Z)']");
    private final ElementsCollection productList = $$x("//android.widget.TextView[@content-desc='test-Item title']");
    private final ElementsCollection addToCartButton = $$x("//android.widget.TextView[@text='ADD TO CART']");
    private final ElementsCollection itemPrice = $$x("//android.widget.TextView[@content-desc='test-Price']");

    public ProductListPage clickOnItemByText(String text) {
        int elementIndex = getElementIndex(productList, text);
        click(addToCartButton.get(elementIndex));
        return this;
    }

    public double getItemPrice(String itemName) {
        int elementIndex = getElementIndex(productList, itemName);
        return Double.parseDouble(removeDollarSignFromText(
                itemPrice.get(elementIndex).getText().replace("$", "")));
    }

    public ProductListPage clickFilterButton() {
        click(filterButton);
        return this;
    }

    public ProductListPage clickPriceFromLowToHigh() {
        click(sortByPriceLowToHigh);
        return this;
    }

    public ProductListPage clickAlphabetically() {
        click(sortByAlphabetically);
        return this;
    }

    public List<String> getAllPrices() {
        return itemPrice.texts();
    }

    public List<String> getAllNames() {
        return productList.texts();
    }
}
