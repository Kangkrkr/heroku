package com.teamk.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamk.heroku.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	public Member findByEmail(String email);
}
