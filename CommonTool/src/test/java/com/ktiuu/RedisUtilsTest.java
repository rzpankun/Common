package com.ktiuu;

import com.ktiuu.reids.RedisUtils;
import org.junit.Test;

/**
 * @Create by pankun
 * @DATE 2020/5/22
 */
public class RedisUtilsTest {
    @Test
    public void testSet(){
        RedisUtils.set("key1","name1");
    }
    @Test
    public void testGet(){
        String key1 = RedisUtils.get("key1");
    }
    @Test
    public void testSetEx(){
        RedisUtils.setex("key2", "name2", 5);
        System.out.println(RedisUtils.get("key2"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(RedisUtils.get("key2"));
    }

    @Test
    public void testSetNx(){
        RedisUtils.setnx("key1", "name11");
    }
}
