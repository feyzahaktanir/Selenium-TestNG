package tests.day10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_BasicAuthentication {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void test(){
        // 1 - https://the-internet.herokuapp.com/basic_auth adresine gidin.
        //driver.get("https://the-internet.herokuapp.com/basic_auth");

        // 2 - Aşağıdaki yöntem ve test datalarını kullanarak authentication'ı yapın
        //                 HTML Komutu  : https://username:password@URL
        //                 Username     : admin
        //                 Password     : admin
        //basic authentication isteyen web servisleri bize nasıl ve hangi bilgilerle authentication yapabileceğimiz bilgisini de vermek zorundadır.
        //bize tarif edilen yöntem ve bize verilen bilgileri birebir uygulayarak istediğimiz web servise giriş sağlayabiliriz.
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        // 3 - Başarılı şekilde sayfaya girildiğini doğrulayın.
        WebElement congratMsg = driver.findElement(By.tagName("p"));

        Assert.assertTrue(congratMsg.isDisplayed());
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
