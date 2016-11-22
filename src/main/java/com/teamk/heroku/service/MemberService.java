package com.teamk.heroku.service;

import com.teamk.heroku.domain.Member;

public interface MemberService {
	public Member findByMemberEmail(String email);
	public Member join(Member member);
}
