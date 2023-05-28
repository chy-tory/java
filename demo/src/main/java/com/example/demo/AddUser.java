package com.example.demo;

import Dao.UserDAO;
import Dao.impl.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.User;

import java.io.IOException;

public class AddUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");


        String name = req.getParameter("name");
        String passwordStr = req.getParameter("password");
        Integer password = Integer.getInteger(passwordStr);
        String remeberStr = req.getParameter("remeber");
        Integer remeber = Integer.getInteger(remeberStr);

        UserDAO userDAO = new UserDAOImpl();
        boolean flag = userDAO.addUser(new User(0,name,password,remeber));

        System.out.println(flag ? "添加成功" : "添加失败");
    }
}
