package com.teamk.heroku.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.service.MemberService;

@Transactional
@Service
@EnableCaching(proxyTargetClass=true)
public class LoginUserDetaisService implements UserDetailsService {

	@Autowired
	private MemberService memberService;

	// 해당 콜백 메소드는 스프링 시큐리티에서 로그인 체크를 진행할 때 호출된다.
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = memberService.findByMemberEmail(email);
		return new LoginUserDetails(member);
	}
}
