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
@Table(name="imageitem")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ImageItem extends AbstractItem{

	public ImageItem(String path, Post post){
		this.image_path = path;
		this.setPost(post);
	}
	
	@Column
	private String image_path;
	
}
