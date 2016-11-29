package com.teamk.heroku.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamk.heroku.domain.AbstractItem;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Post;

@Repository
public class PostExtendedRepository implements PostRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PostCrudRepository crud;
	
	
	@Override
	public Post findOne(Long id){
		return crud.findOne(id);
	}
	
	@Override
	public Post save(Post post) {
		return crud.save(post);
	}

	@Override
	public void delete(Post post) {
		crud.delete(post);
	}
	
	@Override
	public Member getMemberById(Long id) {
		// 주키로 멤버를 가져옵니다.
		Member member = (Member)getSession().createCriteria(Member.class)
			.add(Restrictions.idEq(id))
			.uniqueResult();
			
		return member;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Post> getPostsByMember(Member member) {
		// 해당 멤버가 쓴 포스트들을 모두 가져옴.
		return getSession().createCriteria(Post.class)
				.add(Restrictions.eq("member", member))
				.list();
	}
	
	@SuppressWarnings({ "unchecked", "unused"})
	@Override
	public List<AbstractItem> getItemsByPost(Post post){
		return getSession().createCriteria(AbstractItem.class)
			   .add(Restrictions.eq("post", post))
			   .addOrder(Order.asc("id"))
			   .list();
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
}
