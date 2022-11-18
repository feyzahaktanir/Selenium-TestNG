package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.crypto.KeyAgreementSpi;
import java.time.Duration;

public class C01_DependsOnMethods {

    static WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void logoTest(){
        //https://www.amazon.com/ adresine gidin
        driver.get("https://www.amazon.com/");

        //amazon anasayfaya gittiğinizi test edin.
        WebElement logo = driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test (dependsOnMethods = "logoTest")
    public void aramaTest(){
        //test1 başarılı ise searchBox'ı kullanarak "Nutella" için arama yapın
        String word = "Nutella";
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(word , Keys.ENTER);

        // ve aramanızın gerçekleştiğini test edin.
        WebElement actualword = driver.findElement(By.xpath("//span[@class='a-color-state a-text-bold']"));
        Assert.assertTrue(actualword.getText().contains(word));
    }

    @Test (priority = 2) //(dependsOnMethods = "aramaTest") bunu tek başına çalıştıramazsın
                         // çünkü sadece iki test arasında geçerli bir method, diğer testin başka bir teste bağlı olduğunu anlayamaz.
    public void fiyatTest(){
        //"Nutella" için arama yapıldıysa ilk ürünü tıklayın
        WebElement firstProd = driver.findElement(By.xpath("//div[@data-cel-widget='search_result_1']"));
        firstProd.click();

        // ve fiyatının $10.99 olduğunu test edin.
        WebElement actualPrice = driver.findElement(By.xpath("(//span[@class='a-size-mini olpWrapper'])[2]"));
        String expectedPrice = "$10.99";
        Assert.assertTrue(actualPrice.getText().contains(expectedPrice));

    }

    @AfterClass
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}
