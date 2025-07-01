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

    @DisplayName("Add the product to the shopping cart. Confirm that the added product is displayed")
    @Test
    public void tc_001() {
        String itemName = "Sauce Labs Backpack";

        Item backpackItem = Item.builder()
                .itemName(itemName).build();

        ProductListPage productListPage = loginPage.loginAsDefaultUser();
        double itemPrice = productListPage
                .getItemPrice(itemName);

        backpackItem.setPrice(itemPrice);
        BasketPage basketPage = productListPage
                .clickOnItemByText(itemName)
                .clickCartButton();
        List<String> itemNames = basketPage.getItemsInBasket();
        List<String> price = basketPage.getItemsPriceInBasket();
        double priceInBasket = Double.parseDouble(
                removeDollarSignFromText(price.get(0)));

        Assertions.assertEquals(backpackItem.getItemName(), itemNames.get(0), "Incorrect item name");
        Assertions.assertEquals(backpackItem.getPrice(), priceInBasket, "Incorrect price value");
    }

    @DisplayName("Ensure the Checkout screen displays the correct order details " +
            "Open the shopping cart and continue. Fill in all required information fields " +
            "Navigate to the payment screen. Enter payment information and proceed to order overview ")
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

    @DisplayName("Place the Order and complete the purchase process")
    @Test
    public void tc_003() {
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

    @DisplayName("Filter products by price")
    @Test
    public void tc_004() {
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

    @DisplayName("Validate error messages for empty required fields")
    @Test
    public void tc_005() {
        String itemName = "Sauce Labs Backpack";

        CheckoutPage checkoutPage = loginPage.loginAsDefaultUser()
                .clickOnItemByText(itemName)
                .clickCartButton()
                .clickCheckoutButton();

        checkoutPage.clickContinueButton();

        assertThat(checkoutPage.getValidationMessage())
                .as("Incorrect validation message for name")
                .isEqualTo("First Name is required");
    }

    @DisplayName("Remove items from the cart and verify the update")
    @Test
    public void tc_006() {
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

    @DisplayName("Filter products by name")
    @Test
    public void tc_007() {
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
}
