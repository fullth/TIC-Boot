package com.fullth.tic.service;

import java.util.ArrayList;
import java.util.List;
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

import com.fullth.tic.domain.Member;
import com.fullth.tic.dto.MemberDTO;
import com.fullth.tic.mapper.MemberRepository;

@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepository;
	
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
