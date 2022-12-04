package com.joeun.joeunmall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.joeun.joeunmall.service.ProductRegistrationService;

import lombok.extern.slf4j.Slf4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
     "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
     "file:src/main/resources/spring/root-context.xml"
   })
@WebAppConfiguration
@Slf4j
public class ImageTest {
	
	@Autowired 
	ProductRegistrationService productService;

	@Test
	public void test() {
		log.info("productService");
		log.info("imageVo=" + productService.selectProductImageByImageIndex("22_05_049_8"));
	}

}
