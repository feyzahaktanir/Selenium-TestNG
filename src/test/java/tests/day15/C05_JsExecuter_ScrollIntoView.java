package tests.day15;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C05_JsExecuter_ScrollIntoView extends TestBase {

    //ilgili webelementi bulana kadar sayfada scroll yapar.


    //Action kullanılarak yapılan
    @Test
    public void scrollAction(){

        driver.get("https://www.hotelmycamp.com/");
        Actions actions = (Actions) driver;
        actions.sendKeys(Keys.PAGE_DOWN)
                .sendKeys(Keys.PAGE_DOWN)
                .perform();

        WebElement detailRoom = driver.findElement(By.xpath("(//a[@class='btn-custom'])[2]"));
        detailRoom.click();
    }

    //JSExecuter kullanılarak yapılan
    @Test
    public void scrollJsExecuter(){
        driver.get("https://www.hotelmycamp.com/");

        //1. JsExecuter onjesi oluşturalım ve driver'ı cast edelim
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //2. ilgili webelementi locate edelim
        WebElement detailRoom = driver.findElement(By.xpath("(//a[@class='btn-custom'])[2]"));
        //3. ilgili script ve argument(Bizim web elementimiz) ile objemiz üzerinden
        //      executescript metodunu çalıştıralım
        jse.executeScript("arguments[0].scrollIntoView(true);",detailRoom);
        jse.executeScript("arguments[0].click();",detailRoom);
    }

}
