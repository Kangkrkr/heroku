package com.teamk.heroku.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Member;

@Transactional 
@Service
public class SecurityService {

	private Member member = null;
	
	public Member getCurrentMember() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		
		Authentication auth = ctx.getAuthentication();
		if(auth == null)
			throw new AccessDeniedException("해당 세션에서 사용자를 찾을 수 없습니다.");
		
//		AuthenticationTrustResolver authResolver = new AuthenticationTrustResolverImpl();
//		boolean signedupMember = authResolver.isAnonymous(auth);
		
		//  && signedupMember
		if(auth != null){
			member = getCurrentMember(auth);
		}
		
		return member;
	}

	public Member getCurrentMember(Authentication auth) {
		Member member = null;
		
		if(auth.getPrincipal() instanceof Member)
			member = (Member)auth.getPrincipal();
		else if(auth.getDetails() instanceof Member)
			member = (Member)auth.getDetails();
		else
			throw new AccessDeniedException("인증되지 않은 사용자입니다.");
		
		return member;
	}

}
