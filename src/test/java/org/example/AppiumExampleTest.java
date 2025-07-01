package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.example.driver.AppiumConfig;
import org.example.model.Item;
import org.example.page.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppiumExampleTest {

    private static AndroidDriver driver;
    private LoginPage loginPage = new LoginPage(driver);

    @BeforeAll
    public static void setUp() throws Exception {
        driver = AppiumConfig.getDriver();
    }

    @AfterAll
    public static void tearDown() {
        driver.terminateApp("com.swaglabsmobileapp");
        AppiumConfig.quitDriver();
    }

    @Test
    public void tc_001() {
        String itemName = "Sauce Labs Backpack";

        Item item = Item.builder()
                .itemName(itemName).build();

        ProductPage productPage = loginPage.loginAsDefaultUser();
        double itemPrice = productPage
                .getItemPrice(itemName);

        item.setPrice(itemPrice);
        BasketPage basketPage = productPage
                .clickOnItemByText(itemName)
                .clickCartButton();
        List<String> itemNames = basketPage.getItemsInBasket();
        List<String> price = basketPage.getItemsPriceInBasket();
        double priceInBasket = Double.parseDouble(price.get(0).replace("$", ""));

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
        double priceInBasket = Double.parseDouble(price.get(0).replace("$", ""));

        Assertions.assertEquals(itemName, itemNamesFromBasket.get(0));
        Assertions.assertEquals(29.99, priceInBasket);
    }

}
