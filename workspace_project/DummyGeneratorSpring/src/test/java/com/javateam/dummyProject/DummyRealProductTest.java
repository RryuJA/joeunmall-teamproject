package com.javateam.dummyProject;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javateam.dummyProject.service.DummyRealTargetSiteServiceUpgrade;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
								  "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Slf4j
public class DummyRealProductTest {
	
	@Autowired
	DummyRealTargetSiteServiceUpgrade drtsuSvc;
	
	@Test
	public void test() throws IOException, InterruptedException {
		
		log.info("상품 정보 수집(크롤링) 일괄 작업(batch job) 테스트");
		
		// 샘플 크롤링) 아우터/가디건 카테고리 
		// drtsuSvc.crawlProducts("054", "002", "cardigan");
		
		// 기획서) 01 티셔츠(01_tshirt) - 벤치 사이트) 블라우스 > 블라우스, 셔츠 카테고리 (025 > 001, 002) 
		// 주의) 벤치 사이트에 종류가 많아서 시범적으로 "블라우스(30개 안됨)" + "셔츠(20)"로 한정함 
		// https://www.ely-sia.net/shop/shopbrand.html?xcode=025&mcode=002&type=Y
		drtsuSvc.crawlProducts("01", "025", "001", "tshirt", 30); // 블라우스 : 점검 성공
		drtsuSvc.crawlProducts("01", "025", "002", "tshirt", 20); // 셔츠 : 점검 성공
		
		// 기획서) 02 팬츠/스커트 (02_pants) - 벤치 사이트) 팬츠 > 면바지, 청바지 카테고리 (019 > 001, 002) 
		// 주의) 벤치 사이트에 종류가 많아서 시범적으로 "면바지(50개 안됨)" + "청바지"로 한정함 
		// https://www.ely-sia.net/shop/shopbrand.html?xcode=019&mcode=001&type=Y
		drtsuSvc.crawlProducts("02", "019", "001", "pants", 30); // 면바지 : 점검 성공
		drtsuSvc.crawlProducts("02", "019", "002", "pants", 20); // 청바지 : 점검 성공 
		
		// 기획서) 03 원피스 (03_onepiece) - 벤치 사이트) 원피스/스커트 > 원피스 카테고리 (026 > 001)
		// https://www.ely-sia.net/shop/shopbrand.html?xcode=026&mcode=001&type=Y
		drtsuSvc.crawlProducts("03", "026", "001", "onepiece", 50); // 점겅 성공
		
		// 기획서) 04 니트/가디건 (04_gardigan) - 벤치 사이트) 니트 > 가디건 카테고리 (003 > 006)
		// https://www.ely-sia.net/shop/shopbrand.html?xcode=003&mcode=006&type=Y
		drtsuSvc.crawlProducts("04", "003", "006", "cardigan", 50); // 점검 성공
		
		// 기획서) 05 자켓 (03_jacket) - 벤치 사이트) 아우터 > 자켓/코트 카테고리 (054 > 003)
		// https://www.ely-sia.net/shop/shopbrand.html?xcode=054&mcode=003&type=Y
		drtsuSvc.crawlProducts("05", "054", "003", "jacket", 50); // 점겅 성공
		
		//////////////////////////////////////////////////////////////////////////////////
		
		// ex) 
		// 상품 고유번호(269672), 카테고리 : 아우터 가디건(xcode = 054), 
		// 서브 카테고리(순서) (mcode = 002) 
		// 참고) 카테고리 URL : https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X&mcode=002
		// log.info("개별 상품 상세 정보 : " + drtsuSvc.searchProductContent("267334", "054", "002"));
		
		// drtsuSvc.searchReview("266881");
		
		// log.info("개별 상품 상세 정보 : " + drtsuSvc.searchProductContent("261240", "003", "006"));
		
	} //
	
}
