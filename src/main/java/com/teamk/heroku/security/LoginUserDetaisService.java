package com.teamk.heroku.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.service.MemberService;

import lombok.Getter;

@Service
public class LoginUserDetaisService implements UserDetailsService {

	@Autowired
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return new LoginUserDetails(memberService.findByMemberEmail(email));
	}
	
	@Getter
	class LoginUserDetails extends User {
		private Member member = null;
		
		public LoginUserDetails(Member member) {
			super(member.getName(), member.getPassword(), member.getAuthorities());
			this.member = member;
		}
	}
}
