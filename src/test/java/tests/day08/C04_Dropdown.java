package tests.day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class C04_Dropdown {

    static WebDriver driver;
    WebElement ddElement;
    Select select;

    @BeforeMethod
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        //https://www.amazon.com/ adresine gidin
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void Test1(){
        ddElement = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select = new Select(ddElement);

        //Arama kutusunun yanındaki kategori menüsündeki kategori sayısının 45 olduğunu test edin.
        List<WebElement> optionList = select.getOptions();
        int actualSize = optionList.size();
        int expectedSize = 28;

        Assert.assertEquals(actualSize, expectedSize);
    }

    @Test
    public void Test2(){
        ddElement = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        select = new Select(ddElement);

        //1. Kategori menüsünden Books seçeneğini seçin
        select.selectByVisibleText("Books");
        System.out.println(select.getFirstSelectedOption().getText());

        //2. Arama kutusuna Java yazın ve aratın
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("Java" + Keys.ENTER);

        //3. Bulunan sonuç sayısını yazdırın
        WebElement results = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        System.out.println(results.getText());

        //4. Sonucun Java kelimesini içerdiğini test edin.
        Assert.assertTrue(results.getText().contains("Java"));
    }

    @AfterMethod
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.close();
    }
}
