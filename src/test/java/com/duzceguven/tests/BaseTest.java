package com.duzceguven.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Base test class that handles WebDriver setup and teardown
 */
public class BaseTest {
    
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        // Use WebDriverManager with forced download of latest version
        WebDriverManager.chromedriver()
            .clearDriverCache()
            .clearResolutionCache()
            .driverVersion("latest")
            .setup();
        
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // Uncomment the line below if you need to run tests in headless mode
        // options.addArguments("--headless");
        
        // Initialize the ChromeDriver
        driver = new ChromeDriver(options);
        
        // Set implicit wait timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Delete all cookies
        driver.manage().deleteAllCookies();
    }
    
    @AfterMethod
    public void tearDown() {
        // Close the browser and end the session
        if (driver != null) {
            driver.quit();
        }
    }
}
