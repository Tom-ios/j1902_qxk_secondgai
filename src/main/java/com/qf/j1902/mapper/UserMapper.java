package com.qf.j1902.mapper;

import com.qf.j1902.pojo.Perms;
import com.qf.j1902.pojo.ShIMing;
import com.qf.j1902.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 86181 on 2019/5/27.
 */
@Mapper
public interface UserMapper {
    //权限
    public List<String> findmenuName(String name);
    public List<Perms> findAllPerms();
    public List<String>findNameByid(int id);
    public void xiugaiperm(@Param("id") int id,@Param("name") String name,@Param("permission") String permission);
    public void delperm(int id);
    //public void addquanxian(@Param("id") int roleid,@Param("name") String username,@Param("permission") String qxlist);
    //
    //实名认证修改方法
    public void xiuGaiShiM(@Param("name") String name,@Param("renzheng") String renzheng);
    //根据id修改eamil
    public void xiugaiemail(@Param("idcard") String idcard,@Param("uemail") String uemail);
    public void addUser(User user);
    public User findOne(String sort);
    public User findOneByName(String name);
    public List<User> findAll();
    public User findOneById(int id);
    public void delUser(int id);
    public List<User> MoHufind(String string);
    public void addTrueUser(ShIMing shIMing);
    public void addTrueUser2(ShIMing shIMing);
    public void addTrueUser3(ShIMing shIMing);
    public void xiuGai(@Param("idcard") String idcard, @Param("Auditstate") String Auditstate);
    public List<ShIMing> getAllShiM();
    public void xiuGaiImg(@Param("idcard") String idcard, @Param("img") String img);
    public ShIMing findShiMingOne(int id);
    public ShIMing findOneshimingByidcard(String idcard);
    public ShIMing findOneshimingBytruename(String truename);
    //根据IDcard查名字
    public String findNameByID(String name);
    public void upUser(@Param("name") String name, @Param("email") String email, @Param("other") String other, @Param("id") int id);
    public void xiugaibyidcard(@Param("idcard") String idcard, @Param("Auditstate") String Auditstate);
//根据idcard修改删除
    public void delshiming(@Param("idcard") String idcard);
}
