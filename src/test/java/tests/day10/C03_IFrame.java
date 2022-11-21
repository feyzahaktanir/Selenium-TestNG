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
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class C03_IFrame {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void iframeTest() throws InterruptedException {
        // https://the-internet.herokuapp.com/iframe adresine gidin.
        driver.get("https://the-internet.herokuapp.com/iframe");
        Thread.sleep(3000);
        SoftAssert softAssert = new SoftAssert();
        // Bir metod oluşturun: iframeTest
        //      1-"An Iframe containing... ." textinin erişilebilir olduğunu test edin ve konsolda yazdırın.
        WebElement textIframe = driver.findElement(By.tagName("h3"));
        softAssert.assertTrue(textIframe.isEnabled(),"Iframe yazısı gözükmüyor.");
        System.out.println("textIframe = " + textIframe.getText());

        //      2-TextBox'a "Merhaba Dünya!" yazın.
        WebElement iFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrame); //iFrame'e girdik
                //yazı yazmak istediğimiz alan iframe'in içinde olduğu için direkt ulaşamıyoruz.
                //öncew iframe'i locate edip, onun içine switch yapmalıyız.
        WebElement textBox = driver.findElement(By.tagName("p"));
        textBox.clear();
        textBox.sendKeys("Merhaba Dünya!");

        driver.switchTo().defaultContent(); //iFrame'den çıktık

        //      3-TextBox'ın altında bulunan "Elemental Selenium" linkini textinin görünür olduğunu doğrulayın ve konsolda yazdırın.
        WebElement elemental = driver.findElement(By.xpath("(//a[@target='_blank'])[2]"));
        softAssert.assertTrue(elemental.isEnabled());
        System.out.println("elemental = " + elemental.getText());

        //sponsored yazısı yeni sayfada olduğundan ve driver eski sayfada kaldığından yazıyı locate edemedik.
        elemental.click();
        WebElement newpageTitle = driver.findElement(By.xpath("//p[text()='Sponsored by ']"));
        softAssert.assertTrue(newpageTitle.isDisplayed(),"Sponsored yazısı gözükmüyor.");

        softAssert.assertAll();
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
