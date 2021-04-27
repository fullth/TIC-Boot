package com.fullth.tic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fullth.tic.dto.MemberDTO;
import com.fullth.tic.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/member/login")
	public String login() {
		return "member/loginForm";
	}
	
	@GetMapping("/member/signup")
	public String signupForm(Model model) {
		
		model.addAttribute("member", new MemberDTO());
		
		return "member/signupForm";
	}
	
	@PostMapping("/member/signup")
	public String signup(MemberDTO memberDTO) {
		memberService.signUp(memberDTO);
		
		return "redirect:/";
	}
	
	@GetMapping("/member/failLogin")
	public String loginFail() {
		return "member/failLogin";
	}
}
