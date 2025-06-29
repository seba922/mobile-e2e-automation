package org.example.page;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    private final By productList = By.xpath("//android.widget.TextView[@content-desc='test-Item title']");
    private final By addToCartButtonList = By.xpath("//android.widget.TextView[@text='ADD TO CART']");

    public ProductPage(AndroidDriver driver) {
        super(driver);
    }
}
