package com.yao.test;


import com.yao.test.properties.JwtProperties;
import org.junit.jupiter.api.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;

public class demo1 {

    @Test
    void test1(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
    }

    @Test
    void test2() throws InterruptedException {
        vo[] arr = new vo[2];
        vo vo1 = new vo();
        vo1.setCreateDate(LocalDateTime.now());
        Thread.sleep(1000);
        vo vo2 = new vo();
        vo2.setCreateDate(LocalDateTime.now());
        arr[0] = vo1;
        arr[1] = vo2;

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
//        Stream<String> stream = Arrays.stream(arr).map(vo -> {
//            String format = formatter.format(vo.getCreateDate());
//            return format;
//        });

        String format = formatter.format(vo1.getCreateDate());

        System.out.println(format);
    }

    @Test
    void test3() throws InterruptedException {
        JwtProperties jwtProperties = new JwtProperties();
        System.out.println(jwtProperties.getSecretKey());
    }

}
