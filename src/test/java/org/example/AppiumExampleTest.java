package org.example;

import org.example.model.Item;
import org.example.page.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.helper.StringHelper.removeDollarSignFromText;

public class AppiumExampleTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    public void initPages() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void tc_001() {
        String itemName = "Sauce Labs Backpack";

        Item item = Item.builder()
                .itemName(itemName).build();

        ProductListPage productListPage = loginPage.loginAsDefaultUser();
        double itemPrice = productListPage
                .getItemPrice(itemName);

        item.setPrice(itemPrice);
        BasketPage basketPage = productListPage
                .clickOnItemByText(itemName)
                .clickCartButton();
        List<String> itemNames = basketPage.getItemsInBasket();
        List<String> price = basketPage.getItemsPriceInBasket();
        double priceInBasket = Double.parseDouble(
                removeDollarSignFromText(price.get(0)));

        Assertions.assertEquals(item.getItemName(), itemNames.get(0), "Incorrect item name");
        Assertions.assertEquals(item.getPrice(), priceInBasket, "Incorrect price value");
    }

    @Test
    public void tc_002() {
        String itemName = "Sauce Labs Backpack";

        BasketSummaryPage basketSummaryPage = loginPage.loginAsDefaultUser()
                .clickOnItemByText(itemName)
                .clickCartButton()
                .clickCheckoutButton()
                .typeFirstName("Adam")
                .typeLastName("Nowak")
                .typeZipCode("37-210")
                .clickContinueButton();

        List<String> itemNamesFromBasket = basketSummaryPage.getItemsInBasket();
        List<String> price = basketSummaryPage.getItemsPriceInBasket();
        double priceInBasket = Double.parseDouble(
                removeDollarSignFromText(price.get(0)));

        Assertions.assertEquals(itemName, itemNamesFromBasket.get(0));
        Assertions.assertEquals(29.99, priceInBasket);
    }

    @Test
    public void tc_0012() {
        String itemName = "Sauce Labs Backpack";

        OrderConfirmationPage orderConfirmationPage = loginPage.loginAsDefaultUser()
                .clickOnItemByText(itemName)
                .clickCartButton()
                .clickCheckoutButton()
                .typeFirstName("Adam")
                .typeLastName("Nowak")
                .typeZipCode("37-210")
                .clickContinueButton()
                .clickFinishButton();

        assertThat(orderConfirmationPage.isTitleDisplayed())
                .as("Title is not visible")
                .isTrue();
        assertThat(orderConfirmationPage.isSuccessfulInformationDisplayed())
                .as("Successful order information is not visible")
                .isTrue();
    }

    @Test
    public void tc_003() {
        List<String> listOfAllPrices = loginPage.loginAsDefaultUser()
                .clickFilterButton()
                .clickPriceFromLowToHigh()
                .getAllPrices();

        listOfAllPrices.replaceAll(s -> removeDollarSignFromText(s));

        assertThat(listOfAllPrices)
                .as("Prices are not sorted from lowest to highest")
                .isSorted();
        assertThat(listOfAllPrices.size())
                .as("There is only one item on the page, cannot check sorting function")
                .isGreaterThan(1);
    }

    @Test
    public void tc_004() {
        List<String> listOfAllNames = loginPage.loginAsDefaultUser()
                .clickFilterButton()
                .clickAlphabetically()
                .getAllNames();

        assertThat(listOfAllNames)
                .as("Item names are not sorted alphabetically")
                .isSorted();
        assertThat(listOfAllNames.size())
                .as("There is only one item on the page, cannot check sorting function")
                .isGreaterThan(1);
    }

    @Test
    public void tc_005() {
        String itemName = "Sauce Labs Backpack";

        List<String> itemsInBasket = loginPage.loginAsDefaultUser()
                .clickOnItemByText(itemName)
                .clickCartButton()
                .clickRemoveItemFromBasketButton()
                .getItemsInBasket();

        assertThat(itemsInBasket.size())
                .as("Looks like items are still in the basket")
                .isEqualTo(0);
    }
}
