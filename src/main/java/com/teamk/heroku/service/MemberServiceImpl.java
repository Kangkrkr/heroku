package com.teamk.heroku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public Member findByMemberEmail(String email) {
		return memberRepository.findByEmail(email);
	}

}
