import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleSearchTest {

    private WebDriver driver;
    private String gridURL = "http://hdw_gold:goldTeam0709@34.48.207.106:4444/wd/hub";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(gridURL), capabilities);
        driver.manage().window().maximize();
    }

    @Test
    public void googleSearch() throws InterruptedException {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium Grid Test");
        searchBox.submit();
        Thread.sleep(20000);

        WebElement resultStats = driver.findElement(By.id("result-stats"));
        Assert.assertTrue(resultStats.isDisplayed(), "Results are not displayed.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

