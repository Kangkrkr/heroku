package com.teamk.heroku.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamk.heroku.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
	public Authority findByAuthority(String authority);
}
