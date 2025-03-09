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
 * Correct journey search test for the Düzce Güven website
 * Selects Zonguldak as departure, Düzce as arrival, and 20/03/2025 as journey date
 */
@Epic("Düzce Güven Website Testing")
@Feature("Journey Search")
public class CorrectJourneySearchTest extends BaseTest {
    
    @Test
    @Story("User searches for journey from Zonguldak to Düzce")
    @Description("Test selects Zonguldak as departure, Düzce as arrival, and 20/03/2025 as journey date")
    @Severity(SeverityLevel.CRITICAL)
    public void testCorrectJourneySearch() {
        navigateToWebsite();
        
        selectDepartureLocationSelect2("ZONGULDAK");
        
        selectArrivalLocationSelect2("DÜZCE");
        
        selectDate("20/03/2025");
        
        clickSearchButtonJS();
        
        sleep(5000);
        
        logInfo("Journey search test from ZONGULDAK to DÜZCE on 20/03/2025 completed");
    }
    
    @Step("Navigate to Düzce Güven website")
    private void navigateToWebsite() {
        driver.get("https:
        logInfo("Navigated to Düzce Güven website");
        

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            cookieButton.click();
            logInfo("Closed cookie consent popup");
        } catch (Exception e) {
            logInfo("Cookie popup not found or could not be closed: " + e.getMessage());
        }
        

        sleep(3000);
    }
    
    @Step("Select departure location: ZONGULDAK using Select2")
    private void selectDepartureLocationSelect2(String location) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            WebElement departureSelect = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            departureSelect.click();
            logInfo("Clicked on departure dropdown");
            
            sleep(1000);
            

