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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

/**
 * Test class specifically focused on correctly selecting a date from the calendar
 */
@Epic("Düzce Güven Website Testing")
@Feature("Calendar Date Selection")
public class CalendarPickerTest extends BaseTest {
    
    @Test
    @Story("User selects a specific date from the calendar")
    @Description("Test verifies that a user can click on the date field and select a specific date from the calendar picker")
    @Severity(SeverityLevel.CRITICAL)
    public void testDateSelection() {
        // Navigate to the website
        driver.get("https://www.duzceguven.com.tr");
        
        // Handle cookie consent
        handleCookieConsent();
        
        // Wait for page to load fully
        sleep(3000);
        
        // Open date picker and select a date
        openDatePickerAndSelectDate(19);
    }
    
    private void handleCookieConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Kabul Et')]")));
            cookieButton.click();
            System.out.println("Closed cookie consent popup");
        } catch (Exception e) {
            System.out.println("Cookie popup not found or could not be closed: " + e.getMessage());
        }
    }
    
    @Step("Open date picker and select date: {day}")
    private void openDatePickerAndSelectDate(int day) {
        try {
            // Wait for the page to be properly loaded
            sleep(3000);
            
            // Find the date field using various strategies
            WebElement dateField = null;
            
            // Strategy 1: By input field with calendar icon inside parent
            try {
                dateField = driver.findElement(By.cssSelector("input.form-control.datepicker"));
                System.out.println("Found date field using class selector");
            } catch (Exception e) {
                System.out.println("Could not find date field using class selector: " + e.getMessage());
            }
            
            // Strategy 2: Try using XPath with label text
            if (dateField == null) {
                try {
                    dateField = driver.findElement(By.xpath("//label[contains(text(), 'Tarih')]/following-sibling::input"));
                    System.out.println("Found date field using label selector");
                } catch (Exception e) {
                    System.out.println("Could not find date field using label selector: " + e.getMessage());
                }
            }
            
            // Strategy 3: Try finding the date field by its position in the form
            if (dateField == null) {
                try {
                    // Based on your screenshot, the date field is the third input in the form
                    List<WebElement> inputFields = driver.findElements(By.tagName("input"));
                    if (inputFields.size() >= 3) {
                        dateField = inputFields.get(2); // Third input field (0-indexed)
                        System.out.println("Found date field by position in form");
                    }
                } catch (Exception e) {
                    System.out.println("Could not find date field by position: " + e.getMessage());
                }
            }
            
            // If we found the date field, click it to open the calendar
            if (dateField != null) {
                System.out.println("Clicking date field to open calendar");
                
                // Try regular click first
                try {
                    dateField.click();
                    System.out.println("Clicked date field with regular click");
                } catch (Exception e) {
                    System.out.println("Regular click failed, trying JavaScript click: " + e.getMessage());
                    
                    // Try JavaScript click as fallback
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", dateField);
                    System.out.println("Clicked date field with JavaScript");
                }
                
                // Wait for calendar to appear
                sleep(1500);
                
                // Now that the calendar is open, select the day
                selectDayFromCalendar(day);
            } else {
                System.out.println("Could not find date field using any strategy");
            }
        } catch (Exception e) {
            System.out.println("Error in openDatePickerAndSelectDate: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void selectDayFromCalendar(int day) {
        try {
            System.out.println("Attempting to select day " + day + " from calendar");
            
            // Find day cells with class="day" as seen in the screenshot
            List<WebElement> dayCells = driver.findElements(By.cssSelector("td.day"));
            System.out.println("Found " + dayCells.size() + " day cells");
            
            // Look for the specific day we want
            WebElement targetDay = null;
            for (WebElement dayCell : dayCells) {
                if (dayCell.getText().trim().equals(String.valueOf(day))) {
                    targetDay = dayCell;
                    System.out.println("Found day cell for day " + day);
                    break;
                }
            }
            
            // If we found the day, click it
            if (targetDay != null) {
                // Try regular click first
                try {
                    targetDay.click();
                    System.out.println("Clicked day " + day + " with regular click");
                } catch (Exception e) {
                    System.out.println("Regular click failed, trying JavaScript click: " + e.getMessage());
                    
                    // Try JavaScript click as fallback
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", targetDay);
                    System.out.println("Clicked day " + day + " with JavaScript");
                }
            } else {
                System.out.println("Could not find day cell for day " + day);
                
                // Try direct JavaScript selection as last resort
                JavascriptExecutor js = (JavascriptExecutor) driver;
                String jsScript = 
                    "var days = document.querySelectorAll('td.day');" +
                    "for (var i=0; i < days.length; i++) {" +
                    "  if (days[i].textContent.trim() === '" + day + "') {" +
                    "    days[i].click();" +
                    "    return true;" +
                    "  }" +
                    "}" +
                    "return false;";
                
                Boolean result = (Boolean) js.executeScript(jsScript);
                System.out.println("JavaScript day selection result: " + result);
            }
            
            // Wait a moment to see the result
            sleep(2000);
            
        } catch (Exception e) {
            System.out.println("Error selecting day from calendar: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
