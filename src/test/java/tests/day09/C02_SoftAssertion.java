package tests.day09;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class C02_SoftAssertion {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void Test1() throws InterruptedException {
        // 1. http://zero.webappsecurity.com/ adresine gidin
        driver.get("http://zero.webappsecurity.com/");

        // 2. Sign in butonuna basın
        driver.findElement(By.id("signin_button")).click();

        // 3. Login kutusuna "username" yazın
        driver.findElement(By.id("user_login")).sendKeys("username");

        // 4. Password kutusuna "password" yazın.
        driver.findElement(By.id("user_password")).sendKeys("password");

        // 5. Sign in kutusuna basın
        driver.findElement(By.name("submit")).click();
        driver.navigate().back();

        // 6. Online Banking menüsü içindeki Pay Bills sayfasına gidin.
        driver.findElement(By.id("online-banking")).click();
        driver.findElement(By.id("pay_bills_link")).click();

        // 7. "Purcase Foreign Currensy" tuşuna basın
        driver.findElement(By.xpath("//a[@href='#ui-tabs-3']")).click();

        // 8. "Currency" dropdown menüsünden Eurozone'u seçin.
        WebElement ddElement = driver.findElement(By.id("pc_currency"));
        Select select = new Select(ddElement);

        select.selectByValue("EUR");
        Thread.sleep(2000);

        // 9. soft assert kullanarak "Eurozone (Euro)" seçildiğini test edin.
        SoftAssert softAssert = new SoftAssert();

        String actualword = select.getFirstSelectedOption().getText();
        String expectedword = "Eurozone (euro)";

        softAssert.assertEquals(actualword, expectedword);

        // 10. soft assert kullanarak dropdown menüsünden şu seçenekleri olduğunu test edin:
        // "Select One", "Australia (dollar)", "Canada (dollar)", "Switzerland (franc)", "China (yuan)", "Denmark (krone)", "Eurozone (euro)", "Great Britain (pound)",
        // "Hong Kong (dollar)", "Japan (yen)", "Mexico (peso)", "Norway (krone)", "New Zealand (dollar)", "Sweden (krona)", "Singapore (dollar)", "Thailand (baht)"
        List<WebElement> optionList = select.getOptions();
        String actualOptionListString = "";

        for (WebElement each: optionList){
            actualOptionListString += "\"" + each.getText() + "\", ";
        }

        actualOptionListString = actualOptionListString.substring(0,actualOptionListString.length()-2);
        String expectedOptionListString = "\"Select One\", \"Australia (dollar)\", \"Canada (dollar)\", \"Switzerland (franc)\", \"China (yuan)\", \"Denmark (krone)\", \"Eurozone (euro)\", \"Great Britain (pound)\", \"Hong Kong (dollar)\", \"Japan (yen)\", \"Mexico (peso)\", \"Norway (krone)\", \"New Zealand (dollar)\", \"Sweden (krona)\", \"Singapore (dollar)\", \"Thailand (baht)\"";
        softAssert.assertEquals(actualOptionListString,expectedOptionListString);

        softAssert.assertAll();
    }


    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        //driver.close();
    }
}
