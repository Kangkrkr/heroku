package com.teamk.heroku.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.service.MemberService;

@Transactional
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		request.getSession().setMaxInactiveInterval(60 * 60);

		Member member = memberService.findByMemberEmail(request.getParameter("email"));
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities()));
		
		response.sendRedirect("/home");
	}
}
