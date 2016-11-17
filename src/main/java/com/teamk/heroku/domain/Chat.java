package com.teamk.heroku.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Chat {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, columnDefinition = "varchar(1500)")
	@Size(min=1, max=1000)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date chatDate;
	
	public Chat(String content){
		this.content = content;
		this.chatDate = new Date();
	}
}
