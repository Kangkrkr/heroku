package com.teamk.heroku.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler{

	private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
	
	private Set<WebSocketSession> sessions = new HashSet<>();
	private List<String> chatList = new ArrayList<>();
	
	public SocketHandler() { super(); }

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("connection established.");
		if(sessions.add(session)){
			logger.info(session.getRemoteAddress() + "의 session이 성공적으로 저장되었습니다..");
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		if(sessions.remove(session))
			logger.info(session.getRemoteAddress() + "의 session이 성공적으로 제거되었습니다..");
	}
	
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		chatList.add((String)message.getPayload());
		
		for(WebSocketSession wss : sessions){
			if(wss.isOpen())
				wss.sendMessage(message);
		}
	}

	// true시에 반환시 큰 용량의 메시지(partial message)의 수신이 가능해진다.
	@Override
	public boolean supportsPartialMessages() {
		return true;
	}
	
}
