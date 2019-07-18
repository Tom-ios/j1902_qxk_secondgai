package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.UserMapper;
import com.qf.j1902.pojo.Perms;
import com.qf.j1902.pojo.ShIMing;
import com.qf.j1902.pojo.User;
import com.qf.j1902.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 86181 on 2019/5/27.
 */
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void xiugaiperm(int id, String name, String permission) {
        userMapper.xiugaiperm(id,name,permission);
    }

    @Override
    public List<String> findmenuName(String name) {
        return userMapper.findmenuName(name);
    }


    @Override
    public List<Perms> findAllPerms() {
        return userMapper.findAllPerms();
    }

    @Override
    public List<String> findNameByid(int id) {
        return userMapper.findNameByid(id);
    }

    @Override
    public void delperm(int id) {
        userMapper.delperm(id);
    }

    @Override
    public void xiuGaiShiM(String name, String renzheng) {
        userMapper.xiuGaiShiM(name,renzheng);
    }

    @Override
    public String findNameByID(String name) {
        return userMapper.findNameByID(name);
    }

    @Override
    public void delshiming(String idcard) {
        userMapper.delshiming(idcard);
    }

    @Override
    public void xiugaiemail(String idcard, String uemail) {
        userMapper.xiugaiemail(idcard, uemail);
    }

    @Override
    public void addUser(User user) {
            userMapper.addUser(user);
    }

    @Override
    public User findOne(String sort) {
        User one = userMapper.findOne(sort);
        return one;
    }

    @Override
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @Override
    public User findOneById(int id) {
        User oneById = userMapper.findOneById(id);
        return oneById;
    }

    @Override
    public User findOneByName(String name) {
        return userMapper.findOneByName(name);
    }

    @Override
    public void delUser(int id) {
        userMapper.delUser(id);
    }

    @Override
    public void addTrueUser(ShIMing shIMing) {
        userMapper.addTrueUser(shIMing);
    }

    @Override
    public void addTrueUser2(ShIMing shIMing) {
        userMapper.addTrueUser2(shIMing);
    }

    @Override
    public void addTrueUser3(ShIMing shIMing) {
        userMapper.addTrueUser3(shIMing);
    }

    @Override
    public void xiuGai(String idcard, String Auditstate) {
        userMapper.xiuGai(idcard,Auditstate);
    }

    @Override
    public void upUser(String name, String email, String other, int id) {
        userMapper.upUser(name,email,other,id);
    }

    @Override
    public List<ShIMing> getAllShiM() {
        return userMapper.getAllShiM();
    }

    @Override
    public void xiuGaiImg(String idcard, String img) {
        userMapper.xiuGaiImg(idcard,img);
    }

    @Override
    public ShIMing findShiMingOne(int id) {
        return userMapper.findShiMingOne(id);
    }

    @Override
    public ShIMing findOneshimingByidcard(String idcard) {
        return userMapper.findOneshimingByidcard(idcard);
    }

    @Override
    public ShIMing findOneshimingBytruename(String truename) {
        return userMapper.findOneshimingBytruename(truename);
    }

    @Override
    public void xiugaibyidcard(String idcard, String Auditstate) {
        userMapper.xiugaibyidcard(idcard,Auditstate);
    }


}
