package com.teamk.heroku.restful;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.teamk.heroku.domain.Chat;


@RestController
@RequestMapping("/rest/test")
public class TestController {

	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping
	public List<Chat> test() throws JsonParseException, JsonMappingException, IOException{
		
		ResponseEntity<Object[]> response =
				restTemplate.getForEntity(URI.create("http://localhost:8080/rest/chat"), Object[].class);
		
		ObjectMapper mapper = new ObjectMapper();
		List<Chat> chats = mapper.readValue(mapper.writeValueAsString(response.getBody()), 
				TypeFactory.defaultInstance().constructCollectionLikeType(List.class, Chat.class));
		
		return chats;
	}
}
