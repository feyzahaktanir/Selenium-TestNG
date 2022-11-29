package tests.day15;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C04_JsExecuter_Click extends TestBase {

    //amazon anasayfaya gidip
    //sell linkine JSExecuter ile tıklayalım
    //ilgili sayfaya gittiğimizi test edelim

    @Test
    public void test1(){

        driver.get("https://www.amazon.com");

        //1. JsExecuter onjesi oluşturalım ve driver'ı cast edelim
        JavascriptExecutor jse = (JavascriptExecutor) driver;

        //2. ilgili webelementi locate edelim
        WebElement sellWE = driver.findElement(By.xpath("//a[normalize-space()='Sell']"));

        //3. ilgili script ve argument(Bizim web elementimiz) ile objemiz üzerinden
        //      executescript metodunu çalıştıralım
        jse.executeScript("arguments[0].click();",sellWE);
    }

}
