/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.External;
import com.exavalu.utils.JDBCConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Biswajit
 */
public class ExternalService {
    static Logger logger = Logger.getLogger(ExternalService.class.getName());
    
    public static ArrayList getData() throws ParseException{
        
        //String result = null;
        System.out.println("GET DATA");
        ArrayList<External> externals = new ArrayList();
         try {

		URL url = new URL("https://jsonplaceholder.typicode.com/todos");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
                else{
                    
                    String inline = "";
                    try (Scanner scanner = new Scanner(url.openStream())) {
                        while(scanner.hasNext()){
                            inline += scanner.nextLine();
                        }
                    }
                    JSONParser parse = new JSONParser();
                    JSONArray jsonArray = (JSONArray)parse.parse(inline);
                    //JSONArray jsonArray = data_obj.getJSONArra("languages");
                    
                    for(int i = 0; i<conn.getResponseCode(); i++){
                        
                        External external = new External();
                        JSONObject obj = (JSONObject)jsonArray.get(i);
                        String id = obj.get("id").toString();
                        String userId = obj.get("userId").toString();
                        String title = obj.get("title").toString();
                        String completed = obj.get("completed").toString();
                        
                        
                        
                        external.setId(id);
                        external.setUserId(userId);
                        external.setTitle(title);
                        external.setCompleted(completed);
                        
                        
                        externals.add(external);
                        
                    }
                    
                    System.out.println("Size of User List"+externals.size());
                }

	  } catch (MalformedURLException ex) {

		logger.error(ex.getMessage()+LocalDateTime.now());

	  } catch (IOException ex) {

		logger.error(ex.getMessage()+LocalDateTime.now());

	  }
         return externals;
	}

    public static boolean insertDataInDB(ArrayList externals) {
        boolean result = false;
        try (Connection con = JDBCConnectionManager.getConnection()){
            String sql = "INSERT INTO todo(id,userId,title,completed)" +"VALUES(?,?,?,?)";

            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            con.setAutoCommit(false);
            //Iterator itr = apiUsers.iterator();
            //int i = apiUsers.size();
            Iterator itr = externals.iterator();
            while(itr.hasNext()){
                
                External external = (External) itr.next();
                preparedStatement.setString(1,external.getId());
                preparedStatement.setString(2, external.getUserId());
                preparedStatement.setString(3, external.getTitle());
                preparedStatement.setString(4, external.getCompleted());
                
                preparedStatement.addBatch();
            }
            int[] count = preparedStatement.executeBatch();
            con.commit();

                if (count.length >= 1) {
                    result = true;
                    System.out.println(count.length);
                }
                
                con.close();
                
        } catch (SQLException ex) {
            logger.error(ex.getMessage()+LocalDateTime.now());
        }
        
        return result;
  
    }
}