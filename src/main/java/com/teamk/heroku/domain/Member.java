package com.teamk.heroku.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private Long id;
	
	@NotNull
	@Size(min=10, max=30)
	@Column(nullable = false)
	private String email;
	
	@NotNull
	@Size(min=8)
	@Column(nullable = false, columnDefinition="varchar(255)")
	@JsonIgnore
	private String password;
	
	@NotNull
	@Size(min=2, max=6)
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@Size(min=1, max=10)
	@Column(nullable = false)
	private String nickname;
	
	@OneToOne
	@JoinColumn(name="messagebox_id")
	@JsonIgnore
	private MessageBox messageBox;
	
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<Chat> chats = new HashSet<>();

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Post> posts = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "member_authority",
			   joinColumns = {@JoinColumn(name="user_id")}, 
			   inverseJoinColumns = {@JoinColumn(name="authority_id")})
	@JsonIgnore
	private Set<Authority> authorities = new HashSet<>();
	
	public void addItem(Post post){
		if(posts != null && post != null){
			posts.add(post);
		}
	}
}
