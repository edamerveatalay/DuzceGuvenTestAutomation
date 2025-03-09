package com.duzceguven.tests;

import com.duzceguven.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for login functionality
 */
public class LoginTest extends BaseTest {
    
    private static final String BASE_URL = "https://example.com"; // Replace with your actual website URL
    
    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.goToLoginPage(BASE_URL)
                .login("validUsername", "validPassword");
        
        // Add assertions for successful login
        // For example, check if user is redirected to dashboard
        // Assert.assertTrue(new DashboardPage(driver).isDashboardDisplayed());
    }
    
    @Test
    public void testInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.goToLoginPage(BASE_URL)
                .login("invalidUsername", "invalidPassword");
        
        // Assert that error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password");
    }
    
    @Test
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.goToLoginPage(BASE_URL)
                .login("", "");
        
        // Assert that error message is displayed
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }
}
