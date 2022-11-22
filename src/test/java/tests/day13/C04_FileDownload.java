package tests.day13;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C04_FileDownload extends TestBase {

    // 1- Tests package'ının altına bir class oluşturalım: FileDownload
    // 2- İki tane metod oluşturun: isExist() ve downloadTest()
    // 3- downloadTest() metodunun içinde aşağıdaki testi yapalaım:
    //          - https://the-internet.herokuapp.com/download adresine gidelim
    //          - test.txt dosyasını indirelim
    // 4- Ardından isExist() metodunda dosyanın başarıyla indirilip indirilmediğini test edelim.

    @Test
    public void isExistTest(){
        String filePath = System.getProperty("user.home") + "\\Downloads\\test.txt";
        Assert.assertTrue(Files.exists(Paths.get(filePath)));
    }

    @Test
    public void downloadTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.xpath("//a[.='test.txt']")).click();
        Thread.sleep(5000);
    }
}
