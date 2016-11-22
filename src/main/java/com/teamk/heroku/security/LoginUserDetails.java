package com.teamk.heroku.security;

import org.springframework.security.core.userdetails.User;
import com.teamk.heroku.domain.Member;

import lombok.Getter;

@Getter
class LoginUserDetails extends User {

	private static final long serialVersionUID = -3541063887975984911L;
	private Member member = null;
	
	public LoginUserDetails(Member member) {
		super(member.getName(), member.getPassword(), member.getAuthorities());
		this.member = member;
	}
}