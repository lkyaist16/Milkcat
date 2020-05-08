package com.part6.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public void addUser(User user) throws SQLException {
        Connection connection = DBUtil.getConnection();

        String sql = "INSERT INTO user(login_name, password, age, email) VALUES (?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getLoginName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getAge());
        preparedStatement.setString(4, user.getEmail());

        preparedStatement.execute();
    }

    public User selectById(Integer id) throws SQLException {
        Connection connection = DBUtil.getConnection();

        String sql = "SELECT * FROM user where id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, id);

        ResultSet rs = preparedStatement.executeQuery();

        User user = new User();

        while (rs.next()) {
            user.setId(rs.getInt("id"))
                    .setAge(rs.getInt("age"))
                    .setLoginName(rs.getString("login_name"))
                    .setEmail(rs.getString("email"))
                    .setPassword(rs.getString("password"));
        }

        return user;
    }

    //删除

    //更新
}
