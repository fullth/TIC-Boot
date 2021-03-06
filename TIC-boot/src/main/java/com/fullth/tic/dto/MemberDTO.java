package com.fullth.tic.dto;

import javax.validation.constraints.NotBlank;

import com.fullth.tic.domain.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {
	private Long id;
	
	@NotBlank(message ="ID Required")
	private String username;

	@NotBlank(message ="PW Required")
	private String password;
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.username(username)
				.password(password)
				.build();
	}
	
	@Builder
	public MemberDTO(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}
