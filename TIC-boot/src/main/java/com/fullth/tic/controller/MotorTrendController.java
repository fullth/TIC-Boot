package com.fullth.tic.controller;

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
		
		// API test.
		motorTrendService.getMotorTrend();
		
		return "MotorTrend";
	}
}
