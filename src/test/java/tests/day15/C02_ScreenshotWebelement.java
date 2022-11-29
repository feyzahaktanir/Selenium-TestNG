package tests.day15;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.io.File;
import java.io.IOException;

public class C02_ScreenshotWebelement extends TestBase {

    @Test
    public void nutellaTest() throws InterruptedException, IOException {
        //amazon anasayfaya gidelim
        driver.get("https://www.amazon.com");

        //nutella için arama yapalım
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella" + Keys.ENTER);

        //sonuçların nutella içerdiğini test edelim
        WebElement resultText = driver.findElement(By.xpath("//div[@class='sg-col-inner']"));
        String rslt = resultText.getText();
        Assert.assertTrue(rslt.contains("Nutella"));

        //testin çalıştığının ispatı için tüm sayfanın ss'ini alalım

        //tüm sayfa ss için 4 adım gerekli
        //1. TakeScreenshot objesi oluşturma
        TakesScreenshot tss =(TakesScreenshot) driver;
        //2. kaydedeceğimiz dosyayı oluşturalım
        File allpageSS = new File("SS/allpage.png");
        //3. bir dosya daha oluşturup ss objesi ile ss alalım
        File gecici = tss.getScreenshotAs(OutputType.FILE);
        //4. gecici resmi kaydetmek istediğimiz asıl dosyaya copy yapalım
        FileUtils.copyFile(gecici,allpageSS);
    }
}
