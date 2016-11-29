package com.teamk.heroku.service;

import com.teamk.heroku.domain.Member;

public interface MemberService {
	public Member findOne(Long id);
	public Member findByMemberEmail(String email);
	public Member join(Member member);
}
