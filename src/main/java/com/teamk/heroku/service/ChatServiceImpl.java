package com.teamk.heroku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.Chat;
import com.teamk.heroku.repository.ChatRepository;

@Transactional
@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}

	@Override
	public Chat save(Chat chat) {
		return chatRepository.save(chat);
	}

}
