package com.teamk.heroku.security;

import java.security.AccessControlException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.security.LoginUserDetaisService.LoginUserDetails;

public class SecurityServiceImpl implements SecurityService {

	private Member member = null;
	
	@Override
	public Member getCurrentMember() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		
		Authentication auth = ctx.getAuthentication();
		if(auth == null)
			throw new AccessControlException("해당 세션에서 사용자를 찾을 수 없습니다.");
		
		AuthenticationTrustResolver authResolver = new AuthenticationTrustResolverImpl();
		boolean signedupMember = authResolver.isAnonymous(auth);
		
		if(auth != null && signedupMember)
			member = getCurrentMember(auth);
		
		return member;
	}

	@Override
	public Member getCurrentMember(Authentication auth) {
		LoginUserDetails loginUserDetails = null;
		
		if(auth.getPrincipal() instanceof UserDetails)
			loginUserDetails = (LoginUserDetails)auth.getPrincipal();
		else if(auth.getDetails() instanceof UserDetails)
			loginUserDetails = (LoginUserDetails)auth.getDetails();
		else
			throw new AccessDeniedException("인증되지 않은 사용자입니다.");
		
		return loginUserDetails.getMember();
	}

}
