//package com.teamk.heroku.component;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.RestTemplate;
//
//@Component
//public class PingChecker {
//
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}
//	
//	@Scheduled(fixedDelay = 1000 * 60 * 5)
//	public void sendPing(){
//		try{
//			long start = System.currentTimeMillis();
//			ResponseEntity<String> status = restTemplate.getForEntity("https://my-socket-chat.herokuapp.com/", String.class);
//			long end = System.currentTimeMillis();
//			
//			if(status.getStatusCode().is2xxSuccessful())
//				System.err.println("Ping - OK , Loading time : " + ((end - start)) +"ms");
//		}catch(HttpServerErrorException e){
//			System.out.println(e.getStatusText());
//		}
//	}
//}
