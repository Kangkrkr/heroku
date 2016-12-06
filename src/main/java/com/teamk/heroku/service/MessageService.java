package com.teamk.heroku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Message;
import com.teamk.heroku.repository.MessageRepository;

@Transactional
@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	public Message save(Message message){
		return messageRepository.save(message);
	}
	
	public List<Message> getMessagesByMember(Member current){
		return messageRepository.findMessageByCurrentMember(current);
	}
	
}
