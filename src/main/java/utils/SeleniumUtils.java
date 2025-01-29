package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SeleniumUtils {

    private static final Logger logger = LoggerFactory.getLogger(SeleniumUtils.class);

    public static WebElement waitAndFindElement(WebDriver driver, By locator, int timeoutInSeconds) {
        try {
            logger.info("Waiting for element located by: {}", locator);
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.not(ExpectedConditions.stalenessOf(driver.findElement(locator))));
            return driver.findElement(locator);
        } catch (Exception e) {
            logger.error("Failed to locate element: {}. Error: {}", locator, e.getMessage());
            throw new RuntimeException("Element not found: " + locator, e);
        }
    }

    public static void waitForSpecificTime() throws InterruptedException {
        Thread.sleep(30000);
    }
    public static List<WebElement> waitAndFindElements(WebDriver driver, By locator, int timeoutInSeconds) {
        try {
            logger.info("Waiting for elements located by: {}", locator);
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
            new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.not(ExpectedConditions.stalenessOf(driver.findElement(locator))));
            return driver.findElements(locator);
        } catch (Exception e) {
            logger.error("Failed to locate elements: {}. Error: {}", locator, e.getMessage());
            throw new RuntimeException("Elements not found: " + locator, e);
        }
    }

    public static void clickElement(WebDriver driver, By locator) {
        try {
            logger.info("Clicking element located by: {}", locator);
            WebElement element = waitAndFindElement(driver, locator, 50);
            element.click();
        } catch (Exception e) {
            logger.error("Failed to click element: {}. Error: {}", locator, e.getMessage());
            throw new RuntimeException("Click action failed on element: " + locator, e);
        }
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        try {
            logger.info("Sending keys to element located by: {}", locator);
            WebElement element = waitAndFindElement(driver, locator, 20);
            element.sendKeys(text);
        } catch (Exception e) {
            logger.error("Failed to send keys to element: {}. Error: {}", locator, e.getMessage());
            throw new RuntimeException("Send keys action failed on element: " + locator, e);
        }
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        try {
            logger.info("Scrolling to element located by: {}", locator);
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            logger.error("Failed to scroll to element located by: {}. Error: {}", locator, e.getMessage());
            throw new RuntimeException("Scrolling failed for element located by: " + locator, e);
        }
    }

    public static void scrollToTopWithKeys(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.HOME).perform();
    }

    public static void openInNewTab(WebDriver driver, WebElement element) {
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();    }

    public static void switchToNewTab(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabs.size() - 1));
    }

    public static void switchToMainTab(WebDriver driver) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
}
}
