package com.teamk.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamk.heroku.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	public Member findByEmail(String email);
}
