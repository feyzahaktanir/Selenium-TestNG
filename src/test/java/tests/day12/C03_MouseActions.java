package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.Set;

public class C03_MouseActions extends TestBase {

    // 1- Yeni bir class oluşturalım: MouseActions

    @Test
    public void mouseAction() throws InterruptedException {
        // 2- https://the-internet.herokuapp.com/context_menu adresine gidelim
        driver.get("https://the-internet.herokuapp.com/context_menu");
        Thread.sleep(3000);

        Actions actions = new Actions(driver);

        // 3- Çizili alan üzerinde sağ click yapalım
        WebElement cizili = driver.findElement(By.id("hot-spot"));
        actions.contextClick(cizili).perform();
        Thread.sleep(3000);

        // 4- Alert'te çıkan yazısnın "You selected a context menu" olduğunu test edelim
        Assert.assertEquals(driver.switchTo().alert().getText(),"You selected a context menu");
        Thread.sleep(3000);

        // 5- Tamam diyerek alerti kapatalım
        driver.switchTo().alert().accept();
        Thread.sleep(1000);

        // 6- Elemental Selenium linkine tıklayalım
        driver.findElement(By.linkText("Elemental Selenium")).click();

        // 7- Açılan sayfada h1 tagında "Elemental Selenium" yazdığını tet edelim
        String wh1 = driver.getWindowHandle();
        String wh2 = "";
        Set<String> whValues = driver.getWindowHandles();

        for (String each: whValues
             ) {
            if (!whValues.equals(wh1)){
                wh2 = each;
            }
        }
        System.out.println("whValues = " + whValues);
        System.out.println("wh1 = " + wh1);
        System.out.println("wh2 = " + wh2);

        driver.switchTo().window(wh2);

        Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Elemental Selenium");
    }
}
