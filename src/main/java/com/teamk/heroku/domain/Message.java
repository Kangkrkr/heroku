package com.teamk.heroku.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
@JsonIgnoreProperties(value="messageBox")
public class Message {
	
	@Id @GeneratedValue
	private Long id;
	
	@NotNull
	@Column(columnDefinition="varchar(1000)", nullable=false)
	private String content;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name="is_read")
	private boolean isRead;
	
	@ManyToOne
	@JoinColumn(name="messagebox_id")
	@JsonManagedReference
	private MessageBox messageBox;
	
	@OneToOne
	@JoinColumn(name="sender_id")
	private Member sender;
	
	public Message(String content, MessageBox messageBox, Member sender){
		this.content = content;
		this.date = new Date();
		this.messageBox = messageBox;
		this.sender = sender;
	}
}
