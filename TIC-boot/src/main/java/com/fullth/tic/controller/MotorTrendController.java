package com.fullth.tic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fullth.tic.service.MotorTrendService;

@Controller
public class MotorTrendController {

	@Autowired
	MotorTrendService motorTrendService;

	@GetMapping("/Motor")
	public String motorTrend() {

		Map<String, Object> reqTesla = motorTrendService.getMotorTrend("KEYWORD");
		List<Map<String, Object>> items = (List<Map<String, Object>>) reqTesla.get("result");
		
		for(int i = 0; i < items.size(); i++ )
			System.out.println(items.get(i).get("title"));
		
		return "MotorTrend";
	}
}
