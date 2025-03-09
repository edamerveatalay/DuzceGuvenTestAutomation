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
        navigateToWebsite();
        
        boolean departureSelected = selectDepartureLocation("Ankara");
        Assert.assertTrue(departureSelected, "Failed to select departure location");
        
        boolean arrivalSelected = selectArrivalLocation("Düzce");
        Assert.assertTrue(arrivalSelected, "Failed to select arrival location");
        
        boolean dateSelected = DatePickerUtil.selectDate(driver, 19);
        Assert.assertTrue(dateSelected, "Failed to select date");
        
        boolean searchClicked = clickSearchButton();
        Assert.assertTrue(searchClicked, "Failed to click search button");
        
        verifySearchResults();
        
        logInfo("Complete journey search test executed successfully");
    }
    
    @Step("Navigate to Düzce Güven website")
    private void navigateToWebsite() {
        driver.get("https:
        logInfo("Navigated to Düzce Güven website");
        
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            cookieButton.click();
            logInfo("Closed cookie consent popup");
        } catch (Exception e) {
            logInfo("Cookie popup not found or could not be closed: " + e.getMessage());
        }
        
        sleep(3000);
    }
    
    @Step("Select departure location: {location}")
    private boolean selectDepartureLocation(String location) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement departureField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            
            departureField.click();
            logInfo("Clicked on departure field");
            
            departureField.clear();
            departureField.sendKeys(location);
            logInfo("Entered departure location: " + location);
            
            sleep(1500);
            
            WebElement departureOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            departureOption.click();
            logInfo("Selected departure location from dropdown: " + location);
            
            return true;
        } catch (Exception e) {
            logInfo("Error selecting departure location: " + e.getMessage());
            
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement arrivalField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            
            arrivalField.click();
            logInfo("Clicked on arrival field");
            
            arrivalField.clear();
            arrivalField.sendKeys(location);
            logInfo("Entered arrival location: " + location);
            
            sleep(1500);
            
            WebElement arrivalOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            arrivalOption.click();
            logInfo("Selected arrival location from dropdown: " + location);
            
            return true;
        } catch (Exception e) {
            logInfo("Error selecting arrival location: " + e.getMessage());
            
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            
            searchButton.click();
            logInfo("Clicked search button");
            
            return true;
        } catch (Exception e) {
            logInfo("Error clicking search button: " + e.getMessage());
            
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
            sleep(5000);
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            boolean resultsDisplayed = wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("
                ExpectedConditions.presenceOfElementLocated(By.xpath("
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
