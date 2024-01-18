package com.sun;

import com.sun.mapper.AdminMapper;
import com.sun.mapper.PhotographMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Unit test for simple App.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest
{
    /**
     * Rigorous Test :-)
     */

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PhotographMapper photographMapper;


    @Test
    public void testEnvironment(){
        adminMapper.findAll().forEach(System.out::println);
    }

    @Test
    public void test(){
        System.out.println(File.separator);
    }

    @Test
    public void testFindNamesByAlbumId(){
        photographMapper.findNamesByAlbumId(7L).forEach(System.out::println);
    }
}
