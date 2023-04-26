package com.bjpowernode;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest{

    /**
     * Rigorous Test :-)
     */

    @Test
    public void testAdd(){

        System.out.println("====testAdd 测试add方法====");
        App hello = new App();
        int res = hello.add(10,20);
        //判断结果是否正确
        Assert.assertEquals(30,res);
    }

    @Test
    public void testAdd2(){

        System.out.println("====testAdd2 测试add方法====");
        App hello = new App();
        int res = hello.add(10,20);
        //判断结果是否正确
        Assert.assertEquals(30,res);
    }
}
