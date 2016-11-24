package com.teamk.heroku.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(columnDefinition="varchar(255)")
	private String title;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="member_id")
	@JsonManagedReference
	private Member member;
	
	@OneToMany(mappedBy="post", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonBackReference
	private List<AbstractItem> items = new ArrayList<>();
	
	public Post(String title){
		this.title = title;
	}
	
	public void addItem(AbstractItem item){
		if(items != null && item != null){
			items.add(item);
		}
	}
}
