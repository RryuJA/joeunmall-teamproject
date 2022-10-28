package com.javateam.dummyProject;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.dummyProject.domain.ProductVO;
import com.javateam.dummyProject.service.DummyService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Slf4j
public class DummySerProductTest {
	
	@Autowired
	DummyService dummySvc;
	
	@Test
	public void test() throws IOException, ClassNotFoundException {
		
		log.info("더미 테스트");
		
		for (ProductVO p : dummySvc.getProductsFromSerFile()) {
			log.info(p +"");
		}
	} //
	
}
