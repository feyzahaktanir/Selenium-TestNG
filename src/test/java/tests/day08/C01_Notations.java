package tests.day08;

import org.testng.annotations.Test;

public class C01_Notations {

    @Test (priority = -9)
    public void youtubeTesti(){
        System.out.println("Youtube Testi Çalıştı");
    }

    @Test //priority atanmazsa sıfır (0) kabul eder.
    public void amazonTesti(){
        System.out.println("Amazon Testi Çalıştı");
    }

    @Test (priority = 5)
    public void bestbuyTesti(){
        System.out.println("BestBuy Testi Çalıştı");
    }
}
