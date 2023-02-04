/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Department;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Biswajit
 */
public class DepartmentService {

    public static ArrayList getAllDepartment() {

        ArrayList deptList = new ArrayList();
        try (Connection con = JDBCConnectionManager.getConnection()){
            System.out.println("Connection Established"+con);

            String sql = "Select * from departments";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Department dept = new Department();
                
                dept.setDepartmentId(rs.getInt("departmentId"));
                dept.setDepartmentName(rs.getString("departmentName"));
                
                deptList.add(dept);
                
            }
            con.close();
        } catch (SQLException ex) {
        }
System.out.println("Department List:"+deptList.size());
        return deptList;
    }
}
