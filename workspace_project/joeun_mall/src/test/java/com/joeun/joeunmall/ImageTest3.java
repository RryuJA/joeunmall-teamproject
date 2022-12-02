package com.joeun.joeunmall;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.joeun.joeunmall.service.ProductRegistrationService;
import com.joeun.joeunmall.vo.ProductDTO;

import lombok.extern.slf4j.Slf4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
     "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
     "file:src/main/resources/spring/root-context.xml"
   })
@WebAppConfiguration
@Slf4j
public class ImageTest3 {
	
	@Autowired 
	ProductRegistrationService productService;

	@Test
	public void test() {
		
		//최대 5개 이미지 저장(상품 옵션) 
		ProductDTO productDTO = new ProductDTO();
		productDTO.setUploadImageDeleteYn1("Y");
		assertTrue(productDTO.getUploadImageDeleteYn1().equals("Y"));
		
		}

}
