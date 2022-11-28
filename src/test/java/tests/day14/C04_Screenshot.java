package tests.day14;

import org.openqa.selenium.*;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.io.File;

public class C04_Screenshot extends TestBase {

    @Test
    public void ssTest(){

        driver.get("https://www.google.com");

        //1. TakesScreenshot abstract olduğu için obje oluşturamayız, cast ettik.
        //Aynı zamanda bir interface olduğu için de obje oluşturamayız.
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        //2. Tüm sayfanın ss'ini almak için bir file oluşturalım
        //dosya yolunu belirtelim
        File allpageSS = new File("src/allpage");

        //3. oluşturduğumuz file ile takesscreenshot objesini ilişkilendirelim.
        allpageSS = screenshot.getScreenshotAs(OutputType.FILE);

        //4. eğer spesifik bir webelementin ss'ni almak istiyorsak
        WebElement ggLogo = driver.findElement(By.xpath("//img[@alt='Google']"));
        File logo = new File("src/logo.png");
        logo = ggLogo.getScreenshotAs(OutputType.FILE);
    }
}
