package tests.day14;

import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.Set;

public class C03_Cookies extends TestBase {

    @Test
    public void cookieTest(){
        // 1. Amazon sayfaya gidin.
        driver.get("https://www.amazon.com");

        // 2. tüm cookie'leri listeleyin
        Set<Cookie> cookieSet = driver.manage().getCookies(); //bunun kütüphanesini selenium'dan import etmesi önemli.
        System.out.println("cookieSet = " + cookieSet); //burada yanyana yazdırır.

        int say = 1;
        for (Cookie each: cookieSet ) { System.out.println(say + ". cookie: " + each); say++; }//Böyle daha düzenli

        // 3. Sayfadaki cookies sayısının 5ten büyük olduğunu test edin.
        Assert.assertTrue(cookieSet.size()>5);

        // 4. ismi i18n-prefs olan cookie değerinin USD olduğunu test edin.
        Cookie cookiei18nprefs = driver.manage().getCookieNamed("i18n-prefs");
        System.out.println("cookiei18nprefs = " + cookiei18nprefs);
        Assert.assertTrue(cookiei18nprefs.getValue().equals("USD"));

        // 5. ismi "en sevdiğim cookie" ve değeri "cikolatali" olan bir cookie oluşturun ve sayfaya ekleyin
        Cookie myCookie = new Cookie("en sevdiğim cookie", "cikolatali");
        driver.manage().addCookie(myCookie);

        // 6. eklediğiniz cookienin sayfaya eklendiğini test edin.
        Set<Cookie> cookieSet2 = driver.manage().getCookies();
        say = 1;
        for (Cookie each: cookieSet2 ) { System.out.println(say + ". cookie: " + each); say++; }

        Assert.assertTrue(cookieSet2.contains(myCookie));
        Assert.assertTrue(myCookie.getValue().equals("cikolatali"));

        // 7. ismi skin olan cookie'yi silin ve silindiğini test edin
        driver.manage().deleteCookieNamed("skin");
        Set<Cookie> cookieSet3 = driver.manage().getCookies();
        say = 1;
        for (Cookie each: cookieSet3 ) { System.out.println(say + ". cookie: " + each); say++; }
        Assert.assertFalse(cookieSet3.contains(driver.manage().getCookieNamed("skin")));

        // 8. tüm cookieleri silin ve silindiğini test edin.
        driver.manage().deleteAllCookies();
        Set<Cookie> cookieSet4 = driver.manage().getCookies();
        System.out.println("cookieSet4 Size = " + cookieSet4.size());
        Assert.assertTrue(cookieSet4.size()==0);
    }
}
