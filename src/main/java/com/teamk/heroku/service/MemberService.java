package com.teamk.heroku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.repository.MemberRepository;

@Transactional
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Transactional(readOnly=true)
	public Member findOne(Long id){
		return memberRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Member findByMemberEmail(String email) {
		return memberRepository.findByEmail(email);
	}
	
	public Member join(Member member){
		return memberRepository.save(member);
	}

}
