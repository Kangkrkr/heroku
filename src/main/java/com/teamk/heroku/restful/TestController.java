package com.teamk.heroku.restful;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.teamk.heroku.domain.Chat;
import com.teamk.heroku.domain.ImageItem;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Post;
import com.teamk.heroku.domain.StringItem;
import com.teamk.heroku.repository.ItemRepository;
import com.teamk.heroku.repository.MemberRepository;
import com.teamk.heroku.repository.PostRepository;


@RestController
@RequestMapping("/rest/test")
public class TestController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@PostMapping("/restTemplate")
	public List<Chat> test() throws JsonParseException, JsonMappingException, IOException{
		
		ResponseEntity<Object[]> response =
				restTemplate.getForEntity(URI.create("http://localhost:8080/rest/chat"), Object[].class);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Chat> chats = mapper.readValue(mapper.writeValueAsString(response.getBody()), 
				TypeFactory.defaultInstance().constructCollectionLikeType(List.class, Chat.class));
		
		return chats;
	}
	
	@Transactional
	@GetMapping("/hibernate")
	public void hibernateTest(){
		Member member = memberRepository.findOne(2l);
		
		StringItem s1 = new StringItem("문단에 삽입될 글귀들입니다..");
		itemRepository.save(s1);
		ImageItem i1 = new ImageItem("C:/image/img.jpg");
		itemRepository.save(i1);
		
		Post post1 = new Post("제곧내");
		s1.setPost(post1);
		i1.setPost(post1);
		
		post1.addItem(s1);
		post1.addItem(i1);
		post1.setMember(member);
		post1.setDate(new Date());
		postRepository.save(post1);
		
		member.addItem(post1);
	}
	
	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
}
