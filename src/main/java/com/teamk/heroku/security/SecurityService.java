package com.teamk.heroku.security;

import org.springframework.security.core.Authentication;

import com.teamk.heroku.domain.Member;

public interface SecurityService {
	public Member getCurrentMember();
	public Member getCurrentMember(Authentication auth);
}
