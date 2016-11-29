package com.teamk.heroku.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.teamk.heroku.domain.AbstractItem;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Post;

public interface PostService {

	public List<String> sortItems(MultipartHttpServletRequest req);
	
	public Post findOne(Long id);
	public Post write(MultipartHttpServletRequest req, Member currentMember) throws IOException;
	public void delete(Post post);
	public List<Post> getAllPostByCurrentMember(Member member);
	public List<AbstractItem> getItemsByPost(Post post);
}
