package com.fullth.tic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullth.tic.domain.Motor;
import com.fullth.tic.service.impl.MotorTrendServiceImpl;

@Controller
public class MotorTrendController {

	@Autowired
	MotorTrendServiceImpl motorTrendService;
		
	@GetMapping("/Motor")
	public String motorTrend(Model model) {
		
		Map<String, Object> reqTesla = motorTrendService.getMotorTrend("테슬라");

		// TODO 제네릭을 사용하는데 형변환을 해야하는 것은 제네릭을 잘못사용하는 것 같음.
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> items = (List<Map<String, Object>>) reqTesla.get("result");
		
		//for(int i = 0; i < items.size(); i++ )
			//System.out.println(items.get(i).get("title"));
		
		model.addAttribute("items", items);
		
		return "MotorTrend";
	}
	
	@GetMapping("/motorScrap")
	public String motorScrap(@RequestParam("title") String title
						   , @RequestParam("link") String link
						   , Model model) {
		System.out.println(motorTrendService.selectMotorScrapList());
		
		System.out.println(title);
		System.out.println(link);
		
		Motor motor = new Motor();
		
		motor.setNEWS_TITLE(title);
		motor.setNEWS_LINK(link);
		
		motorTrendService.insertMotorScrapList(motor);
		
		return "redirect:/Motor";
	}
}
