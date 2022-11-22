package tests.day13;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class C02_FileExist {
    @Test
    public void test(){
        System.out.println(System.getProperty("user.home"));

        //masaüstündeki Dijital Sahne klasörünün Path'i
        //"C:\Users\pc\Desktop\Dijital Sahne\Kitap1.xlsx"
        String path = System.getProperty("user.home")+"\\Desktop\\Dijital Sahne\\Kitap1.xlsx";
        System.out.println("path = " + path);

        System.out.println("user.dir: " + System.getProperty("user.dir")); //projenin olduğu klasörün yolunu getirir.

        // Masaüstündeki Dijital Sahne klasörünün içinde Kitap1.xlsx dosyasının olduğunu test edin.
        String path2 = System.getProperty("user.home")+"\\Desktop\\Dijital Sahne\\Kitap1.xlsx";
        System.out.println("path2 = " + path2);

        System.out.println(Files.exists(Paths.get(path2))); //true döndü

        //Projemizde pom.xml olduğunu test edin.
        System.out.println(System.getProperty("user.dir"));
        String pompath = System.getProperty("user.dir") + "\\pom.xml";
        Assert.assertTrue(Files.exists(Paths.get(pompath)));
        System.out.println(Files.exists(Paths.get(pompath)));
    }
}
