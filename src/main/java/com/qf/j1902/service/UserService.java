package com.qf.j1902.service;

import com.qf.j1902.pojo.Perms;
import com.qf.j1902.pojo.ShIMing;
import com.qf.j1902.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 86181 on 2019/5/27.
 */
public interface UserService {
    //权限
    public void xiugaiperm(@Param("id") int id,@Param("name") String name,@Param("permission") String permission);
    public List<String> findmenuName(String name);
    public List<Perms> findAllPerms();
    public List<String>findNameByid(int id);
    public void delperm(int id);
    //
    //实名认证修改方法
    public void xiuGaiShiM(@Param("name") String name,@Param("renzheng") String renzheng);
    //根据IDcard查名字
    public String findNameByID(String name);
    //根据idcard修改删除
    public void delshiming(@Param("idcard") String idcard);
    //根据id修改eamil
    public void xiugaiemail(@Param("idcard") String idcard,@Param("uemail") String uemail);
    public void addUser(User user);
    public User findOne(String sort);
    public List<User> findAll();
    public User findOneById(int id);
    public User findOneByName(String name);
    public void delUser(int id);
    public void addTrueUser(ShIMing shIMing);
    public void addTrueUser2(ShIMing shIMing);
    public void addTrueUser3(ShIMing shIMing);
    public void xiuGai(@Param("idcard") String idcard, @Param("Auditstate") String Auditstate);
    public void upUser(@Param("name") String name, @Param("email") String email, @Param("other") String other, @Param("id") int id);
    public List<ShIMing> getAllShiM();
    public void xiuGaiImg(@Param("idcard") String idcard, @Param("img") String img);
    public ShIMing findShiMingOne(int id);
    public ShIMing findOneshimingByidcard(String idcard);
    public ShIMing findOneshimingBytruename(String truename);
    public void xiugaibyidcard(@Param("idcard") String idcard, @Param("Auditstate") String Auditstate);
}
