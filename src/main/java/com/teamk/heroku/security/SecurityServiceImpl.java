package com.teamk.heroku.security;

import org.springframework.security.core.Authentication;

import com.teamk.heroku.domain.Member;

public class SecurityServiceImpl implements SecurityService {

	@Override
	public Member getCurrentMember() {
		return null;
	}

	@Override
	public Member getCurrentMember(Authentication auth) {
		return null;
	}

}
