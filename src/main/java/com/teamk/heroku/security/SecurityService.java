package com.teamk.heroku.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.teamk.heroku.domain.Member;

@Service
public class SecurityService {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);
	private Member member = null;
	
	public Member getCurrentMember() {
		
		// 시큐리티 컨텍스트를 가져온다.
		SecurityContext ctx = SecurityContextHolder.getContext();
		// 컨텍스트에서 현재 인증된 내역을 가져온다.
		Authentication auth = ctx.getAuthentication();
		
		// 인증된 내역이 없다면 로그인 후 인증된 사용자가 없다는 뜻이다.
		if(auth == null)
			throw new AccessDeniedException("해당 세션에서 사용자를 찾을 수 없습니다.");
		else
			// 인증된 내역이 있다면 그것을 기반으로 현재 사용자를 가져온다.
			member = getCurrentMember(auth);
		
		return member;
	}

	public Member getCurrentMember(Authentication auth) {
		Member member = null;
		
		// 현재 인증된 사용자는 auth 객체로부터 꺼내올 수 있다.
		if(auth.getPrincipal() instanceof Member)
			member = (Member)auth.getPrincipal();
		else if(auth.getDetails() instanceof Member)
			member = (Member)auth.getDetails();
		else
			throw new AccessDeniedException("인증되지 않은 사용자입니다.");
		
		return member;
	}
}
