package com.fullth.tic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fullth.tic.service.MemberService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	AuthFailureHandler AuthFailureHandler;
	
	@Autowired
	AuthProvider authProvider;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 인증을 무시할 디렉토리를 지정(=항상 통과) 
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**");
	}
	
	// HttpSecurity를 통해 HTTP요청에 대한 웹 기반 보안을 구성 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// authorizeRequests
		http.authorizeRequests()
			.antMatchers("/**")
			.permitAll();
		
		// formLogin
		http.formLogin()
			.loginPage("/member/login") 		// .loginPage를 설정하지 않고 /login에 접속하면 기본제공 레이아웃이 뜬다.
			.failureHandler(AuthFailureHandler) // 커스텀 로그인보다 앞에 작성하게되면 기본제공 레이아웃을 사용하게 된다.
			.defaultSuccessUrl("/")
			.permitAll();
			
		// logout
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true);
			
		// exceptionHandling
		http.exceptionHandling()
			.accessDeniedPage("/403Page");			
		
		// authenticationProvider
		http.authenticationProvider(authProvider);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
}
