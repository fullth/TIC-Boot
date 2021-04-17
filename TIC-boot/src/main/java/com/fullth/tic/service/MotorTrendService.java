package com.fullth.tic.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorTrendService {

	@Autowired
	PropReader propReader;

	/**
	 * 
	 * @param keyword
	 * @return rtnObj
	 */
	public Map<String, Object> getMotorTrend(String keyword) {

		List<String> propList = propReader.propRead();

		String clientId = propList.get(0);
		String clientSecret = propList.get(1);
		String apiURL = propList.get(2);

		StringBuffer response = new StringBuffer();
		String parseResponse = null;

		Map<String, Object> rtnObj = new HashMap<>();

		try {
			String search = URLEncoder.encode(keyword, "UTF-8");
			apiURL += "?query=" + search + "&display=3";
			URL url = new URL(apiURL);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			int responseCode = con.getResponseCode();

			BufferedReader br;

			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			while ((parseResponse = br.readLine()) != null) {
				response.append(parseResponse);
			}

			System.out.println(response.toString());

			JSONParser parser = new JSONParser();
			JSONObject result = (JSONObject) parser.parse(response.toString());

			JSONArray items = (JSONArray) result.get("items");
			List<Map<String, Object>> itemList = new ArrayList<>();

			String[] fields = { "title", "link", "description" };

			for (int i = 0; i < items.size(); i++) {
				JSONObject item = (JSONObject) items.get(i);
				Map<String, Object> itemMap = new HashMap<>();
				for (String field : fields) {
					itemMap.put(field, item.get(field));
				}
				itemList.add(itemMap);
			}
			rtnObj.put("result", itemList);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rtnObj;
	}
}
// TODO Method refactoring.