package com.duzceguven.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Utility class to handle date picker interactions
 * Provides robust methods to interact with calendar elements on the Düzce Güven website
 */
public class DatePickerUtil {
    
    /**
     * Selects a date from the calendar on the Düzce Güven website
     * Uses multiple strategies to ensure reliable date selection
     * 
     * @param driver WebDriver instance
     * @param day Day of the month to select
     * @return true if date selection was successful, false otherwise
     */
    public static boolean selectDate(WebDriver driver, int day) {
        try {
            // Step 1: Click on the date field to open the calendar
            if (!clickDateField(driver)) {
                System.out.println("Failed to click on date field");
                return false;
            }
            
            // Allow time for the calendar to appear
            sleep(1500);
            
            // Step 2: Try to select the specific day from the calendar
            return selectDayFromCalendar(driver, day);
        } catch (Exception e) {
            System.out.println("Error in selectDate: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Clicks on the date field to open the calendar
     * 
     * @param driver WebDriver instance
     * @return true if click was successful, false otherwise
     */
    private static boolean clickDateField(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Strategy 1: Find by calendar icon (most reliable based on testing)
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
                System.out.println("Found and clicked date field by calendar icon");
                return true;
            }
            
            // Strategy 2: Find by placeholder
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
                System.out.println("Found and clicked date field by placeholder");
                return true;
            }
            
            // Strategy 3: Try third input field (based on form structure)
            Boolean foundByPosition = (Boolean) js.executeScript(
                "var inputs = document.getElementsByTagName('input');" +
                "if(inputs.length >= 3) {" +
                "  inputs[2].click();" +
                "  return true;" +
                "}" +
                "return false;");
            
            if (foundByPosition) {
                System.out.println("Found and clicked date field by position");
                return true;
            }
            
            System.out.println("Could not find date field using any strategy");
            return false;
        } catch (Exception e) {
            System.out.println("Error in clickDateField: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Selects a specific day from the open calendar
     * 
     * @param driver WebDriver instance
     * @param day Day of the month to select
     * @return true if day selection was successful, false otherwise
     */
    private static boolean selectDayFromCalendar(WebDriver driver, int day) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            
            // Strategy 1: Try to find and click the day cell
            Boolean selectedByJS = (Boolean) js.executeScript(
                "var dayElements = document.querySelectorAll('.datepicker-days td.day, .datepicker td.day, td.day');" +
                "for(var i=0; i<dayElements.length; i++) {" +
                "  if(dayElements[i].textContent.trim() === '" + day + "') {" +
                "    dayElements[i].click();" +
                "    return true;" +
                "  }" +
                "}" +
                "return false;");
            
            if (selectedByJS) {
                System.out.println("Selected day " + day + " using JavaScript");
                return true;
            }
            
            // Strategy 2: Set the date value directly using JavaScript
            LocalDate now = LocalDate.now();
            LocalDate date = LocalDate.of(now.getYear(), now.getMonth(), day);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = date.format(formatter);
            
            Boolean setDateValue = (Boolean) js.executeScript(
                "var inputs = document.querySelectorAll('input');" +
                "for(var i=0; i<inputs.length; i++) {" +
                "  if(inputs[i].placeholder && inputs[i].placeholder.indexOf('/') > -1 || " +
                "     inputs[i].className.indexOf('datepicker') > -1 || " +
                "     i === 2) {" +  // Third input based on form structure
                "    inputs[i].value = '" + formattedDate + "';" +
                "    var event = new Event('change', { 'bubbles': true });" +
                "    inputs[i].dispatchEvent(event);" +
                "    return true;" +
                "  }" +
                "}" +
                "return false;");
            
            if (setDateValue) {
                System.out.println("Set date value directly to " + formattedDate);
                return true;
            }
            
            System.out.println("Could not select day " + day + " using any strategy");
            return false;
        } catch (Exception e) {
            System.out.println("Error in selectDayFromCalendar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Helper method to pause execution
     * 
     * @param millis Time to sleep in milliseconds
     */
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
