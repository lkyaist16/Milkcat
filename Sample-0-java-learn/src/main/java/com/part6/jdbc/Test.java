package com.part6.jdbc;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {

        //添加一个用户
        UserDao userDao = new UserDao();

        User user = new User().setLoginName("15577377498").setAge(12).setPassword("1234").setEmail("123@qq.com");

        userDao.addUser(user);

        //根据id查询
        User userQueryById = userDao.selectById(1);

        System.out.println(userQueryById);

    }
}
