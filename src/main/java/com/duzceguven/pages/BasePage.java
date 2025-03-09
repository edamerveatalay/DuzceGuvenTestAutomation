package com.duzceguven.pages;

import com.duzceguven.utils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Base page class that all page objects will extend
 */
public class BasePage {
    
    protected WebDriver driver;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    /**
     * Navigates to a specific URL
     * 
     * @param url URL to navigate to
     */
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Waits for an element to be visible and then clicks it
     * 
     * @param locator By locator for the element
     */
    protected void clickElement(By locator) {
        WebElement element = WebDriverUtils.waitForElementClickable(driver, locator, 10);
        element.click();
    }
    
    /**
     * Waits for an element to be visible and then enters text
     * 
     * @param locator By locator for the element
     * @param text Text to enter
     */
    protected void enterText(By locator, String text) {
        WebElement element = WebDriverUtils.waitForElementVisible(driver, locator, 10);
        element.clear();
        element.sendKeys(text);
    }
    
    /**
     * Gets text from an element
     * 
     * @param locator By locator for the element
     * @return Text content of the element
     */
    protected String getText(By locator) {
        WebElement element = WebDriverUtils.waitForElementVisible(driver, locator, 10);
        return element.getText();
    }
    
    /**
     * Checks if an element is displayed
     * 
     * @param locator By locator for the element
     * @return true if the element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(By locator) {
        try {
            return WebDriverUtils.waitForElementVisible(driver, locator, 5).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
