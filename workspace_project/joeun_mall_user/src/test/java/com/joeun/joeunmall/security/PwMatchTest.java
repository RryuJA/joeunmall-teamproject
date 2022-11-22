package com.joeun.joeunmall.security;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.joeun.joeunmall.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
     "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
     "file:src/main/resources/spring/root-context.xml"
   })
@WebAppConfiguration
@Slf4j
public class PwMatchTest {
	
	@Autowired UserService userService;
	
	
	@Test
	public void test(){
		log.info("암호 테스트");
		String pw = "#Abcd1234"; 
		BCryptPasswordEncoder bce = new BCryptPasswordEncoder();
		//$2a$10$UTWZ0EQbhXCVFUpjiu42quBdTdkSYJBNU0yaGwejgDsa4PwCyqbUu
		String enPw =  userService.selectUser("2022001abc123").getUserPw();
		log.info("암호화 패쓰워드 =" + enPw);
		assertTrue(bce.matches(pw, enPw));
	}
	
}
