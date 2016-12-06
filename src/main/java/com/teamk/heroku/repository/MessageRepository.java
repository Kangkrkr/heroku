package com.teamk.heroku.repository;

import java.util.List;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Message;

public interface MessageRepository {
	public Message save(Message message);
	public List<Message> findMessageByCurrentMember(Member current);
}
