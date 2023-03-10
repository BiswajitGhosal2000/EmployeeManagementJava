/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;
import com.exavalu.services.RoleService;
import com.exavalu.services.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Biswajit
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    static Logger logger = Logger.getLogger(User.class.getName());

    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String countryCode;
    private String stateCode;
    private String districtCode;

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

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the stateCode
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * @param stateCode the stateCode to set
     */
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    /**
     * @return the districtCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * @param districtCode the districtCode to set
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
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

        boolean success = UserService.doLogin(this);
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
            logger.error("Either Email or Password is Wrong." + LocalDateTime.now());
        }
        return result;
    }

    public String doLogOut() {
        sessionMap.clear();
        return "SUCCESS";
    }

    public String preSignup() throws IOException {
        ArrayList stateList;
        ArrayList distList;
        String result = "FAILURE";
        ArrayList countryList = UserService.getAllCountry();
        sessionMap.put("CountryList", countryList);
        if (this.getCountryCode() != null) {
            System.out.println("Country Code: " + this.getCountryCode());
            stateList = UserService.getAllState(this.getCountryCode());
            sessionMap.put("StateList", stateList);
            sessionMap.put("User", this);
            result = "STATELIST";
        }
        if (this.getStateCode() != null) {
            System.out.println("State Code:" + this.getStateCode());
            distList = UserService.getAllDistrct(this.getStateCode());
            sessionMap.put("DistList", distList);
            sessionMap.put("User", this);
            result = "DISTLIST";
        }

        if (this.firstName != null && this.firstName.length() > 0 && this.lastName != null && this.lastName.length() > 0 && this.emailAddress != null && this.emailAddress.length() > 0 && this.password != null && this.password.length() > 0 && this.stateCode != null && this.stateCode.length() > 0 && this.countryCode != null && this.countryCode.length() > 0 && this.districtCode != null && this.districtCode.length() > 0) {
            System.out.println(firstName + lastName + emailAddress + password + this.getStateCode() + this.getCountryCode() + this.getDistrictCode());
            boolean res = UserService.sendData(this);
            if (res) {
                result = "SUCCESS";
                sessionMap.clear();
            } else {
                result = "FAILURE";
                String alreadyExist = "Email Id Already Exist";
                sessionMap.put("AlreadyExist", alreadyExist);
                logger.error("Something Went error in Sign Up    " + LocalDateTime.now());
            }
        }
        return result;
    }
}
