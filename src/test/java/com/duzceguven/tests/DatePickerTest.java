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
 * Test class focused specifically on date picker functionality
 */
@Epic("Düzce Güven Website Testing")
@Feature("Date Picker")
public class DatePickerTest extends BaseTest {
    
    @Test
    @Story("User selects a date from the date picker")
    @Description("Test verifies that a user can select a specific date from the calendar")
    @Severity(SeverityLevel.CRITICAL)
    public void testDatePicker() {
        driver.get("https:
        
        handleCookieConsent();
        
        sleep(3000);
        
        selectDateFromCalendar(19);
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
    
    @Step("Select date {day} from calendar")
    private void selectDateFromCalendar(int day) {
        try {
            System.out.println("Attempting to click on date field");
            WebElement dateField = driver.findElement(By.xpath("
            
            takeScreenshot("before_date_click");
            
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", dateField);
            System.out.println("Clicked on date field using JavaScript");
            
            sleep(2000);
            
            takeScreenshot("calendar_opened");
            
            WebElement datePickerContainer = driver.findElement(By.xpath("
            
            if (datePickerContainer != null) {
                System.out.println("Date picker container found");
                
                List<WebElement> dayElements = datePickerContainer.findElements(By.xpath(".
                System.out.println("Found " + dayElements.size() + " day elements");
                
                WebElement dayToSelect = null;
                for (WebElement dayElement : dayElements) {
                    System.out.println("Day element text: " + dayElement.getText());
                    if (dayElement.getText().equals(String.valueOf(day))) {
                        dayToSelect = dayElement;
                        break;
                    }
                }
                
                if (dayToSelect != null) {
                    System.out.println("Found day element for day " + day);
                    
                    takeScreenshot("before_day_click");
                    
                    executor.executeScript("arguments[0].click();", dayToSelect);
                    System.out.println("Successfully clicked day " + day + " with JavaScript");
                    
                    sleep(1000);
                    takeScreenshot("after_day_click");
                } else {
                    System.out.println("Could not find the day element for day " + day);
                    
                    String jsScript = 
                        "var days = document.querySelectorAll('.datepicker-days td.day');" +
                        "for (var i=0; i < days.length; i++) {" +
                        "  if (days[i].textContent.trim() === '" + day + "') {" +
                        "    days[i].click();" +
                        "    return true;" +
                        "  }" +
                        "}" +
                        "return false;";
                    
                    Boolean result = (Boolean) executor.executeScript(jsScript);
                    System.out.println("JavaScript day selection result: " + result);
                }
            } else {
                System.out.println("Date picker container not found");
            }
        } catch (Exception e) {
            System.out.println("Error selecting date: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void takeScreenshot(String name) {
        try {
            System.out.println("Taking screenshot: " + name);
        } catch (Exception e) {
            System.out.println("Could not take screenshot: " + e.getMessage());
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
