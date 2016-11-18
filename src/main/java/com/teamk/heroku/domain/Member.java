package com.teamk.heroku.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=10, max=30)
	@Column(nullable = false)
	private String email;
	
	@NotNull
	@Size(min=6, max=20)
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@Size(min=2, max=6)
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@Size(min=1, max=10)
	@Column(nullable = false)
	private String nickname;
	
	@ManyToMany
	@JoinTable(name = "member_authority",
			   joinColumns = {@JoinColumn(name="user_id")}, 
			   inverseJoinColumns = {@JoinColumn(name="authority_id")})
	private Set<Authority> authorities = new HashSet<>();
}
