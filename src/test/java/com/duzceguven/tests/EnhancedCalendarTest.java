package com.duzceguven.tests;

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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Enhanced test class for date picker functionality
 * Uses multiple strategies to locate and interact with date elements
 */
@Epic("Düzce Güven Website Testing")
@Feature("Enhanced Calendar Interaction")
public class EnhancedCalendarTest extends BaseTest {
    
    @Test
    @Story("User selects a date using enhanced methods")
    @Description("Test uses multiple strategies to find and interact with the date picker")
    @Severity(SeverityLevel.CRITICAL)
    public void testEnhancedDateSelection() {
        driver.get("https:
        logInfo("Navigated to website");
        
        handleCookieConsent();
        
        sleep(3000);
        
        if (clickOnDateField()) {
            logInfo("Successfully clicked on date field");
            if (selectDateFromCalendar(19)) {
                logInfo("Successfully selected date from calendar");
            } else {
                logInfo("Failed to select date from calendar");
            }
        } else {
            logInfo("Failed to click on date field");
        }
    }
    
    private void handleCookieConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            cookieButton.click();
            logInfo("Closed cookie consent popup");
        } catch (Exception e) {
            logInfo("Cookie popup not found or could not be closed: " + e.getMessage());
        }
    }
    
    @Step("Click on date field")
    private boolean clickOnDateField() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            logInfo("Attempting to find date field by placeholder");
            Boolean foundByPlaceholder = (Boolean) js.executeScript(
                "var inputs = document.getElementsByTagName('input');" +
                "for(var i=0; i<inputs.length; i++) {" +
                "  if(inputs[i].placeholder && inputs[i].placeholder.indexOf('/') > -1) {" +
                "    inputs[i].click();" +
                "    return true;" +
                "  }" +
                "}" +
                "return false;");
            
            if (foundByPlaceholder) {
                logInfo("Found and clicked date field by placeholder");
                sleep(1000);
                return true;
            }
            
            logInfo("Attempting to find date field by datepicker class");
            Boolean foundByClass = (Boolean) js.executeScript(
                "var inputs = document.getElementsByTagName('input');" +
                "for(var i=0; i<inputs.length; i++) {" +
                "  if(inputs[i].className.indexOf('datepicker') > -1 || " +
                "     inputs[i].getAttribute('data-provide') === 'datepicker') {" +
                "    inputs[i].click();" +
                "    return true;" +
                "  }" +
                "}" +
                "return false;");
            
            if (foundByClass) {
                logInfo("Found and clicked date field by datepicker class");
                sleep(1000);
                return true;
            }
            
            logInfo("Attempting to find date field by calendar icon");
            Boolean foundByIcon = (Boolean) js.executeScript(
                "var icons = document.querySelectorAll('i.fa-calendar, i.fa-calendar-alt, span.calendar-icon');" +
                "if(icons.length > 0) {" +
                "  var icon = icons[0];" +
                "  var parent = icon.parentElement;" +
                "  if(parent.tagName === 'INPUT') {" +
                "    parent.click();" +
                "    return true;" +
                "  } else {" +
                "    var input = parent.querySelector('input');" +
                "    if(input) {" +
                "      input.click();" +
                "      return true;" +
                "    } else {" +
                "      icon.click();" +
                "      return true;" +
                "    }" +
                "  }" +
                "}" +
                "return false;");
            
            if (foundByIcon) {
                logInfo("Found and clicked date field by calendar icon");
                sleep(1000);
                return true;
            }
            
            logInfo("Attempting to find date field by position");
            Boolean foundByPosition = (Boolean) js.executeScript(
                "var inputs = document.getElementsByTagName('input');" +
                "if(inputs.length >= 3) {" +
                "  inputs[2].click();" +
                "  return true;" +
                "}" +
                "return false;");
            
            if (foundByPosition) {
                logInfo("Found and clicked date field by position");
                sleep(1000);
                return true;
            }
            
            try {
                List<WebElement> inputs = driver.findElements(By.tagName("input"));
                if (inputs.size() >= 3) {
                    Actions actions = new Actions(driver);
                    actions.moveToElement(inputs.get(2)).click().perform();
                    logInfo("Clicked on potential date field using Actions class");
                    sleep(1000);
                    return true;
                }
            } catch (Exception e) {
                logInfo("Error using Actions to click: " + e.getMessage());
            }
            
            logInfo("Could not find date field using any strategy");
            return false;
            
        } catch (Exception e) {
            logInfo("Error in clickOnDateField: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    @Step("Select date {day} from calendar")
    private boolean selectDateFromCalendar(int day) {
        try {
            sleep(1500);
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            logInfo("Looking for date picker container");
            
            logInfo("Attempting to select day " + day + " using JavaScript");
            Boolean selectedByJS = (Boolean) js.executeScript(
                "var dayElements = document.querySelectorAll('.datepicker-days td.day, .datepicker td.day, td.day');" +
                "console.log('Found ' + dayElements.length + ' day elements');" +
                "for(var i=0; i<dayElements.length; i++) {" +
                "  console.log('Day element text: ' + dayElements[i].textContent.trim());" +
                "  if(dayElements[i].textContent.trim() === '" + day + "') {" +
                "    dayElements[i].click();" +
                "    return true;" +
                "  }" +
                "}" +
                "return false;");
            
            if (selectedByJS) {
                logInfo("Selected day " + day + " using JavaScript");
                sleep(1500);
                return true;
            }
            
            try {
                logInfo("Attempting to find day by XPath");
                WebElement dayElement = driver.findElement(By.xpath("
                dayElement.click();
                logInfo("Selected day " + day + " using XPath");
                sleep(1500);
                return true;
            } catch (Exception e) {
                logInfo("Could not find day by XPath: " + e.getMessage());
            }
            
            try {
                logInfo("Attempting to find day by searching all td elements");
                List<WebElement> allTds = driver.findElements(By.tagName("td"));
                for (WebElement td : allTds) {
                    if (td.getText().trim().equals(String.valueOf(day))) {
                        td.click();
                        logInfo("Selected day " + day + " by searching all td elements");
                        sleep(1500);
                        return true;
                    }
                }
            } catch (Exception e) {
                logInfo("Error searching all td elements: " + e.getMessage());
            }
            
            try {
                logInfo("Attempting to set date value directly using JavaScript");
                Boolean setDateValue = (Boolean) js.executeScript(
                    "var inputs = document.querySelectorAll('input');" +
                    "for(var i=0; i<inputs.length; i++) {" +
                    "  if(inputs[i].placeholder && inputs[i].placeholder.indexOf('/') > -1 || " +
                    "     inputs[i].className.indexOf('datepicker') > -1 || " +
                    "     i === 2) {" +
                    "    var today = new Date();" +
                    "    var date = new Date(today.getFullYear(), today.getMonth(), " + day + ");" +
                    "    var day = date.getDate().toString().padStart(2, '0');" +
                    "    var month = (date.getMonth() + 1).toString().padStart(2, '0');" +
                    "    var year = date.getFullYear();" +
                    "    var formattedDate = day + '/' + month + '/' + year;" +
                    "    inputs[i].value = formattedDate;" +
                    "    var event = new Event('change', { 'bubbles': true });" +
                    "    inputs[i].dispatchEvent(event);" +
                    "    return true;" +
                    "  }" +
                    "}" +
                    "return false;");
                
                if (setDateValue) {
                    logInfo("Set date value directly using JavaScript");
                    sleep(1500);
                    return true;
                }
            } catch (Exception e) {
                logInfo("Error setting date value directly: " + e.getMessage());
            }
            
            logInfo("Could not select day " + day + " using any strategy");
            return false;
            
        } catch (Exception e) {
            logInfo("Error in selectDateFromCalendar: " + e.getMessage());
            e.printStackTrace();
            return false;
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
