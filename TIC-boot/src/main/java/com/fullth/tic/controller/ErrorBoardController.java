package com.fullth.tic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullth.tic.domain.ErrBoard;
import com.fullth.tic.service.ErrBoardService;

@Controller
public class ErrorBoardController {
	
	@Autowired
	ErrBoardService errBoardService;
	
	@GetMapping("/errBoard")
	public String errBoard(Model model) {
				
		return "errBoard";
	}
	
	@GetMapping("/test")
	public String test(Model model) {
				
		model.addAttribute("boards", errBoardService.selectErrList());
		
		return "test";
	}
	
	@PostMapping("/insertErr")
	public String insertErr(@RequestParam("errTitle") String title
						, @RequestParam("errContents") String contents
					    , Model model) {
		
		ErrBoard err = new ErrBoard();
		err.setTitle(title);
		err.setContents(contents);
		
		errBoardService.insertErr(err);
		
		return "redirect:/errBoard";
	}
}
