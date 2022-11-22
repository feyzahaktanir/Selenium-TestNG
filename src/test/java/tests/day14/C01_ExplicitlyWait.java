package tests.day14;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.time.Duration;

public class C01_ExplicitlyWait extends TestBase {

    //Bir class oluşturun: WaitTest
    //İki tane metod oluşturun: implicitWait, explicitWait
    //İki metod için de aşağıdaki adımları test edin.
    //      https://the-internet.herokuapp.com/dynamic_controls adresine gidin
    //      Remove butonuna basın
    //      "It's gone!" mesajının görüntülendiğini doğrulayın.
    //      Add butonuna basın
    //      "Its's back!" mesajının görüntülendiğini test edin.

    @Test
    public void implicitWait() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement remove = driver.findElement(By.xpath("//button[text()='Remove']"));
        remove.click();
        WebElement itsgonemsg = driver.findElement(By.xpath("//p[text()=\"It's gone!\"]"));
        Assert.assertTrue(itsgonemsg.isDisplayed());
        WebElement add = driver.findElement(By.xpath("//button[text()='Add']"));
        add.click();
        WebElement itsbackmsg = driver.findElement(By.xpath("//p[text()=\"It's back!\"]"));
        Assert.assertTrue(itsbackmsg.isDisplayed());

        Thread.sleep(3000);
    }

    @Test
    public void explicitWait(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        //explicitly wait kullanabilmek için önce wait objesi oluşturmalıyız.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement remove = driver.findElement(By.xpath("//button[text()='Remove']"));
        remove.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"It's gone!\"]")));
        WebElement itsgonemsg = driver.findElement(By.xpath("//p[text()=\"It's gone!\"]"));
        //wait.until(ExpectedConditions.visibilityOf(itsgonemsg));        // Bu projede locate edebilsek zaten beklemezdik o yüzden bu metod geçersiz kalıyor.
        Assert.assertTrue(itsgonemsg.isDisplayed());

        WebElement add = driver.findElement(By.xpath("//button[text()='Add']"));
        add.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"It's back!\"]")));
        WebElement itsbackmsg = driver.findElement(By.xpath("//p[text()=\"It's back!\"]"));
        Assert.assertTrue(itsbackmsg.isDisplayed());
    }
}
