package com.teamk.heroku.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name="message_box")
@Getter @Setter
@NoArgsConstructor
public class MessageBox {
	
	@Id @GeneratedValue
	private Long id;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="messageBox", orphanRemoval=true)
	@JsonBackReference
	private List<Message> messages = new ArrayList<>();
}
