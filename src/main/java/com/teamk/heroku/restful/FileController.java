package com.teamk.heroku.restful;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//부모단 핸들 url을 지정할때는 반드시 value를 통해서 지정하도록 합시다 ㅠㅠ
@RestController
@RequestMapping(value="/post/upload", method={RequestMethod.POST})
public class FileController {

	// 응답을 안내려주면 정상 요청을 받아서 404 에러가 뜬다.
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> testUpload(MultipartHttpServletRequest req) {
		
		List<String> nameList = new ArrayList<>();
		
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()){
			nameList.add(names.nextElement());
		}
		
		Collections.sort(nameList, new Comparator<String>(){
			@Override
			public int compare(String left, String right) {
				String leftTime = left.split("-")[1];
				String rightTime = right.split("-")[1];
				return leftTime.compareTo(rightTime);
			}
		});
		
		for(String n : nameList)
			System.out.println(n+"="+req.getParameter(n));
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
