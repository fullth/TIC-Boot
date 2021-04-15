package com.fullth.tic.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorTrendService {
	
	@Autowired
	PropReader propReader;
	
	public void getMotorTrend() {
		
		List<String> propList = propReader.propRead();
		
        String clientId = propList.get(0);
		String clientSecret = propList.get(1);
		String apiURL = propList.get(2);
		
		System.out.println("clientId:" + clientId + ", clientSecret:" + clientSecret + ", apiURL:" + apiURL);
				
        try {
            String keyword = URLEncoder.encode("KEYWORD", "UTF-8");
            apiURL += "?query=" + keyword;
            URL url = new URL(apiURL);
            
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            
            int responseCode = con.getResponseCode();
            
            BufferedReader br;
            
            if(responseCode == 200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
			String parseResponse;
			StringBuffer response = new StringBuffer();
			
			while ((parseResponse = br.readLine()) != null) {
				response.append(parseResponse);
				System.out.println(parseResponse);
			}
			// TODO Method refactoring.		
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
