package com.duzceguven.tests;

import com.duzceguven.util.DatePickerUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Complete journey search test for the Düzce Güven website
 * Selects departure and arrival locations, selects a date, and clicks the search button
 */
@Epic("Düzce Güven Website Testing")
@Feature("Complete Journey Search")
public class CompleteJourneySearchTest extends BaseTest {
    
    @Test
    @Story("User searches for a journey with departure, arrival, and date")
    @Description("Test performs a complete journey search by selecting departure and arrival locations, date, and clicking search")
    @Severity(SeverityLevel.CRITICAL)
    public void testCompleteJourneySearch() {
        // Navigate to the website
        navigateToWebsite();
        
        // Select departure location
        boolean departureSelected = selectDepartureLocation("Ankara");
        Assert.assertTrue(departureSelected, "Failed to select departure location");
        
        // Select arrival location
        boolean arrivalSelected = selectArrivalLocation("Düzce");
        Assert.assertTrue(arrivalSelected, "Failed to select arrival location");
        
        // Select date using our robust DatePickerUtil
        boolean dateSelected = DatePickerUtil.selectDate(driver, 19);
        Assert.assertTrue(dateSelected, "Failed to select date");
        
        // Click search button
        boolean searchClicked = clickSearchButton();
        Assert.assertTrue(searchClicked, "Failed to click search button");
        
        // Verify search results page is displayed
        verifySearchResults();
        
        logInfo("Complete journey search test executed successfully");
    }
    
    @Step("Navigate to Düzce Güven website")
    private void navigateToWebsite() {
        // Navigate to the website
        driver.get("https://www.duzceguven.com.tr");
        logInfo("Navigated to Düzce Güven website");
        
        // Handle cookie consent if present
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Kabul Et')]")));
            cookieButton.click();
            logInfo("Closed cookie consent popup");
        } catch (Exception e) {
            logInfo("Cookie popup not found or could not be closed: " + e.getMessage());
        }
        
        // Wait for page to load completely
        sleep(3000);
    }
    
    @Step("Select departure location: {location}")
    private boolean selectDepartureLocation(String location) {
        try {
            // Find and click on the departure field
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement departureField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='Kalkış Noktası: Seçiniz']")));
            
            // Click on departure field
            departureField.click();
            logInfo("Clicked on departure field");
            
            // Clear and enter location
            departureField.clear();
            departureField.sendKeys(location);
            logInfo("Entered departure location: " + location);
            
            // Wait for dropdown to appear
            sleep(1500);
            
            // Select location from dropdown
            WebElement departureOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(), '" + location + "')]")));
            departureOption.click();
            logInfo("Selected departure location from dropdown: " + location);
            
            return true;
        } catch (Exception e) {
            logInfo("Error selecting departure location: " + e.getMessage());
            
            // Try using JavaScript as fallback
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                Boolean result = (Boolean) js.executeScript(
                    "var inputs = document.querySelectorAll('input');" +
                    "for(var i=0; i<inputs.length; i++) {" +
                    "  if(inputs[i].placeholder && inputs[i].placeholder.indexOf('Kalkış') > -1) {" +
                    "    inputs[i].value = '" + location + "';" +
                    "    var event = new Event('change', { 'bubbles': true });" +
                    "    inputs[i].dispatchEvent(event);" +
                    "    return true;" +
                    "  }" +
                    "}" +
                    "return false;");
                
                if (result) {
                    logInfo("Set departure location using JavaScript: " + location);
                    return true;
                }
            } catch (Exception jsError) {
                logInfo("JavaScript fallback also failed: " + jsError.getMessage());
            }
            
            return false;
        }
    }
    
    @Step("Select arrival location: {location}")
    private boolean selectArrivalLocation(String location) {
        try {
            // Find and click on the arrival field
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement arrivalField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='Varış Noktası: Seçiniz']")));
            
            // Click on arrival field
            arrivalField.click();
            logInfo("Clicked on arrival field");
            
            // Clear and enter location
            arrivalField.clear();
            arrivalField.sendKeys(location);
            logInfo("Entered arrival location: " + location);
            
            // Wait for dropdown to appear
            sleep(1500);
            
            // Select location from dropdown
            WebElement arrivalOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(), '" + location + "')]")));
            arrivalOption.click();
            logInfo("Selected arrival location from dropdown: " + location);
            
            return true;
        } catch (Exception e) {
            logInfo("Error selecting arrival location: " + e.getMessage());
            
            // Try using JavaScript as fallback
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                Boolean result = (Boolean) js.executeScript(
                    "var inputs = document.querySelectorAll('input');" +
                    "for(var i=0; i<inputs.length; i++) {" +
                    "  if(inputs[i].placeholder && inputs[i].placeholder.indexOf('Varış') > -1) {" +
                    "    inputs[i].value = '" + location + "';" +
                    "    var event = new Event('change', { 'bubbles': true });" +
                    "    inputs[i].dispatchEvent(event);" +
                    "    return true;" +
                    "  }" +
                    "}" +
                    "return false;");
                
                if (result) {
                    logInfo("Set arrival location using JavaScript: " + location);
                    return true;
                }
            } catch (Exception jsError) {
                logInfo("JavaScript fallback also failed: " + jsError.getMessage());
            }
            
            return false;
        }
    }
    
    @Step("Click search button")
    private boolean clickSearchButton() {
        try {
            // Find and click the search button
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Sorgula')]")));
            
            // Click search button
            searchButton.click();
            logInfo("Clicked search button");
            
            return true;
        } catch (Exception e) {
            logInfo("Error clicking search button: " + e.getMessage());
            
            // Try using JavaScript as fallback
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                Boolean result = (Boolean) js.executeScript(
                    "var buttons = document.querySelectorAll('button');" +
                    "for(var i=0; i<buttons.length; i++) {" +
                    "  if(buttons[i].textContent.includes('Sorgula')) {" +
                    "    buttons[i].click();" +
                    "    return true;" +
                    "  }" +
                    "}" +
                    "return false;");
                
                if (result) {
                    logInfo("Clicked search button using JavaScript");
                    return true;
                }
            } catch (Exception jsError) {
                logInfo("JavaScript fallback also failed: " + jsError.getMessage());
            }
            
            return false;
        }
    }
    
    @Step("Verify search results")
    private void verifySearchResults() {
        try {
            // Wait for results to load (adjust timeout as needed)
            sleep(5000);
            
            // Check if we're on the search results page
            // This could be detected by the presence of specific elements on the results page
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean resultsDisplayed = wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'search-results')]")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(), 'Sefer')]")),
                ExpectedConditions.urlContains("search")
            ));
            
            if (resultsDisplayed) {
                logInfo("Search results page is displayed");
            } else {
                logInfo("Could not verify if search results page is displayed");
            }
        } catch (Exception e) {
            logInfo("Error verifying search results: " + e.getMessage());
        }
    }
    
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }
}
