package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtils;

import java.util.*;

public class AmazonPage {

    private WebDriver driver;

    // Locators
    private final By signInButton = By.id("nav-link-accountList");
    private final By emailOrMobileTextBox = By.xpath("//*[@name=\"email\"]");
    private final By continueButton = By.id("continue");
    private final By passwordField = By.id("ap_password");
    private final By signInSubmitButton = By.id("signInSubmit");
    private final By allMenu = By.id("nav-hamburger-menu");
    private final By seeAll = By.xpath("//*[text()='See all']");
    private final By helpAndSettingsMenu = By.xpath("//*[@id=\"hmenu-content\"]/ul[contains(@class, 'hmenu-visible')]/li[21]");
    private final By videoGames = By.xpath("//ul[contains(@class, 'hmenu-visible')]/ul/li[11]/a");
    private final By allVideoGames = By.xpath("//*[@id='hmenu-content']/ul[32]/li[3]/a");
    private final By freeShippingCheckbox = By.xpath("//*[contains(text(), 'All customers')]");
    private final By brands = By.xpath("//*[text()='Brands']");
    private final By conditionIsNew = By.xpath("//*[text()='New']");
    private final By sortByDropdown = By.id("a-autoid-0-announce");
    private final By priceHighToLowOption = By.xpath("//a[text()='Price: High to Low']");
    private final By priceFulfilledByAmazon = By.xpath("//*[@class='a-price-whole']");
    private final By productPriceNotFulfilledByAmazon = By.xpath("//*[@class='a-section a-spacing-small a-spacing-top-small' and not(.//span[contains(text(), 'Fulfilled by Amazon')])]//span[contains(text(), 'EGP')]");
    private final By product = By.xpath("(//*[@class='a-section aok-relative s-image-fixed-height'])");
    private final By nextButton = By.xpath("//*[@aria-label[contains(.,'Go to next page')]]");
    private final By seeAllBuyingOptions = By.xpath("//a[text()=' See All Buying Options ']");
    private final By addToCart = By.id("add-to-cart-button");
    private final By noThanksBtn = By.id("attachSiNoCoverage");
    private final By proceedToBuy = By.xpath("//input[@value='Proceed to checkout']");
    private final By cartButton = By.xpath("//span[contains(text(), 'Cart')]");
    private final By cartCount = By.id("nav-cart-count");
    private final By skipPrimeAccount = By.xpath("(//a[contains(@href, '/-/en/checkout/p/p-40')])[1]");
    private final By shippingAndHandlingPrice = By.xpath("//div[@class='order-summary-grid']//span[contains(text(),'Shipping & handling')]/following::span[1]");
    private final By orderTotalSummary = By.xpath("//span[contains(text(),'Order total')]/ancestor::li//div[@class='order-summary-line-definition']");
    private final By deleteIcon = By.xpath("(//*[@class='a-icon a-icon-small-trash'])");
    Map<Integer, Integer> cartCountAndTotalPrice;



    public AmazonPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getOrderTotal() {
        WebElement orderTotalElement = SeleniumUtils.waitAndFindElement(driver, orderTotalSummary, 30);
        String priceText = orderTotalElement.getText();

        priceText = priceText.replaceAll("[^0-9.]", "");

        if (priceText.contains(".")) {
            priceText = priceText.substring(0, priceText.indexOf("."));
        }

        return Integer.parseInt(priceText);
    }

    public int getCartCount() {
        WebElement orderTotalElement = SeleniumUtils.waitAndFindElement(driver, cartCount, 30);
        String priceText = orderTotalElement.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
        return Integer.parseInt(priceText);
    }

    public int getShippingAndHandlingValue() {
        WebElement shippingElement = SeleniumUtils.waitAndFindElement(driver, shippingAndHandlingPrice, 30);
        String shippingText = shippingElement.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
        return shippingText.isEmpty() ? 0 : Integer.parseInt(shippingText); // Return 0 if value is "--"
    }

