/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.District;
import com.exavalu.models.State;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Biswajit
 */
public class UserService {

    static Logger logger = Logger.getLogger(UserService.class.getName());

    public static boolean doLogin(User user) {
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
            logger.error(ex.getMessage() + LocalDateTime.now());
        }
        return success;
    }

    public static boolean sendData(User user) throws IOException {
        boolean result = false;
        try (Connection con = JDBCConnectionManager.getConnection()) {
            String sql2 = "INSERT INTO users (emailAddress, password, firstName, lastName,countryCode,stateCode,districtCode) values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getCountryCode());
            preparedStatement.setString(6, user.getStateCode());
            preparedStatement.setString(7, user.getDistrictCode());
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
            logger.error(ex.getMessage() + LocalDateTime.now());
        }
        return result;
    }

    public static ArrayList getAllCountry() {
        ArrayList countryList = new ArrayList();

        String sql = "Select * from country";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            //System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                country.setCountryCode(rs.getString("countryCode"));
                country.setCountryName(rs.getString("countryName"));
                countryList.add(country);
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage() + "@" + LocalDateTime.now());
        }

        return countryList;
    }

    public static ArrayList getAllState(String countryCode) {
        ArrayList stateList = new ArrayList();

        String sql = "Select * from state where countryCode=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, countryCode);
            //System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                State state = new State();
                state.setStateCode(rs.getString("stateCode"));
                state.setStateName(rs.getString("stateName"));
                state.setCountryCode(rs.getString("countryCode"));
                stateList.add(state);
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage() + LocalDateTime.now());
        }

        return stateList;
    }

    public static ArrayList getAllDistrct(String stateCode) {
        ArrayList distList = new ArrayList();

        String sql = "Select * from district where stateCode=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, stateCode);
            //System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                District dist = new District();
                dist.setDistrictCode(rs.getString("distCode"));
                dist.setDistrictName(rs.getString("distName"));
                dist.setStateCode(rs.getString("stateCode"));
                distList.add(dist);
            }
            con.close();
        } catch (SQLException ex) {
            logger.error(ex.getMessage() + LocalDateTime.now());
        }
        return distList;
    }
}
