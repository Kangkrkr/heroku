package com.teamk.heroku.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table @Data
@NoArgsConstructor
public class Chat {
	@Id @GeneratedValue
	@JsonProperty
	private Long id;
	
	@JsonProperty
	@Column(nullable = false, columnDefinition = "varchar(1500)")
	@Size(min=1, max=1000)
	private String content;
	
	@JsonProperty
	@Temporal(TemporalType.TIMESTAMP)
	private Date chatDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonManagedReference
	private Member member;
	
	public Chat(String content){
		this.content = content;
		this.chatDate = new Date();
	}
}
