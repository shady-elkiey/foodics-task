package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    public void setup() {
        logger.info("Setting up WebDriver.");
        WebDriverManager.chromedriver().setup(); // Ensure WebDriver binaries are set up
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        logger.info("WebDriver setup complete.");
    }

    public void tearDown() {
        logger.info("Closing WebDriver.");
        if (driver != null) {
            driver.quit();
        }
    }
}
