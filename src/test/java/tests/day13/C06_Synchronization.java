package tests.day13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C06_Synchronization extends TestBase {

    @Test
    public void test1() throws InterruptedException {
        /*
        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-submit")).click();
        Thread.sleep(500);
        */
        /*
        driver.get("https://www.youtube.com");
        driver.findElement(By.xpath("(//yt-icon-button[@id='guide-button'])[1]")).click();
        Thread.sleep(500);
        */

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//button[.='Enable']")).click();
        //Thread.sleep(3000); //bu bekleme olmadan yazıyı göremez.
        WebElement yazi = driver.findElement(By.xpath("//p[text()=\"It's enabled!\"]"));

        Assert.assertTrue(yazi.isDisplayed());
        Thread.sleep(500);

    }
}
