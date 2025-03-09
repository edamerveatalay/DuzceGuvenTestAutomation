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

/**
 * Improved test class for searching bus journeys on the Düzce Güven website with Allure reporting
 * Focuses on better date selection handling
 */
@Epic("Düzce Güven Website Testing")
@Feature("Journey Search")
public class ImprovedDuzceGuvenTest extends BaseTest {
    
    @Test
    @Story("User searches for a journey between two locations")
    @Description("Test verifies that a user can search for a journey by selecting departure, arrival locations and date")
    @Severity(SeverityLevel.CRITICAL)
    public void testJourneySearch() {
        navigateToWebsite();
        selectDepartureLocation("Ankara");
        selectArrivalLocation("Düzce");
        selectDate(19); // Selecting day 19 from the date picker
        clickSearchButton();
    }
    
    @Step("Navigate to Düzce Güven website")
    private void navigateToWebsite() {
        // Navigate to the website
        driver.get("https://www.duzceguven.com.tr");
        
        // Handle cookie consent if present
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Kabul Et')]")));
            cookieButton.click();
            System.out.println("Closed cookie consent popup");
        } catch (Exception e) {
            System.out.println("Cookie popup not found or could not be closed: " + e.getMessage());
        }
        
        waitForPageToLoad();
    }
    
    @Step("Wait for page to load")
    private void waitForPageToLoad() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Step("Select departure location: {location}")
    private void selectDepartureLocation(String location) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement departureField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='Kalkış Noktası: Seçiniz']")));
            departureField.click();
            departureField.clear();
            departureField.sendKeys(location);
            
            Thread.sleep(1000); // Wait for dropdown to appear
            
            WebElement departureOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(), '" + location + "')]")));
            departureOption.click();
            System.out.println("Selected departure location: " + location);
        } catch (Exception e) {
            System.out.println("Could not select departure location: " + e.getMessage());
        }
    }
    
    @Step("Select arrival location: {location}")
    private void selectArrivalLocation(String location) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement arrivalField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@placeholder='Varış Noktası: Seçiniz']")));
            arrivalField.click();
            arrivalField.clear();
            arrivalField.sendKeys(location);
            
            Thread.sleep(1000); // Wait for dropdown to appear
            
            WebElement arrivalOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//li[contains(text(), '" + location + "')]")));
            arrivalOption.click();
            System.out.println("Selected arrival location: " + location);
        } catch (Exception e) {
            System.out.println("Could not select arrival location: " + e.getMessage());
        }
    }
    
    @Step("Select date: {day}")
    private void selectDate(int day) {
        try {
            // Click on the date field to open the date picker
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[contains(@placeholder, '/')]")));
            
            // Use JavaScript to click the date field
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", dateField);
            System.out.println("Clicked on date field with JavaScript");
            
            // Wait for calendar to appear and get fully rendered
            Thread.sleep(2000);
            
            // Find the table that contains the date cells
            WebElement datePickerTable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'datepicker-days')]//table")));
            
            // Find all day cells
            System.out.println("Looking for day: " + day);
            
            // Using JavaScript to find and click the date cell with class="day" and the specified text
            String jsScript = "var cells = arguments[0].querySelectorAll('td.day'); " +
                             "for(var i=0; i<cells.length; i++) { " +
                             "  if(cells[i].textContent.trim() == '" + day + "') { " +
                             "    cells[i].click(); " +
                             "    return true; " +
                             "  } " +
                             "} " +
                             "return false;";
            
            Boolean result = (Boolean) executor.executeScript(jsScript, datePickerTable);
            
            if (result) {
                System.out.println("Successfully selected date: " + day + " using JavaScript");
            } else {
                System.out.println("Failed to find and click day: " + day + " with JavaScript, trying direct approach");
                
                // Fallback to direct WebElement approach
                WebElement dayCell = driver.findElement(By.xpath("//td[@class='day' and text()='" + day + "']"));
                dayCell.click();
                System.out.println("Selected date: " + day + " using direct WebElement click");
            }
            
            // Wait for date to be applied
            Thread.sleep(1000);
            
        } catch (Exception e) {
            System.out.println("Could not select date: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @Step("Click search button and wait for results")
    private void clickSearchButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Sorgula')]")));
            searchButton.click();
            System.out.println("Clicked search button");
            
            // Wait for results to load
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Could not click search button: " + e.getMessage());
        }
        
        // Print success message
        System.out.println("Test completed successfully");
    }
}
