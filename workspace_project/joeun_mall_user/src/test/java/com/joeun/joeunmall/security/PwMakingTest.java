package com.joeun.joeunmall.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
     "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
     "file:src/main/resources/spring/root-context.xml"
   })
@WebAppConfiguration
@Slf4j
public class PwMakingTest {
	
	@Test
	public void test(){
		log.info("암호생성");
		String pw = "joeun1234";
		BCryptPasswordEncoder bce = new BCryptPasswordEncoder();
		log.info("암호화 pw=" + bce.encode(pw));//$2a$10$PdP6.CsUr2OA2Ntvj1GHreOzjlYimO6l/5criE/JNLfshxc5Mb0FC
	}
	
}
