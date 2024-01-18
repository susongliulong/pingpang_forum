package com.sun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.sun.mapper")
public class AlbumApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(AlbumApplication.class,args);
    }
}
