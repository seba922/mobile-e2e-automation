package org.example.page;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementVisible(WebElement element) {
        return this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement webElement) {
        waitForElementVisible(webElement).click();
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

    protected void click(By locator) {
        waitForElement(locator).click();
    }

    protected void type(WebElement webElement, String text) {
        webElement.clear();
        webElement.sendKeys(text);
    }

    protected int getElementIndex(List<WebElement> webElementList, String text) {
        for (int i = 0; i < webElementList.size(); i++) {
            if (webElementList.get(i).getText().equals(text)) {
                return i;
            }
        }

        throw new RuntimeException("Cannot find element on list");
    }
}
