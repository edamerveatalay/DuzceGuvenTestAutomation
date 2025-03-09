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
    
    private final By departureDropdown = By.xpath("
    private final By arrivalDropdown = By.xpath("
    private final By dateField = By.xpath("
    private final By searchButton = By.xpath("
    
    private final By cookieAcceptButton = By.xpath("
    
    public DuzceGuvenHomePage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Navigates to the Düzce Güven homepage
     * 
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage goToHomePage() {
        navigateTo("https:
        handlePopups();
        return this;
    }
    
    /**
     * Handles any popups that appear on the page
     * 
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage handlePopups() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(departureDropdown));
        
        clickElement(departureDropdown);
        
        driver.findElement(departureDropdown).clear();
        driver.findElement(departureDropdown).sendKeys(location);
        
        try {
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("
            clickElement(By.xpath("
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
        clickElement(arrivalDropdown);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("
        
        clickElement(By.xpath("
        
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
        clickElement(dateField);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("
        
        String currentMonthYear = driver.findElement(By.xpath("
        
        while (!currentMonthYear.contains(String.valueOf(year))) {
            if (year > Integer.parseInt(currentMonthYear.split(" ")[1])) {
                clickElement(By.xpath("
            } else {
                clickElement(By.xpath("
            }
            currentMonthYear = driver.findElement(By.xpath("
        }
        
        String targetMonth = getMonthName(month);
        while (!currentMonthYear.contains(targetMonth)) {
            if (currentMonthYear.contains(String.valueOf(year))) {
                if (month > getMonthNumber(currentMonthYear.split(" ")[0])) {
                    clickElement(By.xpath("
                } else {
                    clickElement(By.xpath("
                }
                currentMonthYear = driver.findElement(By.xpath("
            }
        }
        
        clickElement(By.xpath(String.format("
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("
        
        return this;
    }
    
    /**
     * Clicks the search button to search for journeys
     * 
     * @return DuzceGuvenHomePage instance
     */
    public DuzceGuvenHomePage clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        
        clickElement(searchButton);
        
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("
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
