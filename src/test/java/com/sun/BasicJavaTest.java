package com.sun;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
public class BasicJavaTest {

    @Test
    public void test(){

        System.out.println((int)Math.ceil(1.2)+" "+(int)Math.ceil(1.0));
        LocalDate now = LocalDate.now();
        System.out.println(now.toString());
        System.out.println(new Date().toString());
        System.out.println(LocalDate.parse("2023-12-10"));
    }
}
