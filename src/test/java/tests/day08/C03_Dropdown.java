package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class C03_Dropdown {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void test01() throws InterruptedException {
        // https://the-internet.herokuapp.com/dropdown adresine gidin.
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement ddElement = driver.findElement(By.xpath("//select[@id='dropdown']"));
        Select select = new Select(ddElement);
        //1. Index kullanarak Seçenek 1'i (Option 1) seçin ve yazdırın.
        select.selectByIndex(1);
        System.out.println(select.getFirstSelectedOption().getText());

        //2. Value kullanarak Seçenek 2'yi (Option 2) seçin ve yazdırın.
        Thread.sleep(2000);
        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());

        //3. Visible Text (Görünen Metin) kullanarak Seçenek 1'i (Option 1) seçin ve yazdırın.
        Thread.sleep(2000);
        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());

        //4. Tüm dropdown değerlerini (value) yazdırın.
        List<WebElement> optionList = select.getOptions();

        for (WebElement each: optionList){
            System.out.println(each.getText());
        }

        //5. Dropdown'un boyutunu bulun, Dropdown'da 4 öğe varsa konsolda True, değilse False yazdırın.
        int actualSize = optionList.size();
        int expectedSize = 4;

        Assert.assertEquals(actualSize,expectedSize,"False");
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
