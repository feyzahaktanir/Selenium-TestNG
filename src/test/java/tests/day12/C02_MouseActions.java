package tests.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import javax.swing.*;

public class C02_MouseActions extends TestBase {

    @Test
    public void amazonList() throws InterruptedException {
        // amazon anasayfaya gidin
        driver.get("https://www.amazon.com");

        Actions action = new Actions(driver);

        // sağ üstte hello, signin elementinin üzerinde mouse'u bekletin
        WebElement textHello = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
        action.moveToElement(textHello).perform();

        Thread.sleep(3000);

        // açılan menüde create a list linkine tıklayın
        WebElement createList = driver.findElement(By.xpath("//span[text()='Create a List']"));
        action.click(createList).perform();

        // ve new list sayfasının açıldığını test edin
//        SoftAssert softAssert = new SoftAssert(); //Assertion class'ında ve static değil
//        softAssert.assertTrue(driver.findElement(By.className("al-intro-banner-header")).isDisplayed());
//        softAssert.assertAll();

        Assert.assertTrue(driver.findElement(By.className("al-intro-banner-header")).isDisplayed()); //Assert class'ında ve static
    }

}
