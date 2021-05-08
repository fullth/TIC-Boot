package com.fullth.tic.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
	public String login(@Valid MemberDTO memberDTO, Errors errors, Model model) {
		if(errors.hasErrors()) {
			// 실패시 입력한 데이터 유지를 위해
			// model.addAttribute("member", new MemberDTO());
			
			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = memberService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
				System.out.println(validatorResult.get(key));
			}
			System.out.println("로그인 실패 ");
			
			return "member/signupForm";
		}
		model.addAttribute("member", new MemberDTO());
		return "member/loginForm";
	}
	
	@GetMapping("/member/signup")
	public String signupForm() {
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
