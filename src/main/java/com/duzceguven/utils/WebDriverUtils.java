package com.duzceguven.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class for common WebDriver operations
 */
public class WebDriverUtils {
    
    /**
     * Waits for an element to be visible
     * 
     * @param driver WebDriver instance
     * @param locator By locator for the element
     * @param timeoutInSeconds Maximum time to wait in seconds
     * @return WebElement once it is visible
     */
    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    /**
     * Waits for an element to be clickable
     * 
     * @param driver WebDriver instance
     * @param locator By locator for the element
     * @param timeoutInSeconds Maximum time to wait in seconds
     * @return WebElement once it is clickable
     */
    public static WebElement waitForElementClickable(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    
    /**
     * Scrolls to an element using JavaScript
     * 
     * @param driver WebDriver instance
     * @param element WebElement to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Takes a screenshot and saves it to a specified path
     * 
     * @param driver WebDriver instance
     * @param fileName Name of the screenshot file
     */
    public static void takeScreenshot(WebDriver driver, String fileName) {
        try {
            org.openqa.selenium.OutputType<byte[]> outputType = org.openqa.selenium.OutputType.BYTES;
            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(outputType);
            java.nio.file.Files.write(java.nio.file.Paths.get("screenshots/" + fileName + ".png"), screenshot);
        } catch (Exception e) {
            System.err.println("Error taking screenshot: " + e.getMessage());
        }
    }
}
