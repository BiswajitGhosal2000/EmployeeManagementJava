/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class LoginService {

    public static LoginService loginService = null;

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (loginService == null) {
            return new LoginService();
        } else {
            return loginService;
        }
    }

    public boolean doLogin(User user) {
        boolean success = false;

        String sql = "Select * from users where status=1 and emailAddress=? and password=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmailAddress());
            ps.setString(2, user.getPassword());

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                success = true;
            }

        } catch (SQLException ex) {

        }

        return success;
    }

    public static boolean sendData(User user) throws IOException {
        boolean result = false;
        try (Connection con = JDBCConnectionManager.getConnection()) {
            String sql2 = "INSERT INTO users (emailAddress, password, firstName, lastName) values(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            int row = preparedStatement.executeUpdate();
            System.out.println(row);
            if (row != 0) {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            int errorCode = ex.getErrorCode();
            System.out.println("Error Code =" + errorCode);
            if (errorCode != 1062) {
                result = false;
            }
        }
        return result;
    }

}
