package com.fullth.tic.service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.springframework.stereotype.Service;

@Service
public class PropReader {

	public List<String> propRead() {
			String resource = "API.properties";
	        Properties properties = new Properties();
	        
	        List<String> propList = new ArrayList<>();
	
	        try {
	            Reader reader = Resources.getResourceAsReader(resource);
	            properties.load(reader);
	            // Get Naver API prop.
	            propList.add(properties.getProperty("CLIENT_ID"));
	            propList.add(properties.getProperty("CLIENT_SECRET"));
	            // Get Kakao API prop.
	            // TODO Consider how to provide individual APIs 
	            propList.add(properties.getProperty("KAKAO_JS_KEY"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
			return propList;
		}
}
