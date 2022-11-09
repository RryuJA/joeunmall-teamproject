package com.javateam.dummyProject.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.javateam.dummyProject.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DummyService {
	
	private String makeProductIndex(int i) {
		
		return "22_" + String.format("%02d", (int)Math.random()*2)  
			   + "_" + String.format("%03d", i); 
	} //
	
	private String makeOrderIndex(int i) {
		
		String result = "22";
		
		int month = 1 + (int)Math.random()*12;
		result += String.format("%02d", month);
		
		int date = 1 + (int)Math.random()*30;
		result += String.format("%02d", date);
		
		return result + "_" + String.format("%03d", i); 
	}

	private String makeName() {
		
		String name = "";
		
		String first[] = {"김","이","박","최","주","임","엄","성","남궁","독고","황","황보","송","오","유","류","윤","장","정","추"};  
        String middle[] = {"","숙","갑","영","순","선","원","우","이","운","성"};  
    	String last[] = {"영","수","희","빈","민","정","순","주","연","영"}; 
    	
    	name = first[(int)Math.floor(Math.random() * first.length)];
		name += middle[(int)Math.floor(Math.random() * middle.length)];
		name += last[(int)Math.floor(Math.random() * last.length)];
		
		return name;
	}
	
	private String makeRegDate() {
		
		String result = "2022-";
		
		int month = 1 + (int)Math.random()*11;
		result += month < 10 ? "0"+month+"-" : month+"-";
		
		int date = 1 + (int)Math.random()*30;
		result += date < 10 ? "0"+month : month+"";
		
		return result;
	}
	
	/**
	 *  크롤링 항목 : 상품명 / 상품 옵션 / 상품단가  / 등록일자
	 *  
	 * @return
	 * @throws IOException 
	 */
	// private List<ProductVO> crawlProducts() {
	public void crawlProducts() throws IOException {
		
		log.info("crawlProducts");
		
		List<ProductVO> prList = new ArrayList<>();
		ProductVO productVO;

		// 의류몰 베스트 상품 카테고리
		String siteLink = "http://www.midasb.co.kr/shop/bestseller.html?xcode=BEST&type=Y";
		Document doc = Jsoup.connect(siteLink).get();
		
		// 사이트 개별 상품 page source
		/* -- 원문(source text) : 
		<div class="prod-body">

			<div>
			<font color="444A61">●</font>
			    <span style=" background-color: #444A61;border:1px solid #c3c3c3;display:none" class="new-color"></span>
			<font color="4D5442">●</font>
			    <span style=" background-color: #4D5442;border:1px solid #c3c3c3;display:none" class="new-color"></span>
			</div>
			
					<div class="prod-icon">
						<span class='MK-product-icons'><img src='http://cdn6-kinx.makeshop.co.kr/shopimages/midasb/prod_icons/15885?1666594164' class='MK-product-icon-3' /><img src='http://cdn6-kinx.makeshop.co.kr/shopimages/midasb/prod_icons/15881?1666594164' class='MK-product-icon-4' /></span>					</div>
					<div class="prod-name"><span class="sold-prodiconz"></span>비치 패치 후드 야상점퍼 [누빔안감]</div>
					<div class="prod-subname">[Free]<br />
			<font color=#c95b5d>*3온스 누빔 안감으로 올겨울 따뜻하게!</font><br />
			*찬바람을 막아주는 소매 시보리<br />
			*뒷슬릿으로 활동성을 높혔어요~<br />
			</div>
			<div class="prod-price">
												<span>111,600원</span>
					</div>
		
				<div class="review-cut">리뷰 (340)</div>
			</div>
			</div>	                                                                            
			
			<div class="normal-item">
				<div class="prod-thumb">
			
			<!-- 200810 ga -->
			<script>	var dogbowl_Url = '/shop/shopdetail.html?branduid=614232&xcode=019&mcode=005&scode=&GfDT=bWZ3Ug%3D%3D'.split("=")[1].split("&")[0];var dogbowl_Price = '22,800'.replace(/[^0-9]/g,'');productListSub.push({"id": dogbowl_Url,"name": '티아 캐시 폴라 니트',"list_name": dogbowl_list_name, "category": dogbowl_category,"quantity": 1,"price": dogbowl_Price});	productListSubClick.push({"id": dogbowl_Url,"name": '티아 캐시 폴라 니트',"list_name": dogbowl_list_name, "category": dogbowl_category,"quantity": 1,"price": dogbowl_Price});</script>
			<!-- 200810 script end -->
			
					<a href="/shop/shopdetail.html?branduid=614232&xcode=019&mcode=005&scode=&GfDT=bWZ3Ug%3D%3D"><img src="http://cdn6-kinx.makeshop.co.kr/shopimages/midasb/0190050004583.gif?1665712257" alt="" title="" class="s_size" /> <img src="http://cdn6-kinx.makeshop.co.kr/shopimages/midasb/0190050004582.jpg?1603270909" alt="" title="" class="m_size" /></a>
				</div>
		*/
		
		log.info("상품명 크롤");
		
		int prlen = doc.select("div.prod-body").size();
		
		log.info("상품 수 : {}", prlen);
		
		for (int i=0; i<prlen; i++) {
		
			String prName = doc.select("div.prod-body")
							   .get(i)
							   .getElementsByClass("prod-name")
							   .html()
							   .split(">")[2];
			
			log.info("상품명 : " + prName);
			
			String prOption = doc.select("div.prod-body")
							     .get(i)
								 .getElementsByClass("prod-subname")
								 .text();
			
			log.info("상품 옵션명 : " + prOption);
			
			log.info("-------------- 번호 : {}", i);
			log.info("가격 정보 갯수 : " + doc.select("div.prod-body")
								 		  .get(i)
								 		  .getElementsByClass("prod-price")
								 		  .get(0)
								 		  .select("span").size());
			
			int priceLen = doc.select("div.prod-body")
					 		  .get(i)
					 		  .getElementsByClass("prod-price")
					 		  .get(0)
					 		  .select("span").size();
			
			int prPrice = 0;
			
			// 주의) 11번째에서 <span><strike>47,400원</strike></span> 이런 형식으로 strike 추가됨
			// 가격이 두개  검출될 경우는 두번째 가격(할인가) 우선 조치
			if (priceLen == 1) { 
			
				prPrice = Integer.parseInt(doc.select("div.prod-body")
						 		  .get(i)
						 		  .getElementsByClass("prod-price")
						 		  .get(0)
						 		  .select("span")
						 		  .text()
						 		  .replaceAll("[원|,]", ""));
			
			} else { // 가격 정보가 2개 일 경우 (정가/할인가) => 할인가 우선
				
				prPrice = Integer.parseInt(doc.select("div.prod-body")
				 		  .get(i)
				 		  .getElementsByClass("prod-price")
				 		  .get(0)
				 		  .select("span")
				 		  .get(1) // 두번째 할인가 선택
				 		  .text()
				 		  .replaceAll("[원|,]", ""));
			}
			
			log.info("상품 가격 : " + prPrice);
			
			String imgThumbURL = doc.select("div.normal-item")
								    .get(i)
								    .getElementsByClass("prod-thumb")
								    .get(0)
								    .select("img")
								    .attr("src");
			
			String imgName = imgThumbURL.split("\\?")[0].split("\\/")[5]; 
								    		
			log.info("이미지  경로 : " + imgThumbURL);
			log.info("이미지  이름 : " + imgName);
			
			String targetImagePath = "D:/lsh/downloads/";
			
			// 이미지 다운로드 복사
			InputStream in = new URL(imgThumbURL).openStream();
			Files.copy(in, Paths.get(targetImagePath + imgName), StandardCopyOption.REPLACE_EXISTING);
			
			////////////////////////////////////////////////////////////////////////////////////////////
			
			productVO = new ProductVO();
			
			// 상품번호			
			productVO.setProductIndex(this.makeProductIndex(i));
			
			// 주문번호
			productVO.setOrderIndex(this.makeOrderIndex(i));
			
			// 주문자명
			productVO.setUserName(this.makeName());
			
			// 상품명 
			productVO.setProductName(prName);
			
			// 상품옵션
			productVO.setProductOption(prOption);
			
			// 상품가격
			productVO.setProductPrice(prPrice);
			
			// 등록일자
			productVO.setProductDate(java.sql.Date.valueOf(this.makeRegDate()));
			
			// ProductVO [productIndex=22_00_009, orderIndex=220101_009, userName=정우빈, 
			// productName=세르 체크 슬림 슬랙스 [기모안감], productOption=[S/M/L] *체크와 함께 클래식을 입다♥ 
			// *라인은 슬림하게! 기모로 포근함 까지 *깔끔하고 멋스러운 히든클로징, 
			// productPrice=52800, productDate=2022-01-01]
			log.info("{}", productVO);
			
			prList.add(productVO);
		
		} // for
		
		// 직렬화
		log.info("직렬화 시작");
		
		String fileLocation = new File("src\\main\\resources\\ser").getAbsolutePath() + "\\PrList.ser";
		
		try (FileOutputStream     fos = new FileOutputStream(fileLocation);
			 BufferedOutputStream bos = new BufferedOutputStream(fos);) 
		{
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(prList);
			
		} catch (IOException e) {
			
			log.error("직렬화 저장 에러 : " + e);
			e.printStackTrace();
		}
		
		log.info("직렬화 종료");
	} //
	
	
	/**
	 * 역직렬화 : ~~.ser => List 치환
	 * 	 
	 * @return 상품 리스트
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public List<ProductVO> getProductsFromSerFile() throws FileNotFoundException {
		
		File file = ResourceUtils.getFile("classpath:ser/PrList.ser");
		List<ProductVO> demoList = null;
		
		try (FileInputStream     fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis);) {
		
			ObjectInputStream in = new ObjectInputStream(bis);
			
			demoList = (List<ProductVO>)in.readObject();
			
		} catch (IOException e) {
			log.error("역직렬화 에러 : " + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error("직렬화 파일 로딩 오류 : " + e);
			e.printStackTrace();
		}
		
		return demoList;
	} //
	
}
