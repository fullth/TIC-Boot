package com.fullth.tic.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
public class Member {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message ="Input ID")
	private String username;
	
	@NotBlank(message ="Input PW")
	private String password;
	
	@Builder
	public Member(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}
