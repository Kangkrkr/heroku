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
@Table(name="lineitem")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class LineItem extends AbstractItem {
	
	public LineItem(boolean isLine, Post post){
		this.is_line = isLine;
		this.setPost(post);
	}
	
	@Column
	private boolean is_line;
	
}
