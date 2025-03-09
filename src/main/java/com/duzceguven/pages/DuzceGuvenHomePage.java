package com.duzceguven.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page object for the Düzce Güven homepage
 */
public class DuzceGuvenHomePage extends BasePage {
    
    // Locators based on the screenshot
    private final By departureDropdown = By.xpath("//input[@placeholder='Kalkış Noktası: Seçiniz']");
    private final By arrivalDropdown = By.xpath("//input[@placeholder='Varış Noktası: Seçiniz']");
    private final By dateField = By.xpath("//input[@placeholder='09/03/2025']");
    private final By searchButton = By.xpath("//button[contains(text(), 'Sorgula')]");
    
    // Popup locators
    private final By cookieAcceptButton = By.xpath("//button[contains(text(), 'Kabul Et')]");
    
    public DuzceGuvenHomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigates to the Düzce Güven homepage
     * 
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage goToHomePage() {
        navigateTo("https://www.duzceguven.com.tr");
        handlePopups();
        return this;
    }
    
    /**
     * Handles any popups that appear on the page
     * 
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage handlePopups() {
        // Wait a moment for popups to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Handle cookie consent popup if present
        try {
            if (driver.findElements(cookieAcceptButton).size() > 0) {
                clickElement(cookieAcceptButton);
            }
        } catch (Exception e) {
            System.out.println("Cookie popup not found or could not be closed: " + e.getMessage());
        }
        
        return this;
    }
    
    /**
     * Selects a departure location
     * 
     * @param location Departure location to select
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage selectDepartureLocation(String location) {
        // Wait for the element to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(departureDropdown));
        
        // Click on the departure dropdown to open the list
        clickElement(departureDropdown);
        
        // Type the location name
        driver.findElement(departureDropdown).clear();
        driver.findElement(departureDropdown).sendKeys(location);
        
        // Wait for dropdown options and select the first matching option
        try {
            Thread.sleep(1000); // Give time for dropdown to populate
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text(), '" + location + "')]"))); 
            clickElement(By.xpath("//li[contains(text(), '" + location + "')]"));
        } catch (Exception e) {
            System.out.println("Could not select departure location: " + e.getMessage());
        }
        
        return this;
    }
    
    /**
     * Selects an arrival location
     * 
     * @param location Arrival location to select
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage selectArrivalLocation(String location) {
        // Click on the arrival dropdown to open the list
        clickElement(arrivalDropdown);
        
        // Wait for the dropdown list to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='" + location + "']")));
        
        // Click on the location in the list
        clickElement(By.xpath("//li[text()='" + location + "']"));
        
        return this;
    }
    
    /**
     * Selects a journey date
     * 
     * @param day Day of the month to select
     * @param month Month to select (1-12)
     * @param year Year to select
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage selectDate(int day, int month, int year) {
        // Click on the date field to open the calendar picker
        clickElement(dateField);
        
        // Wait for the calendar to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'datepicker')]")));
        
        // Get the current month and year displayed in the calendar
        String currentMonthYear = driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();
        
        // Navigate to the correct month and year
        // First, navigate to the correct year if needed
        while (!currentMonthYear.contains(String.valueOf(year))) {
            // If target year is after current year, click next; otherwise click prev
            if (year > Integer.parseInt(currentMonthYear.split(" ")[1])) {
                clickElement(By.xpath("//th[@class='next']"));
            } else {
                clickElement(By.xpath("//th[@class='prev']"));
            }
            currentMonthYear = driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();
        }
        
        // Then navigate to the correct month
        String targetMonth = getMonthName(month);
        while (!currentMonthYear.contains(targetMonth)) {
            // If we're in the correct year but wrong month
            if (currentMonthYear.contains(String.valueOf(year))) {
                // If target month is after current month, click next; otherwise click prev
                if (month > getMonthNumber(currentMonthYear.split(" ")[0])) {
                    clickElement(By.xpath("//th[@class='next']"));
                } else {
                    clickElement(By.xpath("//th[@class='prev']"));
                }
                currentMonthYear = driver.findElement(By.xpath("//th[@class='datepicker-switch']")).getText();
            }
        }
        
        // Now select the day
        // Find the day that's not in the previous or next month (not having class 'old' or 'new')
        clickElement(By.xpath(String.format("//td[not(contains(@class, 'old')) and not(contains(@class, 'new')) and text()='%d']", day)));
        
        // Wait for the calendar to close
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'datepicker')]"))); 
        
        return this;
    }
    
    /**
     * Clicks the search button to search for journeys
     * 
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage clickSearchButton() {
        // Wait for the button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        
        // Click the search button
        clickElement(searchButton);
        
        // Wait for the search results to load (adjust the locator based on actual results page)
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'sefer-list') or contains(@class, 'results')]")));
        } catch (Exception e) {
            System.out.println("Search results element not found with expected class. Continuing anyway.");
        }
        
        return this;
    }
    
    /**
     * Performs a complete journey search
     * 
     * @param departureLocation Departure location
     * @param arrivalLocation Arrival location
     * @param day Day of the month
     * @param month Month (1-12)
     * @param year Year
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage searchJourney(String departureLocation, String arrivalLocation, int day, int month, int year) {
        selectDepartureLocation(departureLocation);
        selectArrivalLocation(arrivalLocation);
        selectDate(day, month, year);
        clickSearchButton();
        return this;
    }
    
    /**
     * Helper method to get month name from month number
     * 
     * @param month Month number (1-12)
     * @return Month name in Turkish
     */
    private String getMonthName(int month) {
        switch (month) {
            case 1: return "Ocak";
            case 2: return "Şubat";
            case 3: return "Mart";
            case 4: return "Nisan";
            case 5: return "Mayıs";
            case 6: return "Haziran";
            case 7: return "Temmuz";
            case 8: return "Ağustos";
            case 9: return "Eylül";
            case 10: return "Ekim";
            case 11: return "Kasım";
            case 12: return "Aralık";
            default: throw new IllegalArgumentException("Invalid month: " + month);
        }
    }
    
    /**
     * Helper method to get month number from month name
     * 
     * @param monthName Month name in Turkish
     * @return Month number (1-12)
     */
    private int getMonthNumber(String monthName) {
        switch (monthName) {
            case "Ocak": return 1;
            case "Şubat": return 2;
            case "Mart": return 3;
            case "Nisan": return 4;
            case "Mayıs": return 5;
            case "Haziran": return 6;
            case "Temmuz": return 7;
            case "Ağustos": return 8;
            case "Eylül": return 9;
            case "Ekim": return 10;
            case "Kasım": return 11;
            case "Aralık": return 12;
            default: throw new IllegalArgumentException("Invalid month name: " + monthName);
        }
    }
}
