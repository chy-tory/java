package Dao;

import pojo.User;

import java.util.List;

public interface UserDAO {
    //查询用户列表
    List<User> getUserList();
    // 新增用户
    boolean addUser(User user);
    // 修改用户信息
    boolean updateUser(User user);
    // 根据用户名查找
    User getUserByName(String name);
    // 删除用户记录
    boolean delUser(String name);
}
