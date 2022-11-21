package tests.day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class C01_WindowHandle {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void handleWindows() throws InterruptedException {
        driver.get("https://www.amazon.com");
        String whAmazon = driver.getWindowHandle();
        System.out.println("1st page WH value: " + whAmazon);

        driver.switchTo().newWindow(WindowType.WINDOW); // yeni bir pencere açılır.
        driver.get("https://www.bestbuy.com");
        String whBestbuy = driver.getWindowHandle();
        System.out.println("2nd page WH value: " + whBestbuy);
        Thread.sleep(3000);

        Set<String> handleValues = driver.getWindowHandles();
        System.out.println("handleValues = " + handleValues);

        driver.switchTo().newWindow(WindowType.TAB);    // ilk açılan pencerede yeni bir sekme açılır.
        driver.get("https://www.facebook.com");
        String whFacebook = driver.getWindowHandle();
        System.out.println("3rd page WH value: " + whFacebook);

        Thread.sleep(3000);

        //amazonda açık olan sayfaya geçin ve nutella için arama yapın.
        driver.switchTo().window(whAmazon);
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys("nutella" + Keys.ENTER);

        //bestbuy açıl olan sayfaya geçin ve title'ın bestbuy içerdiğini test edin.
        driver.switchTo().window(whBestbuy);
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Bestbuy"));

        //facebookun açık olduğu sayfaya geçin ve url'in https://www.facebook.com olduğunu test edin.
        driver.switchTo().window(whFacebook);
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url,"https://www.facebook.com");


    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
