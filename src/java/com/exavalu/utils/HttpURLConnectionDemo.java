/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.utils;

import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import org.apache.log4j.Logger;

/**
 *
 * @author Biswajit
 */
public class HttpURLConnectionDemo {
    static Logger logger = Logger.getLogger(HttpURLConnectionDemo.class.getName());

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_URL = "https://jsonplaceholder.typicode.com/todos";

	private static final String POST_URL = "https://jsonplaceholder.typicode.com/";

	private static final String POST_PARAMS ="todos";

//	public static void main(String[] args) throws IOException {
//		sendGET();
//		System.out.println("GET DONE");
//		sendPOST();
//		System.out.println("POST DONE");
//	}

	public static StringBuffer sendGET() throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
                StringBuffer response = new StringBuffer();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
                        
			System.out.println(response.toString());
                        
		} else {
			System.out.println("GET request did not work.");
                        logger.error("GET request did not work." + LocalDateTime.now());
		}
            
                return response;
	}

	private static void sendPOST() throws IOException {
		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} else {
			System.out.println("POST request did not work.");
                        logger.error("POST request did not work." + LocalDateTime.now());
		}
	}

}