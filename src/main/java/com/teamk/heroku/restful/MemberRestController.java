package com.teamk.heroku.restful;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamk.heroku.domain.Authority;
import com.teamk.heroku.domain.Member;
import com.teamk.heroku.service.AuthorityService;
import com.teamk.heroku.service.MemberService;

@RestController
@RequestMapping("/rest/member")
public class MemberRestController {

	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("email") String email, @RequestParam("password") String password){
		System.err.println(email+" 가 로그인을 시도합니다.");
		Member member = memberService.findByMemberEmail(email);
		
		if(member == null)
			return new ResponseEntity<String>("해당 사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
		
		if(!passwordEncoder.matches(password, member.getPassword()))
			return new ResponseEntity<String>("비밀번호가 틀립니다.", HttpStatus.BAD_REQUEST);
		
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities()));
		return new ResponseEntity<String>("안녕하세요. "+member.getName()+"님.", HttpStatus.OK);
	}
	
	@PostMapping("/join")
	public ResponseEntity<?> join(@Valid Member member, BindingResult result){
		if(result.hasErrors()){
			for(ObjectError e : result.getAllErrors()){
				System.out.println(e.toString());
			}
			return new ResponseEntity<String>("에러가 발생하였습니다.", HttpStatus.BAD_REQUEST);
		}
		
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		Set<Authority> authorities = member.getAuthorities();
		authorities.add(authorityService.findByAuthority("USER"));
		
		memberService.join(member);
		
		return new ResponseEntity<String>("회원가입이 되었습니다.", HttpStatus.OK);
	}
	
}
