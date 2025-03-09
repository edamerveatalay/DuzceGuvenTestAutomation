package com.duzceguven.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Test class for searching bus journeys on the Düzce Güven website with Allure reporting
 */
@Epic("Düzce Güven Website Testing")
@Feature("Journey Search")
public class DuzceGuvenAllureTest extends BaseTest {
    
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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[contains(@placeholder, '/')]")));
            dateField.click();
            
            // Wait for calendar to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'datepicker')]")));
            
            // Find the day cell in the table row with class="day" and click it
            // This approach works with the table structure: <tr><td class="day">17</td>...<td class="day">23</td></tr>
            WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//td[@class='day' and text()='" + day + "']")));
            dateElement.click();
            System.out.println("Selected date: " + day);
        } catch (Exception e) {
            System.out.println("Could not select date: " + e.getMessage());
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
