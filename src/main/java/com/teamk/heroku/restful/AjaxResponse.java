package com.teamk.heroku.restful;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AjaxResponse<T> {
	@JsonProperty
	private T body;
	
	@JsonIgnore
	private HttpStatus status;
	
	public AjaxResponse(T message){
		this.body = message;
		this.status = HttpStatus.OK;
	}
	
	public static <T> AjaxResponse<T> ok(){
		AjaxResponse<T> ar = new AjaxResponse<>();
		ar.status = HttpStatus.OK;
		
		return ar;
	}
	
	public AjaxResponse<?> setStatus(HttpStatus status){
		this.status = status;
		return this;
	}
}
