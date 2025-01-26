package tests;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.AmazonPage;
import utils.SeleniumUtils;

import java.time.Duration;

public class AmazonTest extends BaseTest {

    private final String baseUrl = "https://www.amazon.eg/?language=en_AE";
    private AmazonPage amazonPage;

    @BeforeClass
    public void init(){
       amazonPage = new AmazonPage(driver);
    }

    @BeforeSuite
    public void setUpTest() {
        logger.info("Setting up the test.");
        setup();
    }

    @AfterSuite
    public void tearDownTest() {
        logger.info("Tearing down the test.");
        tearDown();
    }

    @BeforeMethod
    public void navigateToAmazon(){
        driver.get(baseUrl);
    }

    @Test
    public void amazonAutomation() {
        logger.info("Navigating to Amazon: {}", baseUrl);

        try {
            //Step 1: Login
            logger.info("Logging into Amazon.");
            amazonPage.loginToAmazon();

            // Step 2: Open the "All" menu
            logger.info("Opening the 'All' menu.");
            amazonPage.openAllMenu();

            // Step 3.1: Click "See All"
            logger.info("Clicking 'See All'.");
            amazonPage.clickOnSeeAll();

            // Step 3.2: Scroll to "Video Games"
            logger.info("Scrolling to 'Video Games'.");
            amazonPage.scrollToVideoGames();

            // Step 3.3: Click "All Video Games"
            logger.info("Clicking 'All Video Games'.");
            amazonPage.clickOnAllVideoGames();

            // Step 4: Apply Filters - "Free Shipping" & "New Condition"
            logger.info("Applying filters: 'Free Shipping' and 'New Condition'.");
            amazonPage.applyFilters();

            // Step 5: Sort by Price: High to Low
            logger.info("Sorting by price: High to Low.");
            amazonPage.sortByPriceFromHighToLow();

            // Step 6: Add products below 15k EGP
            logger.info("Adding products below 15k EGP to the cart.");

            // Add logic to handle product addition here.

            // Step 7: Verify all products are in the cart
//            logger.info("Verifying products in the cart.");
//            SeleniumUtils.clickElement(driver, By.id("nav-cart"));

        } catch (Exception e) {
            logger.error("An error occurred during the test: {}", e.getMessage());
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        }
    }
}
