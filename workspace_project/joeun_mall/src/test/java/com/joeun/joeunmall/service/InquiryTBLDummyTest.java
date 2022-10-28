package com.joeun.joeunmall.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.joeun.joeunmall.service.*; 

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@ContextConfiguration({"file:src/main/resources/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class InquiryTBLDummyTest {

	@Autowired
	InquiryTBLDummy inquiryDummy;
	
	@Before
	public void setup() {
		log.info("inqCategory 테스트:" + inquiryDummy.inqCategory);
	}
	
	@Test
	public void test() {
		log.info("test");
//		inquiryDummy.makeInquiryAnswer();
		for(int j = 0; j<10; j++) {
			for(int i = 0; i<10; i++) {
//				int num = (int)(Math.random()*2);
//				log.info("랜덤 결과 확인: " + num);
//				inquiryDummy.setInqCategory(num);
				log.info("멤버 필드 랜덤: " + inquiryDummy.inqCategory);
				log.info("결과: " + inquiryDummy.makeInquiryAnswer());
			}
		}
	}

}
