/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.LoginService;
import com.exavalu.services.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Biswajit
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = LoginService.getInstance().doLogin(this);
        ArrayList empList = EmployeeService.getAllEmployees();
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();

        if (success) {
            System.out.println("returning Success from doLogin method");
            sessionMap.put("User", this);
            sessionMap.put("EmpList", empList);
            sessionMap.put("DeptList", deptList);
            sessionMap.put("RoleList", roleList);
            result = "SUCCESS";
        } else {
            String loginErrorMsg = "Either Email or Password is Wrong!";
            sessionMap.put("LoginErrorMsg", loginErrorMsg);
            System.out.println("returning Failure from doLogin method");
        }
        return result;
    }

    public String doLogOut() {
        sessionMap.clear();
        return "SUCCESS";
    }

    public String addUser() {
        String result = "FAILURE";

        try {
            boolean res = LoginService.sendData(this);
            if (res){
                result = "SUCCESS";
            }
            else{
                String alreadyExist = "Email Id Already Exist";
                sessionMap.put("AlreadyExist", alreadyExist);
            }
        } catch (IOException ex) {
        }
        return result;
    }
}
