package com.teamk.heroku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamk.heroku.domain.Chat;
import com.teamk.heroku.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}

	@Override
	public Chat save(Chat chat) {
		return chatRepository.save(chat);
	}

}
