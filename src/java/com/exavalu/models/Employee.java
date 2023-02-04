/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.EmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

public class Employee extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String password;
    private String address;
    private int age;
    private String gender;
    private double basicSalary;
    private int roleId;
    private int departmentId;
    private double specialAllowance;
    private double carAllowance;
    private String departmentName;
    private String roleName;

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phone to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the basicSalary
     */
    public double getBasicSalary() {
        return basicSalary;
    }

    /**
     * @param basicSalary the basicSalary to set
     */
    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return the specialAllowance
     */
    public double getSpecialAllowance() {
        return specialAllowance;
    }

    /**
     * @param specialAllowance the specialAllowance to set
     */
    public void setSpecialAllowance(double specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    /**
     * @return the carAllowance
     */
    public double getCarAllowance() {
        return carAllowance;
    }

    /**
     * @param carAllowance the carAllowance to set
     */
    public void setCarAllowance(double carAllowance) {
        this.carAllowance = carAllowance;
    }

    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String addNewEmployee() throws Exception {
        String res = "FAILURE";
        Employee addEmp = new Employee();
        addEmp.setFirstName(firstName);
        addEmp.setLastName(lastName);
        addEmp.setAddress(address);
        addEmp.setPhoneNo(phoneNo);
        addEmp.setGender(gender);
        addEmp.setDepartmentId(departmentId);
        addEmp.setRoleId(roleId);
        addEmp.setAge(age);
        addEmp.setBasicSalary(basicSalary);
        addEmp.setCarAllowance(carAllowance);
        addEmp.setSpecialAllowance(specialAllowance);

        boolean result = EmployeeService.addEmployee(addEmp);
        ArrayList empList = EmployeeService.getAllEmployees();

        if (result) {
            String successMsg = "Employee Added Successfully";
            sessionMap.put("SuccessMsg", successMsg);
            sessionMap.put("EmpList", empList);
            res = "SUCCESS";
        } else {
            String errorMsg = "Employee Added Successfully";
            sessionMap.put("ErrorMsg", errorMsg);
        }
        return res;
    }

    public String editEmployee() throws Exception {
        String res = "FAILURE";
        Employee emp = EmployeeService.getEmployee(employeeId);
        if (employeeId != 0) {
            sessionMap.put("Emp", emp);
            res = "SUCCESS";
        }
        return res;
    }

    public String saveEmployee() throws Exception {
        String res = "FAILURE";
        Employee emp = new Employee();
        emp.setEmployeeId(employeeId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setAddress(address);
        emp.setPhoneNo(phoneNo);
        emp.setDepartmentId(departmentId);
        emp.setRoleId(roleId);
        emp.setAge(age);
        emp.setBasicSalary(basicSalary);
        emp.setCarAllowance(carAllowance);
        emp.setSpecialAllowance(specialAllowance);
        emp.setGender(gender);

        boolean result = EmployeeService.updateEmployee(emp, employeeId);
        ArrayList empList = EmployeeService.getAllEmployees();
        if (result) {
            sessionMap.put("EmpList", empList);
            res = "SUCCESS";
        } else {
            sessionMap.put("Emp", emp);
        }
        return res;
    }

    public String deleteEmployee() throws Exception {
        String res = "FAILURE";
        boolean result = EmployeeService.deleteEmployee(employeeId);
        ArrayList empList = EmployeeService.getAllEmployees();
        if (result) {
            sessionMap.put("EmpList", empList);
            res = "SUCCESS";
        }
        return res;
    }
    public String searchEmployee() throws Exception {
        String res = "FAILURE";
        ArrayList emps = EmployeeService.searchEmployee(firstName,lastName, gender, departmentName, roleName);
        
        if (!emps.isEmpty()) {
            sessionMap.put("Emps", emps);
            res = "SUCCESS";
        }
        return res;
    }
    
}
