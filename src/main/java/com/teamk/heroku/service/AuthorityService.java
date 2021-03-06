package com.teamk.heroku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Authority;
import com.teamk.heroku.repository.AuthorityRepository;

@Transactional
@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Transactional(readOnly=true)
	public Authority findByAuthority(String authority) {
		return authorityRepository.findByAuthority(authority);
	}
	
}
