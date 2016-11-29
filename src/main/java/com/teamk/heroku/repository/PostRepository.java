package com.teamk.heroku.repository;

import java.util.List;

import com.teamk.heroku.domain.AbstractItem;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Post;

public interface PostRepository {

	public Post findOne(Long id);
	public Post save(Post post);
	public void delete(Post post);
	public Member getMemberById(Long id);
	public List<Post> getPostsByMember(Member member);
	public List<AbstractItem> getItemsByPost(Post post);
}
