package com.teamk.heroku.security;

import org.springframework.security.core.userdetails.User;

import com.teamk.heroku.domain.Member;

import lombok.Getter;

@Getter
// User 클래스는 UserDetails 인터페이스의 구현체이다.
class LoginUserDetails extends User {
	private static final long serialVersionUID = -3541063887975984911L;
	private Member member = null;
	
	public LoginUserDetails(Member member) {
		super(member.getEmail(), member.getPassword(), true, true, true, true, member.getAuthorities());
		this.member = member;
	}
}