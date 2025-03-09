package com.duzceguven.tests;

import com.duzceguven.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test class for login functionality
 */
public class LoginTest extends BaseTest {
    
    private static final String BASE_URL = "https:
    
    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.goToLoginPage(BASE_URL)
                .login("validUsername", "validPassword");
        
    }
    
    @Test
    public void testInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.goToLoginPage(BASE_URL)
                .login("invalidUsername", "invalidPassword");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password");
    }
    
    @Test
    public void testEmptyCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        
        loginPage.goToLoginPage(BASE_URL)
                .login("", "");
        
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }
}
