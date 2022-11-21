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

public class C01_Alerts {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // https://the-internet.herokuapp.com/javascript_alerts adresine gidin.
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void acceptAlert(){
        //Bir metod oluşturun: acceptAlert
        //      1. Butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının "You successfully clicked an alert" olduğunu test edin.

        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        driver.switchTo().alert().accept();  //sağ tık çalışmıyorsa js allert, çalışıyorsa html allert
        WebElement result = driver.findElement(By.xpath("//p[@id='result']"));

        String actualResult = result.getText();
        String expectedResult = "You successfully clicked an alert";

        Assert.assertEquals(actualResult,expectedResult);
    }


    @Test
    public void dismissAlert() {
        //Bir metod oluşturun: dismissAlert
        //      2. Butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve
        //      result mesajının "successfully" içermediğini test edin.

        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        driver.switchTo().alert().dismiss();
        WebElement result = driver.findElement(By.xpath("//p[@id='result']"));

        String actualResult = result.getText();
        String withoutResult = "successfully";

        Assert.assertFalse(actualResult.contains(withoutResult));
    }

    @Test
    public void sendKeysAlert() throws InterruptedException {
        //Bir metod oluşturun: sendKeysAlert
        //      3. Butona tıklayın, uyarıdaki metin kutusuna isminizi yazın,
        //      OK butonuna tıklayın ve
        //      result mesajında isminizin görüntülendiğini doğrulayın.

        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        driver.switchTo().alert().sendKeys("Feyza");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();

        WebElement result = driver.findElement(By.xpath("//p[@id='result']"));

        String actualResult = result.getText();
        String withResult = "Feyza";
        Thread.sleep(1000);
        Assert.assertTrue(actualResult.contains(withResult));

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
