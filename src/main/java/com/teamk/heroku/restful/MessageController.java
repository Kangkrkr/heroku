package com.teamk.heroku.restful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Message;
import com.teamk.heroku.security.SecurityService;
import com.teamk.heroku.service.MemberService;
import com.teamk.heroku.service.MessageService;

@RestController
@RequestMapping(value="/rest/message")
public class MessageController {

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping
	public ResponseEntity<?> getMessages(){
		return new ResponseEntity<List<Message>>(messageService.getMessagesByMember(securityService.getCurrentMember()), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> read(@PathVariable("id") Long id){
		return new ResponseEntity<Boolean>(messageService.read(id), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		messageService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value="/send")
	public ResponseEntity<?> send(@RequestParam("targetEmail") String targetEmail, @RequestParam("content") String content){
		try{
			Member member = memberService.findByMemberEmail(targetEmail);
			Message message = new Message(content, member.getMessageBox(), securityService.getCurrentMember());
			message.setRead(false);
			
			messageService.save(message);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>("메세지 전송에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("메세지 전송에 성공하였습니다.", HttpStatus.OK);
	}
	
}
