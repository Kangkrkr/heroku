package com.teamk.heroku.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter 
@Setter
@NoArgsConstructor
public class Authority implements GrantedAuthority {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String authority;
	
}
