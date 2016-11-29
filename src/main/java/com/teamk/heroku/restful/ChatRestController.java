package com.teamk.heroku.restful;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamk.heroku.domain.Chat;
import com.teamk.heroku.security.SecurityService;
import com.teamk.heroku.service.ChatService;

//부모단 핸들 url을 지정할때는 반드시 value를 통해서 지정하도록 합시다 ㅠㅠ
@RestController
@RequestMapping(value="/rest/chat")
public class ChatRestController {

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SecurityService securityService;
	
	@GetMapping
	public ResponseEntity<?> getChatList(){
		List<Chat> chatList = chatService.findAll();
		
		return new ResponseEntity<List<Chat>>(chatList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> saveChat(@Valid Chat chat, BindingResult bind){

		if(bind.hasFieldErrors("content"))
			return new ResponseEntity<String>("입력내용이 너무 깁니다.", HttpStatus.BAD_REQUEST);
		
		chat.setMember(securityService.getCurrentMember());
		chatService.save(chat);
		
		return ResponseEntity.ok("");
	}
}
