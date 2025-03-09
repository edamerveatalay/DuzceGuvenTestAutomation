package com.duzceguven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object for the login page
 */
public class LoginPage extends BasePage {
    
    // Locators
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("loginButton");
    private final By errorMessage = By.className("error-message");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigates to the login page
     * 
     * @param baseUrl Base URL of the application
     * @return LoginPage instance
     */
    public LoginPage goToLoginPage(String baseUrl) {
        navigateTo(baseUrl + "/login");
        return this;
    }
    
    /**
     * Enters username in the username field
     * 
     * @param username Username to enter
     * @return LoginPage instance
     */
    public LoginPage enterUsername(String username) {
        enterText(usernameField, username);
        return this;
    }
    
    /**
     * Enters password in the password field
     * 
     * @param password Password to enter
     * @return LoginPage instance
     */
    public LoginPage enterPassword(String password) {
        enterText(passwordField, password);
        return this;
    }
    
    /**
     * Clicks the login button
     * 
     * @return LoginPage instance
     */
    public LoginPage clickLoginButton() {
        clickElement(loginButton);
        return this;
    }
    
    /**
     * Performs login with the given credentials
     * 
     * @param username Username to enter
     * @param password Password to enter
     * @return LoginPage instance
     */
    public LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }
    
    /**
     * Gets the error message text
     * 
     * @return Error message text
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }
    
    /**
     * Checks if the error message is displayed
     * 
     * @return true if the error message is displayed, false otherwise
     */
    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }
}
