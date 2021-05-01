package com.fullth.tic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.fullth.tic.domain.Member;
import com.fullth.tic.dto.MemberDTO;
import com.fullth.tic.mapper.MemberRepository;

@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * <pre>
	 * 1. 개요 : Validation 처리 
	 * 2. 처리내용 :
	 * 		- 
	 * </pre>
	 * @Method Name : 	validateHandling
	 * @history 	:
	 * -------------------------------------------------------------------
	 * 변경일			변경내용
	 * -------------------------------------------------------------------
	 * 2021.05.01	최초 작성
	 * -------------------------------------------------------------------
	 *
	 * @param errors
	 * @return validatorResult 유효성 처리 결과 메세지 
	 * @throws 
	 */
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }
	
	@Transactional
	public Long signUp(MemberDTO memberDTO) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); 
		memberDTO.setPassword(bCryptPasswordEncoder.encode(memberDTO.getPassword()));
		
		return memberRepository.save(memberDTO.toEntity()).getId();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Member> memberWrapper = memberRepository.findByusername(username);
		Member member = memberWrapper.get();
		
		List<GrantedAuthority> authorties = new ArrayList<>();
		
		if("admin".equals(username)) {
			authorties.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
		} else {
			authorties.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
		}
		
		return new User(member.getUsername(), member.getPassword(), authorties);
	}

}
