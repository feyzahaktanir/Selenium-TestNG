package tests.day11;

import org.testng.annotations.Test;
import utilities.TestBase;

public class C03_TestBaseİlkTest extends TestBase {  //bu TestNG'nin özelliği

    @Test
    public void Test1(){
        driver.get("https://www.amazon.com");
    }
}
