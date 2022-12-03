package com.joeun.joeunmall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.joeun.joeunmall.service.ProductRegistrationService;
import com.joeun.joeunmall.vo.ProductImageVO;

import lombok.extern.slf4j.Slf4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
     "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
     "file:src/main/resources/spring/root-context.xml"
   })
@WebAppConfiguration
@Slf4j
public class ImageTest2 {
	
	@Autowired 
	ProductRegistrationService productService;

	@Test
	public void test() {
		
		//최대 5개 이미지 저장(상품 옵션) 
		ProductImageVO productImageVO = new ProductImageVO();

		//저장할 상품 옵션이 있을 때 

			/*productImageVO.setProductDetailImage(fileuploadService.setUploadFileName(productDTO, false, 1));
			productImageVO.setProductIndex(productDTO.getProductIndex());
			// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
			// 미등록 상품일 경우 (최초등록상품) >>null 
			String tempImgNum = productService.selectMaxProductImageIndex(productDTO.getProductIndex());
			tempImgNum = tempImgNum == null ? "0" : tempImgNum.split("_")[3];
			int lastProductIndex = Integer.parseInt(tempImgNum);

			String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
			productImageVO.setProductImageIndex(productImageIndex);
			log.info("productImageIndex");*/

			
			//DB에서 이미지 등록 점검
			if(productService.selectProductImageByImageIndex("22_05_049_1")!=null) {//수정
				log.info("이미지 1 수정");

			}else {//이미지 생성
				log.info("이미지 1 생성");

			}//
		}

}
