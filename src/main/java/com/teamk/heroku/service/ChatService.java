package com.teamk.heroku.service;

import java.util.List;

import com.teamk.heroku.domain.Chat;

public interface ChatService {
	public List<Chat> findAll();
	public Chat save(Chat chat);
}
