package test;

import Dao.UserDAO;
import Dao.impl.UserDAOImpl;
import pojo.User;

import java.util.Scanner;

public class TestSQL {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        UserDAO userDAO = new UserDAOImpl();

        System.out.println("请输入用户名称:");
        String name = input.next();
        User user = userDAO.getUserByName(name);
        if (user == null) {
            System.out.println("请输入用户密码:");
            int password = input.nextInt();
            System.out.println("是否记住密码:");
            int remeber = input.nextInt();

            user = new User(0,name,password,remeber);
            userDAO.addUser(user);

        }
        System.out.println("添加成功");
     }


}
