package tests.day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Set;

public class C02_WindowHandle {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void handleWindow(){

        // 1- https://the-internet.herokuapp.com/windows adresine gidin
        driver.get("https://the-internet.herokuapp.com/windows");

        SoftAssert softAssert = new SoftAssert();

        // 2- Sayfadaki textin "Opening a new window" olduğunu doğrulayın
        WebElement text = driver.findElement(By.tagName("h3"));
        String actualText = text.getText();
        String expectedText = "Opening a new window";

        softAssert.assertEquals(actualText,expectedText,"Opening a new window yazısı bulunamadı.");

        // 3- Click Here butonuna tıklayın
        //      Burada windowhandle değerini bilmediğim bir sayfa açılıyor.
        //      O sayfanın windowhandle değerini bulmam için geçtiğim sayfalardaki windowhandle değerlerini kaydetmeliyim.
        String wHandle1 = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();

        // 4- Açılan yeni pencerenin sayfa başlığının (title) "New Window" olduğunu doğrulayın
        //      Önce açılan sayfanın handle değerini elde etmeliyim.
        //              Önce tüm handle değerlerini alıp bir set'e koyalım.
        Set<String> wHandles = driver.getWindowHandles();
        String wHandle2 = "";

        for (String each: wHandles
             ) {
            if (!each.equals(wHandle1)){
                wHandle2 = each;
            }
        }

        System.out.println("All WindowHandle" + wHandles);
        System.out.println("wHandle2 = " + wHandle2);

        driver.switchTo().window(wHandle2);

        String actualTitle = driver.getTitle();
        String expectedTitle = "New Window";

        softAssert.assertEquals(actualTitle,expectedTitle, "Title yazısı yanlıştır.");

        // 5- Sayfadaki textin "New Window" olduğunu doğrulayın
        WebElement text2 = driver.findElement(By.tagName("h3"));
        String actualText2 = text2.getText();
        String expectedText2 = "New Window";

        softAssert.assertEquals(actualText2,expectedText2,"New Window yazısı bulunamadı.");

        // 6- Bir önceki pencereye geri döndükten sonra sayfa başlığının "The Internet" olduğunu doğrulayın

        driver.switchTo().window(wHandle1);

        String actualTitle2 = driver.getTitle();
        String expectedTitle2 = "New Window";

        softAssert.assertEquals(actualTitle2,expectedTitle2, "Title yazısı yanlıştır.");

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
