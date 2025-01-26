package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SeleniumUtils;

import java.time.Duration;

public class AmazonPage {

    private WebDriver driver;

    // Locators
    private By signInButton = By.id("nav-link-accountList");
    private By emailOrMobileTextBox = By.xpath("//*[@name=\"email\"]");
    private By continueButton = By.id("continue");
    private By passwordField = By.id("ap_password");
    private By signInSubmitButton = By.id("signInSubmit");
    private By allMenu = By.id("nav-hamburger-menu");
    private By seeAll = By.xpath("//*[text()='See all']");
    private By helpAndSettingsMenu = By.xpath("//*[@id=\"hmenu-content\"]/ul[contains(@class, 'hmenu-visible')]/li[21]");
    private By videoGames = By.xpath("//ul[contains(@class, 'hmenu-visible')]/ul/li[11]/a");
    private By allVideoGames = By.xpath("//*[@id='hmenu-content']/ul[32]/li[3]/a");
    private By freeShippingCheckbox = By.xpath("//*[contains(text(), 'All customers')]");
    private By brands = By.xpath("//*[text()='Brands']");
    private By conditionIsNew = By.xpath("//*[text()='New']");
    private By sortByDropdown = By.id("a-autoid-0-announce");
    private By priceHighToLowOption = By.xpath("//a[text()='Price: High to Low']");



    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginToAmazon(){
        SeleniumUtils.clickElement(driver, signInButton);
        SeleniumUtils.sendKeys(driver, emailOrMobileTextBox, "00966539693826");
        SeleniumUtils.clickElement(driver, continueButton);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Retrieve the password from the environment variable
        String password = System.getenv("MY_PASSWORD");
        SeleniumUtils.sendKeys(driver, passwordField, password);
        SeleniumUtils.clickElement(driver, signInSubmitButton);
    }

    public void openAllMenu(){
        SeleniumUtils.clickElement(driver, allMenu);
    }

    public void clickOnSeeAll(){
        SeleniumUtils.clickElement(driver, seeAll);
    }

    public void scrollToVideoGames(){
        SeleniumUtils.scrollToElement(driver, helpAndSettingsMenu);
        SeleniumUtils.clickElement(driver, videoGames);
    }

    public void clickOnAllVideoGames(){
        SeleniumUtils.clickElement(driver, allVideoGames);
    }

    public void applyFilters(){
        SeleniumUtils.clickElement(driver, freeShippingCheckbox);
        SeleniumUtils.scrollToElement(driver, brands);
        SeleniumUtils.clickElement(driver, conditionIsNew);
    }

    public void sortByPriceFromHighToLow(){
        SeleniumUtils.clickElement(driver, sortByDropdown);
        SeleniumUtils.clickElement(driver, priceHighToLowOption);
    }
}
