/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Employee;
import com.exavalu.utils.JDBCConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Avijit Chattopadhyay
 */
public class EmployeeService {

    public static ArrayList getAllEmployees() {
        ArrayList empList = new ArrayList();
        String sql = "SELECT * FROM employees e join departments d join roles r where e.departmentId = d.departmentId and e.roleId = r.roleId and isDeleted=0 order by employeeId ";
        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhoneNo(rs.getString("phoneNo"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));
                emp.setSpecialAllowance(rs.getDouble("specialAllowance"));
                empList.add(emp);
            }
        } catch (SQLException ex) {
        }
        System.err.println("Total rows Returned:" + empList.size());

        return empList;
    }

    public static Employee getEmployee(int employeeId) {
        Employee emp = new Employee();
        try {
            Connection con = JDBCConnectionManager.getConnection();
            String sql = "SELECT * FROM employees e join departments d join roles r where e.departmentId = d.departmentId and e.roleId = r.roleId and isDeleted=0 and e.employeeId = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                emp.setEmployeeId(employeeId);
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhoneNo(rs.getString("phoneNo"));
                emp.setAddress(rs.getString("address"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentId(rs.getInt("departmentId"));
                emp.setRoleId(rs.getInt("roleId"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));
                emp.setSpecialAllowance(rs.getDouble("specialAllowance"));
            }

        } catch (SQLException ex) {
        }
        return emp;
    }

    public static boolean addEmployee(Employee emp) {
        boolean result = false;
        try (Connection con = JDBCConnectionManager.getConnection()) {

            String sql = "INSERT INTO employees ( firstName, lastName, phoneNo, address, gender, age, departmentId, roleId, basicSalary, carAllowance, specialAllowance)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhoneNo());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setInt(6, emp.getAge());
            preparedStatement.setInt(7, emp.getDepartmentId());
            preparedStatement.setInt(8, emp.getRoleId());
            preparedStatement.setDouble(9, emp.getBasicSalary());
            preparedStatement.setDouble(10, emp.getCarAllowance());
            preparedStatement.setDouble(11, emp.getSpecialAllowance());

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    public static boolean updateEmployee(Employee emp, int employeeId) {

        boolean result = false;
        try (Connection con = JDBCConnectionManager.getConnection()) {

            String sql = "UPDATE employees SET firstName = ? , lastName = ? , phoneNo = ? , address = ?, gender = ? , age = ? , basicSalary = ?,carAllowance = ?, specialAllowance = ?, departmentId = ?, roleId =? WHERE employeeId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, emp.getFirstName());
            preparedStatement.setString(2, emp.getLastName());
            preparedStatement.setString(3, emp.getPhoneNo());
            preparedStatement.setString(4, emp.getAddress());
            preparedStatement.setString(5, emp.getGender());
            preparedStatement.setInt(6, emp.getAge());
            preparedStatement.setDouble(7, emp.getBasicSalary());
            preparedStatement.setDouble(8, emp.getCarAllowance());
            preparedStatement.setDouble(9, emp.getSpecialAllowance());
            preparedStatement.setInt(10, emp.getDepartmentId());
            preparedStatement.setInt(11, emp.getRoleId());

            preparedStatement.setInt(12, employeeId);

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    public static boolean deleteEmployee(int employeeId) {
        boolean result = false;
        try (Connection con = JDBCConnectionManager.getConnection()) {

            String sql = "Update employees set isDeleted=1 WHERE employeeId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);

            int row = preparedStatement.executeUpdate();

            if (row == 1) {
                result = true;
            }

        } catch (SQLException ex) {
        }
        return result;
    }

    public static ArrayList searchEmployee(String firstName, String lastName, String gender, String departmentName, String roleName) {
        ArrayList emps = new ArrayList();

        try (Connection con = JDBCConnectionManager.getConnection()) {

            String sql = "SELECT * from employees e join departments d join roles r where e.departmentId = d.departmentId and e.roleId = r.roleId and isDeleted=0 and e.firstName like ? and e.lastName like ? and e.gender like ? and d.departmentName like ? and r.roleName like ?";

            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, firstName + "%");
            preparedStatement.setString(2, lastName + "%");
            preparedStatement.setString(3, gender + "%");
            preparedStatement.setString(4, departmentName + "%");
            preparedStatement.setString(5, roleName + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setAddress(rs.getString("address"));
                emp.setEmployeeId(rs.getInt("employeeId"));
                emp.setFirstName(rs.getString("firstName"));
                emp.setLastName(rs.getString("lastName"));
                emp.setPhoneNo(rs.getString("phoneNo"));
                emp.setGender(rs.getString("gender"));
                emp.setAge(rs.getInt("age"));
                emp.setDepartmentName(rs.getString("departmentName"));
                emp.setRoleName(rs.getString("roleName"));
                emp.setBasicSalary(rs.getDouble("basicSalary"));
                emp.setCarAllowance(rs.getDouble("carAllowance"));
                emp.setSpecialAllowance(rs.getDouble("specialAllowance"));

                emps.add(emp);

            }

        } catch (SQLException ex) {
        }
        return emps;
    }
}
