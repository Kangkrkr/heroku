package com.teamk.heroku.restful;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.teamk.heroku.domain.AbstractItem;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Post;
import com.teamk.heroku.security.SecurityService;
import com.teamk.heroku.service.PostService;

//부모단 핸들 url을 지정할때는 반드시 value를 통해서 지정하도록 합시다 ㅠㅠ
@RestController
@RequestMapping(value="/rest/post")
public class PostController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private PostService postService;
	
	// 응답을 안내려주면 정상 요청을 받아서 404 에러가 뜬다.
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public ResponseEntity<?> testUpload(MultipartHttpServletRequest req) throws IOException {

		Member currentMember = securityService.getCurrentMember();
		postService.write(req, currentMember);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 현재 접속 멤버의 게시물들을 가져온다.
	@RequestMapping
	public ResponseEntity<?> getPosts() {
		List<Post> posts = postService.getAllPostByCurrentMember(securityService.getCurrentMember());
		ResponseEntity<?> res = new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
		
		return res;
	}
	
	// 게시물 중 하나를 택해 그 게시물의 아이템들을 모두 가져온다.
	@RequestMapping("/{id}")
	public ResponseEntity<?> getPost(@PathVariable("id") Long id) {
		List<AbstractItem> items = postService.getItemsByPost(postService.findOne(id));
		ResponseEntity<?> res = new ResponseEntity<List<AbstractItem>>(items, HttpStatus.OK);
		
		return res;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> removePost(@PathVariable("id") Long id) {
		postService.delete(postService.findOne(id));
		ResponseEntity<?> res = new ResponseEntity<>(HttpStatus.OK);
		
		return res;
	}
}
