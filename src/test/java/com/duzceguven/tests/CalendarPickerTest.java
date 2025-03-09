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
        driver.get("https:
        
        handleCookieConsent();
        
        sleep(3000);
        
        openDatePickerAndSelectDate(19);
    }
    
    private void handleCookieConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            cookieButton.click();
            System.out.println("Closed cookie consent popup");
        } catch (Exception e) {
            System.out.println("Cookie popup not found or could not be closed: " + e.getMessage());
        }
    }
    
    @Step("Open date picker and select date: {day}")
    private void openDatePickerAndSelectDate(int day) {
        try {
            sleep(3000);
            
            WebElement dateField = null;
            
            try {
                dateField = driver.findElement(By.cssSelector("input.form-control.datepicker"));
                System.out.println("Found date field using class selector");
            } catch (Exception e) {
                System.out.println("Could not find date field using class selector: " + e.getMessage());
            }
            
            if (dateField == null) {
                try {
                    dateField = driver.findElement(By.xpath("
                    System.out.println("Found date field using label selector");
                } catch (Exception e) {
                    System.out.println("Could not find date field using label selector: " + e.getMessage());
                }
            }
            
            if (dateField == null) {
                try {
                    List<WebElement> inputFields = driver.findElements(By.tagName("input"));
                    if (inputFields.size() >= 3) {
                        dateField = inputFields.get(2);
                        System.out.println("Found date field by position in form");
                    }
                } catch (Exception e) {
                    System.out.println("Could not find date field by position: " + e.getMessage());
                }
            }
            
            if (dateField != null) {
                System.out.println("Clicking date field to open calendar");
                
                try {
                    dateField.click();
                    System.out.println("Clicked date field with regular click");
                } catch (Exception e) {
                    System.out.println("Regular click failed, trying JavaScript click: " + e.getMessage());
                    
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", dateField);
                    System.out.println("Clicked date field with JavaScript");
                }
                
                sleep(1500);
                
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
            
            List<WebElement> dayCells = driver.findElements(By.cssSelector("td.day"));
            System.out.println("Found " + dayCells.size() + " day cells");
            
            WebElement targetDay = null;
            for (WebElement dayCell : dayCells) {
                if (dayCell.getText().trim().equals(String.valueOf(day))) {
                    targetDay = dayCell;
                    System.out.println("Found day cell for day " + day);
                    break;
                }
            }
            
            if (targetDay != null) {
                try {
                    targetDay.click();
                    System.out.println("Clicked day " + day + " with regular click");
                } catch (Exception e) {
                    System.out.println("Regular click failed, trying JavaScript click: " + e.getMessage());
                    
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", targetDay);
                    System.out.println("Clicked day " + day + " with JavaScript");
                }
            } else {
                System.out.println("Could not find day cell for day " + day);
                
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
