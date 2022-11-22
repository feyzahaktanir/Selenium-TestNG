package tests.day12;

import org.testng.annotations.Test;
import tests.day10.C01_Alerts;
import utilities.TestBase;

public class C01_TestBaseKullanim {

    @Test
    public void test1(){

        //Aşağıdaki şekilde projeniz içindeki herhangi bir class'tan obje oluşturabilir ve
        //o obje sayesinde ait olduğu class'daki tüm variable ve method'lara
        //(access modifier izin verdiği sürece) ulaşabilirim.
        C01_Alerts obj = new C01_Alerts();
        obj.acceptAlert();

        //eğer proje kapsamında bir class'dan obje oluşturulmasını engellemek isterseniz
        //  1- o class'ın constructor'ını private yapabiliriz.
        //  2- class'ın kendisini abstract yapabiliriz.
        // 1.method çok tercih edilmez çünkü OOP concept'e uymaz (çok sınırlı sayıda kullanımı vardır).
        // 2.method'u kullanabiliriz, böylece o class'daki abstract olmayan (concrate)
        // methodları override etmek mevburiyeti olmadan kullanabiliriz.
        // ama obje oluşturamayız.

        //Örneğin biz testBase class'ını abstract yaptığımızdan obje oluşturamayız.
        // ama child class'lardan TestBase'deki setUp ve tearDown methodlarını kullanabiliriz.

        //TestBase obj1 = new TestBase();
    }
}
