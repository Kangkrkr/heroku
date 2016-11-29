package com.teamk.heroku.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="stringitem")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class StringItem extends AbstractItem {
	
	public StringItem(String content, Post post){
		this.content = content;
		this.setPost(post);
	}

	@Column(columnDefinition="varchar(2000)")
	private String content;
	
}
