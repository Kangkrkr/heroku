package com.teamk.heroku.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Message;
import com.teamk.heroku.domain.MessageBox;

@Repository
public class MessageExtendedRepository implements MessageRepository {

	@Autowired
	private MessageCrudRepository crud;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Message save(Message message){
		return crud.save(message);
	}
	
	@Override
	public boolean read(Long id) {
		Message message = crud.findOne(id);
		boolean hasRead = message.isRead();
		
		message.setRead(true);
		crud.save(message);
		
		return hasRead;
	}
	
	@Override
	public void delete(Long id) {
		crud.delete(id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findMessageByCurrentMember(Member current) {
		MessageBox messageBox = 
				(MessageBox) getSession().createCriteria(MessageBox.class)
				.add(Restrictions.eq("id", current.getMessageBox().getId()))
				.uniqueResult();
		
		return getSession().createCriteria(Message.class)
				.add(Restrictions.eq("messageBox", messageBox))
				.addOrder(Order.desc("date"))
				.list();
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
}
