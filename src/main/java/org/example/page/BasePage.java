package org.example.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

import static com.codeborne.selenide.Condition.visible;

public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    protected void click(SelenideElement selenideElement) {
        selenideElement.shouldBe(visible).click();
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementVisible(WebElement element) {
        return this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement webElement) {
        webElement.click();
    }

    public void swipeDown(AndroidDriver driver) {
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;

        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    protected void swipeAndClick(WebElement webElement) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                waitForElementVisible(webElement).click();
                return;
            } catch (TimeoutException | org.openqa.selenium.ElementClickInterceptedException e) {
                swipeDown(driver);
                attempts++;
            }
        }
        throw new RuntimeException("Cannot click on element");
    }

    protected void type(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected int getElementIndex(ElementsCollection elements, String text) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getText().equals(text)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Nie znaleziono elementu o tekście: " + text);
    }
}
