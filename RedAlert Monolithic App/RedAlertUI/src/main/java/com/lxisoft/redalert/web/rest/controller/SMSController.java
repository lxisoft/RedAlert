package com.lxisoft.redalert.web.rest.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;


@Controller
	@RequestMapping("/redAlertuiHistory")
	public class SMSController {
	
		 @RequestMapping("/send")
		public String sendSms() {
			try {
				// Construct data
				String apiKey = "apikey=" + "YacuOjh2n5o-IaPRKveaPloRQ57nOFEMv1lWBmiDF2";
				String message = "&message=" + "This msg from textlocal";
				String sender = "&sender=" + "TXTLCL";
				String numbers = "&numbers=" + "919645068849";
				
				// Send data
				HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
				String data = apiKey + numbers + message + sender;
				System.out.println("data..............."+data);
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				conn.getOutputStream().write(data.getBytes("UTF-8"));
				final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				final StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					stringBuffer.append(line);
				}
				rd.close();
				
				return stringBuffer.toString();
			} catch (Exception e) {
				System.out.println("Error SMS "+e);
				return "Error "+e;
			}
		}
}
		
		
		