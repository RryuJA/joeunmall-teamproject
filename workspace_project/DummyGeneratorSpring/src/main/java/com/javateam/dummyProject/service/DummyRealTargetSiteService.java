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
import java.nio.file.LinkOption;
import java.nio.file.Path;
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
import net.coobird.thumbnailator.Thumbnails;

@Service
@Slf4j
public class DummyRealTargetSiteService {
	
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
	 * 저장 폴더가 있는지 점검하고 없으면 생성
	 *
	 * @param targetImagePath 저장 폴더 ex) C:/joeunmall-teamproject/product-images
	 * @return 저장 폴더 (생성) 여부
	 */
	public boolean createSaveFolder(String targetImagePath) {
		
		log.info("저장 폴더 점검 및 생성");
		
		boolean flag = false;
		
		Path savePath = Paths.get(targetImagePath);

		// 저장 폴더 유무 점검
		// 없으면 생성
		if (Files.exists(savePath, LinkOption.NOFOLLOW_LINKS) == false) {
			
			try {
			
				Files.createDirectory(savePath);
				flag = true;
				
			} catch (IOException e) {
				log.error("폴더 생성 에러 : " + e);
				e.printStackTrace();
				flag = false;
			}
			
		}
		
		return flag;
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
		
		// List<ProductVO> prList = new ArrayList<>();
		// ProductVO productVO;
		// 의류몰 베스트 상품 카테고리
		// https://www.ely-sia.net/index.html
		
		// 대상 카테고리 : 아우터 카테고리 -> 가디건, 자켓/코트 (2개 세부 카테고리에서만 수집)
		// https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X
		
		// 가디건     : https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X&mcode=002
		// 자켓/코트 : https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X&mcode=003
		
		// 가디건
		String siteLink = "https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X&mcode=002";
		Document doc = Jsoup.connect(siteLink).get();
		
		// 사이트 카테고리 page source
		/* -- 원문(source text) : 카테고리 갤러리 페이지 소스 중에서
		   crawl_text : 카테고리_상품고유정보_html내용.txt 
		*/
		
		// 세부 카테고리별 한정 수량 수집 : 50개
		// int prlen = doc.select("div.prd-info").size();
		// int prlen = 50;
		int prlen = 2; // 임시 조치(낱개 test)
		// log.info("상품 수 : {}", prlen);
		
		// 카테고리 보류 개별 상품 고유번호(branduid) 목록
		List<String> branduidList = new ArrayList<>(); 
		
		for (int i=0; i<prlen; i++) {
			
			// 50개의 상품의 고유번호 : branduid 수집 : data-product-code="269672"
			// ex) <span class="crema-product-reviews-count" data-product-code="269672"
			
			String branduid = doc.select("dd.prd-info")
							   	 .get(i)
								 .getElementsByClass("prd-price")
								 .get(0)
								 .getElementsByClass("crema-product-reviews-count")
								 .attr("data-product-code");
			
			log.info("상품 고유번호 (branduid) : " + branduid); 
		
			//////////////////////////////////////////////////////////////////////////////////
			
			// 개별 상품 정보 수집
			// 대상 페이지 : https://www.ely-sia.net/shop/shopdetail.html?branduid=269672
			
			// 사이트 카테고리 page source
			/* -- 원문(source text) : 카테고리 갤러리 페이지 소스 중에서
			   crawl_text : 개별상품_html내용.txt 
			 */
			
			log.info("개별 상품 정보 조회 : " + branduid);
			
			siteLink = "https://www.ely-sia.net/shop/shopdetail.html?branduid="+branduid;
			Document doc2 = Jsoup.connect(siteLink).get();
			
			// 상품명 수집
			String productName = doc2.select("form#form1")
								   	 .get(0)
									 .getElementsByClass("tit-prd") //  <h3 class="tit-prd">
									 .get(0)
									 .html();
			
			productName = productName.substring(0, productName.indexOf("<div>")).trim(); // 불필요 데이터 제거

			log.info("상품명 : " + productName);
			
			///--------------------------------------------------------------------------------
			
			// 상품 단가(정가) 수집
			// <input type="hidden" id="price_wh" name="price_wh" value="48,500" />
			String productPrice = doc2.select("form#form1")
								   	 .get(0)
									 // .getElementById("price_wh")
								   	 .getElementById("regular_price")
									 .val();
									 
			productPrice = productPrice.replaceAll(",", ""); // "," 제거
			
			log.info("상품가(정가) : " + productPrice); 
			
			///--------------------------------------------------------------------------------
			
			// 상품 옵션 : 첫번째  선택 상자
			String productSelect1Option1 = doc2.select("form#form1")
											   	 .get(0)
												 .getElementsByClass("basic_option")
												 .eq(0)
												 .get(0)
												 .getElementsByTag("option")
												 .eq(1)
												 .attr("title");
			
			String productSelect1Option2 = doc2.select("form#form1")
											   	 .get(0)
												 .getElementsByClass("basic_option")
												 .eq(0)
												 .get(0)
												 .getElementsByTag("option")
												 .eq(2)
												 .attr("title");
			
			log.info("선택상자-1 : 상품 옵션-1 : " + productSelect1Option1); 
			log.info("선택상자-1 : 상품 옵션-2 : " + productSelect1Option2);
			
			log.info("///--------------------------------------------------------------------------------");
			
			// 상품 옵션 : 두번째  선택 상자
			String productSelect2Option1 = doc2.select("form#form1")
											   	 .get(0)
												 .getElementsByClass("basic_option")
												 .eq(1)
												 .get(0)
												 .getElementsByTag("option")
												 .eq(1)
												 .attr("title");

			String productSelect2Option2 = doc2.select("form#form1")
											   	 .get(0)
												 .getElementsByClass("basic_option")
												 .eq(1)
												 .get(0)
												 .getElementsByTag("option")
												 .eq(2)
												 .attr("title");
			
			String productSelect2Option3 = doc2.select("form#form1")
											   	 .get(0)
												 .getElementsByClass("basic_option")
												 .eq(1)
												 .get(0)
												 .getElementsByTag("option")
												 .eq(3)
												 .attr("title");
			
			log.info("선택상자-2 : 상품 옵션-1 : " + productSelect2Option1); 
			log.info("선택상자-2 : 상품 옵션-2 : " + productSelect2Option2);
			log.info("선택상자-2 : 상품 옵션-3 : " + productSelect2Option3);
			
			log.info("///--------------------------------------------------------------------------------");
			
			String thumbImageRightFilename = doc2.select("div.thumb")
				   	 					 .get(0)
										 .getElementsByTag("img")
										 .attr("src")
										 .split("/")[3];
			
			thumbImageRightFilename = thumbImageRightFilename.substring(0, thumbImageRightFilename.indexOf("?"));
			
			log.info("썸네일(우측 큰 상품 이미지) (원파일명) : " + thumbImageRightFilename);
			// 우측 큰 파일     format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_thumbnail
			// 이미지 사이즈 : 610 * 686 px
			String siteFixURL = "https://www.ely-sia.net/shopimages/park6594/";
			
			// ex) https://www.ely-sia.net/shopimages/park6594/0540020007112.jpg
			String thumbImageRightFilenameURL = siteFixURL + "/" + thumbImageRightFilename;
			
			log.info("이미지  경로 : " + thumbImageRightFilenameURL);
			
			// String targetImagePath = "C:/joeunmall-teamproject/product-images";
			String targetImagePath = "C:/joeunmall-teamproject";
			
			// 카테고리 폴더 점검 및 생성
			this.createSaveFolder(targetImagePath); // C:/joeunmall-teamproject
			targetImagePath += "/product-images";
			this.createSaveFolder(targetImagePath); // C:/joeunmall-teamproject/product-images
			this.createSaveFolder(targetImagePath + "/04_cardigan"); // C:/joeunmall-teamproject/product-images/04_cardigan
			
			// 이미지 다운로드 복사
			InputStream in = new URL(thumbImageRightFilenameURL).openStream();
			
			// format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_thumbnail
			String saveFileName = "22_04_" + String.format("%03d", i) + "_thumbnail.jpg";
			Files.copy(in, Paths.get(targetImagePath + "/04_cardigan/" + saveFileName), StandardCopyOption.REPLACE_EXISTING);

			// 좌측 작은 썸네일들
			// 가시적 이미지 사이즈 : 125 * 140 px
			// 좌측 작은 파일들 format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_상세이미지순서(1자리)(1~5)
			// ex) 
			// http://park6594.jpg3.kr/2022/1024/so_1024_3_13.jpg
			// http://park6594.jpg3.kr/2022/1024/so_1024_3_14.jpg
			// http://park6594.jpg3.kr/2022/1024/so_1024_3_15.jpg

			String thumbImageLeftFilePathname1 = doc2.select("div.multiThumb")
													 .get(0)
													 .getElementsByTag("img")
													 .eq(0)
													 .attr("src");
			
			log.info("좌측 썸네일 이미지-1 : " + thumbImageLeftFilePathname1);
			
			// 이미지 다운로드 복사(원파일 : 이미지 사이즈 : 610 * 686 px)
			in = new URL(thumbImageLeftFilePathname1).openStream();
			
			// format) 좌측 작은 파일들 format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_상세이미지순서(1자리)(1~5)
			String saveFileName1 = "22_04_" + String.format("%03d", i) + "_1.jpg";
			Files.copy(in, Paths.get(targetImagePath + "/04_cardigan/" + saveFileName1), StandardCopyOption.REPLACE_EXISTING);
			
			///--------------------------------------------------------------------------------
			
			String thumbImageLeftFilePathname2 = doc2.select("div.multiThumb")
													 .get(0)
													 .getElementsByTag("img")
													 .eq(1)
													 .attr("src");
							
			log.info("좌측 썸네일 이미지-2 : " + thumbImageLeftFilePathname2);
			
			in = new URL(thumbImageLeftFilePathname2).openStream();
			
			// format) 좌측 작은 파일들 format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_상세이미지순서(1자리)(1~5)
			String saveFileName2 = "22_04_" + String.format("%03d", i) + "_2.jpg";
			Files.copy(in, Paths.get(targetImagePath + "/04_cardigan/" + saveFileName2), StandardCopyOption.REPLACE_EXISTING);
			
			///--------------------------------------------------------------------------------
			
			String thumbImageLeftFilePathname3 = doc2.select("div.multiThumb")
														.get(0)
														.getElementsByTag("img")
														.eq(2)
														.attr("src");
			
			log.info("좌측 썸네일 이미지-3 : " + thumbImageLeftFilePathname3);
			
			in = new URL(thumbImageLeftFilePathname3).openStream();
			
			// format) 좌측 작은 파일들 format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_상세이미지순서(1자리)(1~5)
			String saveFileName3 = "22_04_" + String.format("%03d", i) + "_3.jpg";
			Files.copy(in, Paths.get(targetImagePath + "/04_cardigan/" + saveFileName3), StandardCopyOption.REPLACE_EXISTING);
			
			log.info("//-----------------------------------------------------------------------");
	
			// 썸네일화 저장(2차 저장) : 이미지 사이즈 : 125 * 140 px
			
			String saveFileNameThumb1 = "22_04_" + String.format("%03d", i) + "_1_thumbnail.jpg";
			
			log.info("save Path : " + targetImagePath + "/04_cardigan/" + saveFileNameThumb1);
			
			Thumbnails.of(new File(targetImagePath + "/04_cardigan/" + saveFileName1))
			 		   .size(125, 140)
			 		   .outputFormat("jpg")
			 		   .toFile(targetImagePath + "/04_cardigan/" + saveFileNameThumb1); 
			
			///--------------------------------------------------------------------------------
			
			String saveFileNameThumb2 = "22_04_" + String.format("%03d", i) + "_2_thumbnail.jpg";
			
			log.info("save Path : " + targetImagePath + "/04_cardigan/" + saveFileNameThumb2);
			
			Thumbnails.of(new File(targetImagePath + "/04_cardigan/" + saveFileName2))
			 		   .size(125, 140)
			 		   .outputFormat("jpg")
			 		   .toFile(targetImagePath + "/04_cardigan/" + saveFileNameThumb2); 	
			
			///--------------------------------------------------------------------------------
			
			String saveFileNameThumb3 = "22_04_" + String.format("%03d", i) + "_3_thumbnail.jpg";
			
			log.info("save Path : " + targetImagePath + "/04_cardigan/" + saveFileNameThumb3);
			
			Thumbnails.of(new File(targetImagePath + "/04_cardigan/" + saveFileName3))
			 		   .size(125, 140)
			 		   .outputFormat("jpg")
			 		   .toFile(targetImagePath + "/04_cardigan/" + saveFileNameThumb3); 
			
			///--------------------------------------------------------------------------------
			
			log.info("///////////////////////////////////////////////////////////////////////////");
		} // for
		
	} //
	
}
