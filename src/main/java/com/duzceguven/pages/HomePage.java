package com.duzceguven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object for the home page
 */
public class HomePage extends BasePage {
    
    private final By searchBox = By.id("searchBox");
    private final By searchButton = By.id("searchButton");
    private final By loginLink = By.linkText("Giriş");
    private final By welcomeMessage = By.className("welcome-message");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigates to the home page
     * 
     * @param baseUrl Base URL of the application
     * @return HomePage instance
     */
    public HomePage goToHomePage(String baseUrl) {
        navigateTo(baseUrl);
        return this;
    }
    
    /**
     * Performs a search with the given keyword
     * 
     * @param keyword Keyword to search for
     * @return HomePage instance
     */
    public HomePage search(String keyword) {
        enterText(searchBox, keyword);
        clickElement(searchButton);
        return this;
    }
    
    /**
     * Clicks on the login link
     * 
     * @return LoginPage instance
     */
    public LoginPage clickLoginLink() {
        clickElement(loginLink);
        return new LoginPage(driver);
    }
    
    /**
     * Gets the welcome message text
     * 
     * @return Welcome message text
     */
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }
    
    /**
     * Checks if the welcome message is displayed
     * 
     * @return true if the welcome message is displayed, false otherwise
     */
    public boolean isWelcomeMessageDisplayed() {
        return isElementDisplayed(welcomeMessage);
    }
    
    /**
     * Clicks on a menu item with the given text
     * 
     * @param menuText Text of the menu item to click
     * @return HomePage instance
     */
    public HomePage clickMenuItem(String menuText) {
        clickElement(By.xpath("
        return this;
    }
}
