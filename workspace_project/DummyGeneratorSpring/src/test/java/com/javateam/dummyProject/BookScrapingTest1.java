package com.javateam.dummyProject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
       "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
       "file:src/main/webapp/WEB-INF/spring/root-context.xml"
     })
@WebAppConfiguration
public class BookScrapingTest1 {
	
	@Test
	public void bookScrap() throws IOException {
		
		String bookSite = "http://www.yes24.com/24/Category/Display/001001019001";
		Document doc = Jsoup.connect(bookSite).get();
		
		log.info("카테고리 도서수 : " + doc.select("li div.goods_info div.goods_name a[href]").size());
		log.info("카테고리 첫째 도서 번호 : " + doc.select("li div.goods_info div.goods_name a[href]").attr("href").split("/")[3]);
		// log.info("카테고리 첫째 도서 제목 : " + doc.select("li div.goods_info div.goods_name a[href]").get(0).text());
		
		log.info("-------------------------------");
		
		int len = doc.select("li div.goods_info div.goods_name a[href]").size();
		
		for (int i=0; i<len; i+=2) {
			log.info("카테고리 {} 도서 번호 : {}", i, doc.select("li div.goods_info div.goods_name a[href]").get(i).attr("href").split("/")[3]);
		} //
	} //

}