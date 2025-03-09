package com.duzceguven.tests;

import com.duzceguven.pages.DuzceGuvenHomePage;
import org.testng.annotations.Test;

/**
 * Test class for searching bus journeys on the Düzce Güven website
 */
public class DuzceGuvenSearchTest extends BaseTest {
    
    @Test
    public void testSearchJourneyFromAnkaraToDuzce() {
        // Create an instance of the Düzce Güven homepage
        DuzceGuvenHomePage homePage = new DuzceGuvenHomePage(driver);
        
        // Navigate to the Düzce Güven website
        homePage.goToHomePage();
        
        // Select departure location as Ankara
        homePage.selectDepartureLocation("Ankara");
        
        // Select arrival location as Düzce
        homePage.selectArrivalLocation("Düzce");
        
        // Select March 20, 2025 as the journey date
        homePage.selectDate(20, 3, 2025);
        
        // Click the search button
        homePage.clickSearchButton();
        
        // Add assertions here to verify that the search results are displayed correctly
        // For example:
        // Assert.assertTrue(homePage.areSearchResultsDisplayed());
    }
    
    @Test
    public void testSearchJourneyUsingChainedMethods() {
        // Create an instance of the Düzce Güven homepage
        DuzceGuvenHomePage homePage = new DuzceGuvenHomePage(driver);
        
        // Perform the complete search operation using chained methods
        homePage.goToHomePage()
                .selectDepartureLocation("Ankara")
                .selectArrivalLocation("Düzce")
                .selectDate(20, 3, 2025)
                .clickSearchButton();
        
        // Add assertions here to verify that the search results are displayed correctly
    }
    
    @Test
    public void testSearchJourneyUsingConvenienceMethod() {
        // Create an instance of the Düzce Güven homepage
        DuzceGuvenHomePage homePage = new DuzceGuvenHomePage(driver);
        
        // Navigate to the Düzce Güven website
        homePage.goToHomePage();
        
        // Use the convenience method to perform the complete search operation
        homePage.searchJourney("Ankara", "Düzce", 20, 3, 2025);
        
        // Add assertions here to verify that the search results are displayed correctly
    }
}
