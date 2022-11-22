package tests.day14;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;


public class C02_Faker extends TestBase {

    @Test
    public void facebookKayitTesti() throws InterruptedException {
        // https://www.facebook.com adresine gidin
        driver.get("https://www.facebook.com");

        // "create new acount" butonuna basın
        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

        // "firstName" giris kutusuna bir isim yazın
        WebElement firstName = driver.findElement(By.xpath("//input[@name='firstname']"));

        Actions actions = new Actions(driver);
        Faker faker = new Faker();

        String email = faker.internet().emailAddress();

        actions.click(firstName)
                .sendKeys(faker.name().name())

        // "surname" giris kutusuna bir soyisim yazın
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().name())

        // "email" giris kutusuna bir email yazın
                .sendKeys(Keys.TAB)
                .sendKeys(email)

        // "email" onay kutusuna emaili tekrar yazın
                .sendKeys(Keys.TAB)
                .sendKeys(email)

        // Bir şifre girin
                .sendKeys(Keys.TAB)
                .sendKeys(faker.internet().password())
                .sendKeys(Keys.TAB)

        // Tarih için bir gün seçin
                .sendKeys(Keys.TAB)
                .sendKeys("30")

        // Tarih için bir ay seçin
                .sendKeys(Keys.TAB)
                .sendKeys("Kas")

        // Tarih için bir yıl seçin
                .sendKeys(Keys.TAB)
                .sendKeys("1995")
                .sendKeys(Keys.TAB)

        // Cinsiyeti seçin
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ARROW_RIGHT).perform();

        // İşaretlediğini cisiyetin seçili, diğer cinsiyetin seçili olmadığını test edin.
        WebElement male = driver.findElement(By.xpath("//input[@value='2']"));
        WebElement female = driver.findElement(By.xpath("//input[@value='1']"));

        Assert.assertTrue(male.isSelected());
        Assert.assertFalse(female.isSelected());

        // Sayfayı kapatın

        Thread.sleep(5000);
    }
}
