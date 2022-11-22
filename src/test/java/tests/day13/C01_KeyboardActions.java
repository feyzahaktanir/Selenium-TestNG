package tests.day13;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C01_KeyboardActions extends TestBase {

    @Test
    public void keyboardActions() throws InterruptedException {
        // 1- Bir class oluşturalım: KeyboardActions
        // 2- https://html.com/tags/iframe/ adresine gidelim
        driver.get("https://html.com/tags/iframe/");

        // 3- Video'yu görecek kadar aşağı için
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN)
                .sendKeys(Keys.PAGE_DOWN)
                .sendKeys(Keys.ARROW_UP)
                .perform();

        // 4- Videoyu izlemek için Play tuşuna basın
        WebElement iframe = driver.findElement(By.xpath("//iframe[1]"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.xpath("//button[@class='ytp-large-play-button ytp-button ytp-large-play-button-red-bg']")).click();
        Thread.sleep(1000);
        // 5- Videoyu çalıştırdığınızı test edin.
        Assert.assertTrue(driver.findElement(By.xpath("//button[@title='Pause (k)']")).isEnabled());
        Thread.sleep(10000);
    }

}