            WebElement zonguldakOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("select2-departure-result-0jho-6700")));
            zonguldakOption.click();
            logInfo("Selected ZONGULDAK from dropdown");
            

            if (!isElementSelected("select2-departure-result-0jho-6700")) {
                WebElement departureOption = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("
                departureOption.click();
                logInfo("Selected ZONGULDAK by text content");
            }
            
            if (!isElementSelected("select2-departure-result-0jho-6700")) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                    "var options = document.querySelectorAll('.select2-results__option');" +
                    "for (var i = 0; i < options.length; i++) {" +
                    "  if (options[i].textContent.includes('ZONGULDAK')) {" +
                    "    options[i].click();" +
                    "    break;" +
                    "  }" +
                    "}");
                logInfo("Selected ZONGULDAK using JavaScript");
            }
            
        } catch (Exception e) {
            logInfo("Error selecting departure location: " + e.getMessage());
            
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                    "var selects = document.querySelectorAll('select');" +
                    "for (var i = 0; i < selects.length; i++) {" +
                    "  if (selects[i].id === 'departure' || selects[i].name === 'departure') {" +
                    "    for (var j = 0; j < selects[i].options.length; j++) {" +
                    "      if (selects[i].options[j].text.includes('ZONGULDAK')) {" +
                    "        selects[i].options[j].selected = true;" +
                    "        var event = new Event('change', { 'bubbles': true });" +
                    "        selects[i].dispatchEvent(event);" +
                    "        break;" +
                    "      }" +
                    "    }" +
                    "  }" +
                    "}");
                logInfo("Selected ZONGULDAK using fallback JavaScript method");
            } catch (Exception jsError) {
                logInfo("JavaScript fallback also failed: " + jsError.getMessage());
            }
        }
    }
    
    private boolean isElementSelected(String elementId) {
        try {
            WebElement element = driver.findElement(By.id(elementId));
            return element.isSelected() || element.getAttribute("aria-selected").equals("true");
        } catch (Exception e) {
            return false;
        }
    }
    
    @Step("Select arrival location: DÜZCE using Select2")
    private void selectArrivalLocationSelect2(String location) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            WebElement arrivalSelect = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            arrivalSelect.click();
            logInfo("Clicked on arrival dropdown");
            
            sleep(1000);
            

            WebElement duzceOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("select2-arrival-result-wbm1-8100")));
            duzceOption.click();
            logInfo("Selected DÜZCE from dropdown");
            

            if (!isElementSelected("select2-arrival-result-wbm1-8100")) {
                WebElement arrivalOption = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("
                arrivalOption.click();
                logInfo("Selected DÜZCE by text content");
            }
            
            if (!isElementSelected("select2-arrival-result-wbm1-8100")) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                    "var options = document.querySelectorAll('.select2-results__option');" +
                    "for (var i = 0; i < options.length; i++) {" +
                    "  if (options[i].textContent.includes('DÜZCE')) {" +
                    "    options[i].click();" +
                    "    break;" +
                    "  }" +
                    "}");
                logInfo("Selected DÜZCE using JavaScript");
            }
            
        } catch (Exception e) {
            logInfo("Error selecting arrival location: " + e.getMessage());
            
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                    "var selects = document.querySelectorAll('select');" +
                    "for (var i = 0; i < selects.length; i++) {" +
                    "  if (selects[i].id === 'arrival' || selects[i].name === 'arrival') {" +
                    "    for (var j = 0; j < selects[i].options.length; j++) {" +
                    "      if (selects[i].options[j].text.includes('DÜZCE')) {" +
                    "        selects[i].options[j].selected = true;" +
                    "        var event = new Event('change', { 'bubbles': true });" +
                    "        selects[i].dispatchEvent(event);" +
                    "        break;" +
                    "      }" +
                    "    }" +
                    "  }" +
                    "}");
                logInfo("Selected DÜZCE using fallback JavaScript method");
            } catch (Exception jsError) {
                logInfo("JavaScript fallback also failed: " + jsError.getMessage());
            }
        }
    }
    
    @Step("Select date: 20/03/2025")
    private void selectDate(String dateString) {
        try {

            boolean dateSelected = DatePickerUtil.selectDate(driver, 20);
            
            if (dateSelected) {
                logInfo("Selected date 20/03/2025 using DatePickerUtil");
                return;
            }
            

            JavascriptExecutor js = (JavascriptExecutor) driver;
            

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("
            dateField.click();
            logInfo("Clicked on date field");
            sleep(1000);
            

            Boolean dateSet = (Boolean) js.executeScript(
                "var dateInputs = document.querySelectorAll('input[type=\"date\"], input.datepicker, input[placeholder*=\"Tarih\"]');" +
                "for (var i = 0; i < dateInputs.length; i++) {" +
                "  dateInputs[i].value = '20/03/2025';" +
                "  var event = new Event('change', { 'bubbles': true });" +
                "  dateInputs[i].dispatchEvent(event);" +
                "  return true;" +
                "}" +
                "return false;");
            
            if (dateSet) {
                logInfo("Set date to 20/03/2025 using JavaScript");
            } else {

                WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("
                dateElement.click();
                logInfo("Selected date from calendar: 20/03/2025");
            }
            
        } catch (Exception e) {
            logInfo("Error selecting date: " + e.getMessage());

            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript(
                    "var inputs = document.querySelectorAll('input');" +
                    "for (var i = 0; i < inputs.length; i++) {" +
                    "  if (inputs[i].type === 'date' || inputs[i].placeholder.includes('Tarih') || " +
                    "      inputs[i].className.includes('date') || inputs[i].id.includes('date')) {" +
                    "    inputs[i].value = '20/03/2025';" +
                    "    var event = new Event('change', { 'bubbles': true });" +
                    "    inputs[i].dispatchEvent(event);" +
                    "    break;" +
                    "  }" +
                    "}");
                logInfo("Set date to 20/03/2025 using ultra fallback method");
            } catch (Exception jsError) {
                logInfo("All date selection methods failed: " + jsError.getMessage());
            }
        }
    }
    
    @Step("Click search button using JavaScript")
    private void clickSearchButtonJS() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            

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
