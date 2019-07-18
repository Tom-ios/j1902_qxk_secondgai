package com.qf.j1902;

import com.qf.j1902.mapper.UserMapper;
import com.qf.j1902.pojo.Perms;
import com.qf.j1902.pojo.ShIMing;
import com.qf.j1902.pojo.User;
import com.qf.j1902.utils.MD5;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Qxk on 2019/6/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Configuration("com.qf.j1902.mapper")
public class Mappertest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test1(){
        List<String> qxk = userMapper.findmenuName("qxk");
        for ( String s:qxk) {
            System.out.println(qxk);
        }


    }
    @Test
    public void test2(){
        String md5ofStr = new MD5().getMD5ofStr("22");
        System.out.println(md5ofStr);
        Md5Hash md5Hash = new Md5Hash("666", null);
        System.out.println(md5Hash);

    }
    @Test
    public void test3(){
        List<Perms> allPerms = userMapper.findAllPerms();
        for (Perms p:allPerms  ) {
            System.out.println(p);
        }

    }
    @Test
    public void test4(){
        userMapper.xiuGaiShiM("my","已实名");
    }
    @Test
    public void test5(){
        String nameByID = userMapper.findNameByID("33333");
        System.out.println(nameByID);

    }
    @Test
    public void test6(){
        List<String> nameByid = userMapper.findNameByid(1);
        for (String s:nameByid) {
            System.out.println(s);
        }
    }
    @Test
    public void test7(){
        List<User> all = userMapper.findAll();
        for (User u:all ) {
            System.out.println(u);
        }


    }
    @Test
    public void test8(){
        userMapper.xiugaiperm(2,"grr","hhh");
    }
    @Test
    public void test9(){
        ShIMing oneshimingByidcard = userMapper.findOneshimingByidcard("123456789");
        System.out.println(oneshimingByidcard);
    }
    @Test
    public void test10(){
        ShIMing tom = userMapper.findOneshimingBytruename("tom");
        System.out.println(tom);
    }
    @Test
    public void test11(){
        userMapper.delperm(1);
    }
    @Test
    public void test12(){
        userMapper.delshiming("6106311994xxxxxxxxxxxx");
    }
    @Test
    public void test13(){
        userMapper.xiugaiemail("123456789","1029220388@qq.com");
    }

}
