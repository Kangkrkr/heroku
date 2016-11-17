package com.teamk.heroku.restful;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AjaxResponse<T> {
	private T body;
	private Date createdTime;
	private HttpStatus status;
	
	public AjaxResponse(T message){
		this.body = message;
		this.createdTime = new Date();
		this.status = HttpStatus.OK;
	}
	
	public static <T> AjaxResponse<T> ok(){
		AjaxResponse<T> ar = new AjaxResponse<>();
		ar.status = HttpStatus.OK;
		
		return ar;
	}
}
