package com.duzceguven.tests;

import com.duzceguven.pages.DuzceGuvenHomePage;
import org.testng.annotations.Test;

/**
 * Test class for searching bus journeys on the Düzce Güven website
 */
public class DuzceGuvenSearchTest extends BaseTest {
    
    @Test
    public void testSearchJourneyFromAnkaraToDuzce() {
        DuzceGuvenHomePage homePage = new DuzceGuvenHomePage(driver);
        
        homePage.goToHomePage();
        
        homePage.selectDepartureLocation("Ankara");
        
        homePage.selectArrivalLocation("Düzce");
        
        homePage.selectDate(20, 3, 2025);
        
        homePage.clickSearchButton();
        
    }
    
    @Test
    public void testSearchJourneyUsingChainedMethods() {
        DuzceGuvenHomePage homePage = new DuzceGuvenHomePage(driver);
        
        homePage.goToHomePage()
                .selectDepartureLocation("Ankara")
                .selectArrivalLocation("Düzce")
                .selectDate(20, 3, 2025)
                .clickSearchButton();
        
    }
    
    @Test
    public void testSearchJourneyUsingConvenienceMethod() {
        DuzceGuvenHomePage homePage = new DuzceGuvenHomePage(driver);
        
        homePage.goToHomePage();
        
        homePage.searchJourney("Ankara", "Düzce", 20, 3, 2025);
        
    }
}
