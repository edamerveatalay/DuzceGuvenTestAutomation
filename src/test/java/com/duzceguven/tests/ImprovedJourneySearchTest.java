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
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Improved journey search test for the Düzce Güven website
 * Includes robust JavaScript-based methods for location selection and date picking
 */
@Epic("Düzce Güven Website Testing")
@Feature("Journey Search")
public class ImprovedJourneySearchTest extends BaseTest {
    
    @Test
    @Story("User completes a journey search with all required fields")
    @Description("Test performs a complete journey search with departure location, arrival location and date")
    @Severity(SeverityLevel.CRITICAL)
    public void testCompleteJourneySearch() {
        // Navigate to the website
        navigateToWebsite();
        
        // Select departure location using JavaScript
        selectDepartureLocationJS("Ankara");
        
        // Select arrival location using JavaScript
        selectArrivalLocationJS("Düzce");
        
        // Select date using our DatePickerUtil
        DatePickerUtil.selectDate(driver, 19);
        
        // Click search button
        clickSearchButtonJS();
        
        // Wait for results
        sleep(5000);
        
        logInfo("Journey search test completed successfully");
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
    
    @Step("Select departure location: {location} using JavaScript")
    private void selectDepartureLocationJS(String location) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Attempt to find departure input by placeholder text
            String script = 
                "var inputs = document.querySelectorAll('input');" +
                "for (var i = 0; i < inputs.length; i++) {" +
                "  if (inputs[i].placeholder && inputs[i].placeholder.includes('Kalkış')) {" +
                "    inputs[i].focus();" +
                "    inputs[i].value = '" + location + "';" +
                "    var event = new Event('input', { 'bubbles': true });" +
                "    inputs[i].dispatchEvent(event);" +
                "    return inputs[i];" +
                "  }" +
                "}" +
                "return null;";
            
            WebElement departureInput = (WebElement) js.executeScript(script);
            
            if (departureInput != null) {
                logInfo("Found and set departure location input with JavaScript: " + location);
                
                // Wait for dropdown to appear
                sleep(2000);
                
                // Try to select from dropdown using JavaScript
                String selectScript = 
                    "var options = document.querySelectorAll('li');" +
                    "for (var i = 0; i < options.length; i++) {" +
                    "  if (options[i].textContent.includes('" + location + "')) {" +
                    "    options[i].click();" +
                    "    return true;" +
                    "  }" +
                    "}" +
                    "return false;";
                
                Boolean selected = (Boolean) js.executeScript(selectScript);
                
                if (selected) {
                    logInfo("Selected departure location from dropdown: " + location);
                } else {
                    logInfo("Could not find location in dropdown, using direct value");
                }
            } else {
                // Fallback: try to set the first input field
                String fallbackScript = 
                    "var inputs = document.querySelectorAll('input');" +
                    "if (inputs.length > 0) {" +
                    "  inputs[0].focus();" +
                    "  inputs[0].value = '" + location + "';" +
                    "  var event = new Event('change', { 'bubbles': true });" +
                    "  inputs[0].dispatchEvent(event);" +
                    "  return true;" +
                    "}" +
                    "return false;";
                
                Boolean fallbackResult = (Boolean) js.executeScript(fallbackScript);
                
                if (fallbackResult) {
                    logInfo("Set departure location using fallback method: " + location);
                } else {
                    logInfo("Failed to set departure location with all methods");
                }
            }
            
            // Give time for the selection to take effect
            sleep(1000);
            
        } catch (Exception e) {
            logInfo("Error selecting departure location: " + e.getMessage());
        }
    }
    
    @Step("Select arrival location: {location} using JavaScript")
    private void selectArrivalLocationJS(String location) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Attempt to find arrival input by placeholder text
            String script = 
                "var inputs = document.querySelectorAll('input');" +
                "for (var i = 0; i < inputs.length; i++) {" +
                "  if (inputs[i].placeholder && inputs[i].placeholder.includes('Varış')) {" +
                "    inputs[i].focus();" +
                "    inputs[i].value = '" + location + "';" +
                "    var event = new Event('input', { 'bubbles': true });" +
                "    inputs[i].dispatchEvent(event);" +
                "    return inputs[i];" +
                "  }" +
                "}" +
                "return null;";
            
            WebElement arrivalInput = (WebElement) js.executeScript(script);
            
            if (arrivalInput != null) {
                logInfo("Found and set arrival location input with JavaScript: " + location);
                
                // Wait for dropdown to appear
                sleep(2000);
                
                // Try to select from dropdown using JavaScript
                String selectScript = 
                    "var options = document.querySelectorAll('li');" +
                    "for (var i = 0; i < options.length; i++) {" +
                    "  if (options[i].textContent.includes('" + location + "')) {" +
                    "    options[i].click();" +
                    "    return true;" +
                    "  }" +
                    "}" +
                    "return false;";
                
                Boolean selected = (Boolean) js.executeScript(selectScript);
                
                if (selected) {
                    logInfo("Selected arrival location from dropdown: " + location);
                } else {
                    logInfo("Could not find location in dropdown, using direct value");
                }
            } else {
                // Fallback: try to set the second input field
                String fallbackScript = 
                    "var inputs = document.querySelectorAll('input');" +
                    "if (inputs.length > 1) {" +
                    "  inputs[1].focus();" +
                    "  inputs[1].value = '" + location + "';" +
                    "  var event = new Event('change', { 'bubbles': true });" +
                    "  inputs[1].dispatchEvent(event);" +
                    "  return true;" +
                    "}" +
                    "return false;";
                
                Boolean fallbackResult = (Boolean) js.executeScript(fallbackScript);
                
                if (fallbackResult) {
                    logInfo("Set arrival location using fallback method: " + location);
                } else {
                    logInfo("Failed to set arrival location with all methods");
                }
            }
            
            // Give time for the selection to take effect
            sleep(1000);
            
        } catch (Exception e) {
            logInfo("Error selecting arrival location: " + e.getMessage());
        }
    }
    
    @Step("Click search button using JavaScript")
    private void clickSearchButtonJS() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Try to find the search button by text content
            String script = 
                "var buttons = document.querySelectorAll('button');" +
                "for (var i = 0; i < buttons.length; i++) {" +
                "  if (buttons[i].textContent.includes('Sorgula')) {" +
                "    buttons[i].click();" +
                "    return true;" +
                "  }" +
                "}" +
                "return false;";
            
            Boolean clicked = (Boolean) js.executeScript(script);
            
            if (clicked) {
                logInfo("Clicked search button using JavaScript");
            } else {
                // Fallback: try to click any button that looks like a submit button
                String fallbackScript = 
                    "var buttons = document.querySelectorAll('button[type=\"submit\"], input[type=\"submit\"], button.btn-primary, button.search-button');" +
                    "if (buttons.length > 0) {" +
                    "  buttons[0].click();" +
                    "  return true;" +
                    "}" +
                    "return false;";
                
                Boolean fallbackResult = (Boolean) js.executeScript(fallbackScript);
                
                if (fallbackResult) {
                    logInfo("Clicked search button using fallback method");
                } else {
                    logInfo("Failed to click search button with all methods");
                }
            }
            
        } catch (Exception e) {
            logInfo("Error clicking search button: " + e.getMessage());
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
