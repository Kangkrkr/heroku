package com.teamk.heroku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.MessageBox;
import com.teamk.heroku.repository.MemberRepository;
import com.teamk.heroku.repository.MessageBoxRepository;

@Transactional
@Service
public class MemberService {

	@Autowired
	private MessageBoxRepository messageBoxRepository;
	
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
		MessageBox created = messageBoxRepository.save(new MessageBox());
		
		member.setMessageBox(created);
		return memberRepository.save(member);
	}

}
