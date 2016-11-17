package com.teamk.heroku.restful;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamk.heroku.domain.Chat;
import com.teamk.heroku.service.ChatService;

@RestController
@RequestMapping("/chat/rest")
public class ChatRestController {

	@Autowired
	private ChatService chatService;
	
	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public AjaxResponse<?> getChatList(){
		List<Chat> chatList = chatService.findAll();
		
		return new AjaxResponse<List<Chat>>(chatList);
	}
	
	@RequestMapping(value = {"","/"}, method = RequestMethod.POST)
	public AjaxResponse<?> saveChat(@Valid Chat chat, BindingResult bind){

		if(bind.hasFieldErrors("content"))
			return new AjaxResponse<String>("입력내용이 너무 깁니다.");
		
		chatService.save(chat);
		
		return AjaxResponse.ok();
	}
}
