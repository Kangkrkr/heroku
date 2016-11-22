//package com.teamk.heroku;
//
//import static org.hamcrest.CoreMatchers.containsString;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.embedded.LocalServerPort;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.teamk.heroku.service.ChatService;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class HerokuApplicationTests {
//
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@MockBean
//	private ChatService chatService;
//	
//	@LocalServerPort
//	private int port;
//	
//	@Test
//	public void testGetAllChat() throws Exception{
//		String uri = "http://localhost:"+port+"/rest/chat";
//		
//		mockMvc.perform(get(uri)).andDo(print()).andExpect(status().isOk());
//		mockMvc.perform(get(uri)).andExpect(content().string(containsString("kangkrkr")));
//	}
//
//}
