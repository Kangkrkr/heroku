package com.teamk.heroku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.repository.MemberRepository;

@Transactional
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional(readOnly=true)
	@Override
	public Member findByMemberEmail(String email) {
		return memberRepository.findByEmail(email);
	}
	
	@Override
	public Member join(Member member){
		return memberRepository.save(member);
	}

}
