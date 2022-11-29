package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class TestBase { //abstract koyarsak kimse bizim TestBase'imiz ile obje oluşturamaz.

    protected WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    public void allpageSS() throws IOException {
        TakesScreenshot tss = (TakesScreenshot) driver;
        String dateSS = new SimpleDateFormat("yyMMdd_hhmmss").format(new Date());
        File allpage = new File("target/SS/allpage_" + dateSS + ".jpeg");
        File temporary = tss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(temporary,allpage);
    }
}
