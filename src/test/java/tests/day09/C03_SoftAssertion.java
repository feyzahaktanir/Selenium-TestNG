package tests.day09;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class C03_SoftAssertion {

    @Test

    public void test(){
        int a=10;
        int b=20;
        int c=30;

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(a,b,"1. test başarısız.");
        softAssert.assertTrue(a>b,"2. test başarısız.");
        softAssert.assertTrue(a>c,"3. test başarısız.");
        softAssert.assertTrue(c>b,"4. test başarısız.");
        softAssert.assertTrue(c>a,"5. test başarısız.");

        softAssert.assertAll(); //bu satır olmazsa sadece class çalışır ve passed yazar, raporlama yapmaz sadece kodlar çalışmış olur.
        System.out.println("Eğer başarısız test varsa bu satır çalışmaz.");
    }
}
