package tests.day13;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C05_FileUpload extends TestBase {
    // 1- Tests package'ının altına bir class oluşturalım: FileUpload

    @Test
    public void fileUpload() throws InterruptedException {
        // 2- https://the-internet.herokuapp.com/upload adresine gidelim
        driver.get("https://the-internet.herokuapp.com/upload");

        // 3- ChooseFile butonuna basalım.
        // 4- Yüklemek istediğiniz dosyayı seçelim
        //      - choose file butonunu locate edelim
        WebElement chooseFile = driver.findElement(By.id("file-upload"));

        //      - yükleyeceğimiz dosyanın dinamik path'ini hazırlayalım
        String filePath = System.getProperty("user.home") + "\\Downloads\\test.txt";

        //      - sendKeys() ile dinamik path'i dosya seç butonuna yollayalım.
        chooseFile.sendKeys(filePath);

        // 5- Upload butonuna basalım
        driver.findElement(By.id("file-submit")).click();

        // 6- "File Uploaded!" textinin görüntülendiğini test edelim
        Assert.assertTrue(driver.findElement(By.tagName("h3")).isDisplayed());

        Thread.sleep(3000);
    }
}
