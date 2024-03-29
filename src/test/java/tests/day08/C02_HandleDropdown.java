package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;
import java.util.List;

public class C02_HandleDropdown {

    WebDriver driver;

     @BeforeMethod
    public void setUp(){
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
     }

    @Test
    public void dropdownTesti() throws InterruptedException {
         driver.get("https://www.amazon.com");
         //DropDown'da varolan seçeneklerden birini seçmek için
        //1.Adım : dropdown webelementini locate edip bir değişkene atıyoruz.
        WebElement ddElement = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

        //2.Adım : Select classından bir obje oluşturalım.
        // ve parametre olarak locate ettiğimiz Webelementi yazalım.
        Select select = new Select(ddElement);

        //3.Adım : select objesini kullanarak, Select claasında var olan 3 seçim methodundan
        // istediğimizi kullanarak dropdown'da varolan option'lardan birini seçebiliriz.
        // seçim yapmamıza yardım eden bu 3 method void'dir dolayısıyla bize birşey döndürmezler.
        select.selectByIndex(3); //sadece 3. indeksi seçer. herhangi bir veri yazdırmaz.

        // eğer seçtiğimiz option değerini yazdırmak istersek
        System.out.println(select.getFirstSelectedOption().getText()); //Baby

        Thread.sleep(3000);
        // ben dropdown'da isimle aratmak istiyorsam
        select.selectByVisibleText("Deals");

        Thread.sleep(3000);
        //incele'de dropdown select'inin child'ı olan option value ile seçtirme
        select.selectByValue("search-alias=movies-tv-intl-ship"); //Movie & TV

        // bütün listeyi yazdırma
        List<WebElement> optionList = select.getOptions();
        for (WebElement each: optionList){
            System.out.println(each.getText());
        }
    }


     @AfterMethod
    public void tearDown() throws InterruptedException {
         Thread.sleep(3000);
         driver.close();
     }
}