    public void loginToAmazon(){
        SeleniumUtils.clickElement(driver, signInButton);
        SeleniumUtils.sendKeys(driver, emailOrMobileTextBox, "00966539693826");
        SeleniumUtils.clickElement(driver, continueButton);
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

    public int[] extractPricesInReverseOrder() {

        List<WebElement> priceElementsFulfilledByAmazon = SeleniumUtils.waitAndFindElements(driver, priceFulfilledByAmazon, 30);
        List<WebElement> priceElementsNotFulfilledByAmazon = SeleniumUtils.waitAndFindElements(driver, productPriceNotFulfilledByAmazon, 30);

        int[] pricesFulfilled = new int[priceElementsFulfilledByAmazon.size()];
        int[] pricesNotFulfilled = new int[priceElementsNotFulfilledByAmazon.size()];

        for (int i = priceElementsFulfilledByAmazon.size() - 1; i >= 0; i--) {
            String priceText = priceElementsFulfilledByAmazon.get(i).getText();
            int price = Integer.parseInt(priceText.replaceAll("[^0-9.]", ""));
            pricesFulfilled[priceElementsFulfilledByAmazon.size() - 1 - i] = price;
            System.out.println("Parsed price: " + price + " (from element index " + i + ")");
        }
        for (int i = priceElementsNotFulfilledByAmazon.size() - 1; i >= 0; i--) {
            String priceText = priceElementsNotFulfilledByAmazon.get(i).getText();
            priceText = priceText.split("\\.")[0];
            int price = Integer.parseInt(priceText.replaceAll("[^0-9.]", ""));
            pricesNotFulfilled[priceElementsNotFulfilledByAmazon.size() - 1 - i] = price;
            System.out.println("Parsed price: " + price + " (from element index " + i + ")");
        }

        int[] resultArray = mergeAndSortAscending(pricesFulfilled,pricesNotFulfilled);

        return resultArray;
    }

    public Map<Integer, Integer> clickOnProductsThatTheirSumAreLessThan15KOfTheCurrentScreen(){
        List<WebElement> productPrice = SeleniumUtils.waitAndFindElements(driver, product, 30);
        int[] prdoctsArr = extractPricesInReverseOrder();
        int productsArrSize = prdoctsArr.length;
        int sum = 0;
        this.cartCountAndTotalPrice = new HashMap<>(); // Map to store product count and total price

        if(prdoctsArr[0]>15000.0){
            SeleniumUtils.clickElement(driver, nextButton);
            return this.cartCountAndTotalPrice;
        }
        else {
            for (int i = 0; i < productsArrSize; i++) {
                sum = sum + prdoctsArr[i];
                if (sum > 15000) {
                    this.cartCountAndTotalPrice.put(i, sum - prdoctsArr[i]);
                    return this.cartCountAndTotalPrice;
                }
                else {
                    int elementIndex = productsArrSize - 1 - i;
                    WebElement productElement = productPrice.get(elementIndex);
                    SeleniumUtils.openInNewTab(driver, productElement);
                    SeleniumUtils.switchToNewTab(driver);
                    if(checkElementIsDisplayed(addToCart)){
                        addToCartFlow();
                    }else if(checkIfSeeAllBuyingOptionsIsDisplayed()) {
                        seeAllBuyingOptionsFlow();}
                    SeleniumUtils.switchToMainTab(driver);
                }
            }
            return this.cartCountAndTotalPrice;
        }
    }

    public boolean checkIfSeeAllBuyingOptionsIsDisplayed(){
        return driver.findElement(seeAllBuyingOptions).isDisplayed();
    }

    public void addToCartFlow(){
        SeleniumUtils.clickElement(driver,addToCart);
        SeleniumUtils.clickElement(driver, noThanksBtn);
    }

    public void seeAllBuyingOptionsFlow(){
        SeleniumUtils.clickElement(driver, seeAllBuyingOptions);
        List<WebElement> addToCartsButtons = SeleniumUtils.waitAndFindElements(driver, addToCart, 30);
        addToCartsButtons.get(0).click();
    }

    public int checkThatCountAndPriceOfProductsAreNotEqualZeroAndReturnTheOrderSumCart()  {

            this.cartCountAndTotalPrice = clickOnProductsThatTheirSumAreLessThan15KOfTheCurrentScreen();
            while (this.cartCountAndTotalPrice.values().stream().anyMatch(value -> value == 0)){
                this.cartCountAndTotalPrice = clickOnProductsThatTheirSumAreLessThan15KOfTheCurrentScreen();
            }
            SeleniumUtils.scrollToTopWithKeys(driver);
            SeleniumUtils.waitForSpecificTime();
            SeleniumUtils.clickElement(driver, cartButton);
            SeleniumUtils.clickElement(driver, proceedToBuy);
            SeleniumUtils.waitForSpecificTime();
            if(checkElementIsDisplayed(skipPrimeAccount)) {SeleniumUtils.clickElement(driver, skipPrimeAccount);}
            int total = getOrderTotal();
            int handlingAndShipping = getShippingAndHandlingValue();
            return total - handlingAndShipping;
    }

    public boolean checkThatCartCountAndPriceIsMatchingTheTotalOrder(){

            int totalOrder = checkThatCountAndPriceOfProductsAreNotEqualZeroAndReturnTheOrderSumCart();
            SeleniumUtils.scrollToTopWithKeys(driver);
            SeleniumUtils.waitForSpecificTime();
            SeleniumUtils.clickElement(driver, cartButton);
            int cartCount = getCartCount();
            return this.cartCountAndTotalPrice.containsKey(cartCount) && this.cartCountAndTotalPrice.get(cartCount) == totalOrder;
    }

    public void deleteItemsFoundInTheCart(){
        List<WebElement> deleteButtons = SeleniumUtils.waitAndFindElements(driver, deleteIcon, 30);
        for (WebElement button : deleteButtons) {
            button.click();
        }
    }

    public boolean checkElementIsDisplayed(By locator){
        return driver.findElement(locator).isDisplayed();
    }

    public int[] mergeAndSortAscending(int[] arr1, int[] arr2) {
        int[] mergedArray = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
        System.arraycopy(arr2, 0, mergedArray, arr1.length, arr2.length);
        Arrays.sort(mergedArray);
        return mergedArray;
    }
}
