package com.teamk.heroku.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.teamk.heroku.domain.AbstractItem;
import com.teamk.heroku.domain.ImageItem;
import com.teamk.heroku.domain.LineItem;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Post;
import com.teamk.heroku.domain.StringItem;
import com.teamk.heroku.repository.PostRepository;

@Transactional
@Service
public class PostService {

	private static final String IMAGE_PATH = "C:/nginx/root/images/";
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileService fileService;
	
	public List<String> sortItems(MultipartHttpServletRequest req) {
		List<String> nameList = new ArrayList<>();
		
		Enumeration<String> names = req.getParameterNames();
		Iterator<String> it = req.getFileNames();
		
		while(names.hasMoreElements()) nameList.add(names.nextElement());
		while(it.hasNext()) nameList.add(it.next());
			
		Collections.sort(nameList, new Comparator<String>(){
			@Override
			public int compare(String left, String right) {
				return left.split("-")[1].compareTo(right.split("-")[1]);
			}
		});
		
		return nameList;
	}

	public Post findOne(Long id){
		return postRepository.findOne(id);
	}
	
	public long getPostCountByMember(Member member){
		return postRepository.getPostCountByMember(member);
	}
	
	public Post write(MultipartHttpServletRequest req, Member currentMember) throws IOException {
		List<String> items = this.sortItems(req);
		
		String title = req.getParameter(items.get(0));
		Post post = new Post(title, new Date(), currentMember);
		//post.addItem(new StringItem(title, post));
		
		Path storePath = Paths.get(IMAGE_PATH + currentMember.getEmail());
		if(!Files.exists(storePath))
			Files.createDirectory(storePath);
		
		for(int i=1; i<items.size(); i++){
			String name = items.get(i);
			
			if(name.startsWith("image")){
				String filename = fileService.store(req, storePath, name);
				post.addItem(new ImageItem(filename, post));
			}else if(name.startsWith("content")){
				String content = req.getParameter(name);
				post.addItem(new StringItem(content, post));
			}else {
				post.addItem(new LineItem(true, post));
			}
		}

		return postRepository.save(post);
	}

	public void delete(Post post) {
		postRepository.delete(post);
	}

	public List<Post> getAllPostByCurrentMember(Member member) {
		return postRepository.getPostsByMember(member);
	}
	
	public List<Post> getPostsByPage(Member member, int page, int size){
		return postRepository.getPostsByPage(member, page, size);
	}

	public List<AbstractItem> getItemsByPost(Post post) {
		return postRepository.getItemsByPost(post);
	}

}
