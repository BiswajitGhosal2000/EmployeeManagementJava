/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.ExternalService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Biswajit
 */
public class External extends ActionSupport implements ApplicationAware, SessionAware, Serializable {
    static Logger logger = Logger.getLogger(External.class.getName());

    private String userId;
    private String id;
    private String title;
    private String completed;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the completed
     */
    public String getCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(String completed) {
        this.completed = completed;
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

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    
    public String addExternal() throws ParseException{
        String result = "FAILURE";
        ArrayList externals = ExternalService.getData();
        boolean res = ExternalService.insertDataInDB(externals);
        if (res) {
            String successMgs = "Data Added in the DataBase!!!!";
            sessionMap.put("SuccessMsg", successMgs);
            result = "SUCCESS";
            System.out.println("Returning From Success");
        } else {
            String failureMsg = "Something Went Wrong!!!!";
            sessionMap.put("FailureMsg", failureMsg);
            System.out.println("Returning From Failure");
            logger.error("Couldn't add External Data" + LocalDateTime.now());
        }
        return result;
    }
}
