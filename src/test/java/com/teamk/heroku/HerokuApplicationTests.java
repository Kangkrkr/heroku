package com.teamk.heroku;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.teamk.heroku.domain.AbstractItem;
import com.teamk.heroku.domain.ImageItem;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.domain.Post;
import com.teamk.heroku.domain.StringItem;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HerokuApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@LocalServerPort
	private int port;
	
	/*
	@Test
	public void testGetAllChat() throws Exception{
		String uri = "http://localhost:"+port+"/rest/chat";
		
		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
		mockMvc.perform(get(uri)).andExpect(content().string(containsString("kangkrkr")));
	}
	*/
	
	@Transactional
	@Test
	public void test(){
		Session session = getCurrentSession();
		if(!session.isConnected()){
			System.out.println("세션이 닫혀있습니다...");
			return;
		}
		
		Member member = getMemberById(session, 1l);
		List<Post> posts = getPostsByMember(session, member);
		printAllPostByMember(posts);
		
		System.out.println("테스트끝~");
	}

	private Member getMemberById(Session session, Long id) {
		// 주키로 멤버를 가져옵니다.
		Member member = (Member)session.createCriteria(Member.class)
			.add(Restrictions.idEq(id))
			.uniqueResult();
			
		return member;
	}
	
	@SuppressWarnings("unchecked")
	private List<Post> getPostsByMember(Session session, Member member) {
		// 해당 멤버가 쓴 포스트들을 모두 가져옴.
		List<Post> posts = 
				session.createCriteria(Post.class)
					.add(Restrictions.eq("member", member))
					.list();
		return posts;
	}
	
	private void printAllPostByMember(List<Post> posts) {
		for(Post p : posts){
			System.out.println("제목 : " + p.getTitle());
			for(AbstractItem i : p.getItems()){
				if(i instanceof StringItem){
					StringItem stringItem = (StringItem)i;
					System.out.println(stringItem.getContent());
				}
				else if(i instanceof ImageItem){
					ImageItem imageItem = (ImageItem)i;
					System.out.println(imageItem.getImage_path());
				}
			}
			System.out.println("------------------------------------");
		}
	}

	public Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
}
