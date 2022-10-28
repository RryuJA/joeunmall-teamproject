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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.javateam.dummyProject.domain.ProductVO;
import com.javateam.dummyProject.domain.ShopProductDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DummyRealTargetSiteServiceUpgrade {
	
	private String makeProductIndex(String cateNum, int i) {
		
		return "22_" + cateNum + "_" + String.format("%03d", i); 
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
	
	public void searchReview(String branduid) throws IOException {
		
		log.info("상품별 리뷰 수집");
		/*
		 // 리뷰 수정 요청
		 function update_review(uid, date) {
		    window.open('/shop/review_update.html?uid=' + uid + '&date=' + date, '', 'width=452,height=400,scrollbars=yes');
		 } 
		 
		 또는 
		 
		 function reviewnotice(uid, dt) {
		    window.open('/shop/reviewview.html?branduid=' + uid + '&date=' + dt, 'pop_review', 'width=450,height=400,scrollbars=yes');
		 }
		 
		 // var dt=new Date();var jsr=dt.getFullYear()+''+(dt.getMonth()+1)+''+dt.getDate()+''+dt.getHours()+''+dt.getMinutes()
		*/
		
		// String reviewSiteLink = "https://www.ely-sia.net/shop/reviewview.html?branduid=" + branduid + "&date=" +
		
		String siteLink = "https://www.ely-sia.net/shop/shopdetail.html?branduid=266881&xcode=054&mcode=002&scode=&type=X&sort=manual&cur_code=054002&GfDT=aWx3Ug%3D%3D";
		Document doc = Jsoup.connect(siteLink).get();
		
		String str = doc.select("span[name=snapreview_upper]")
						.get(0)
						.html();
					   // .get(0)
					   //.getElementsByClass("sf_review_user_write_review")
					   //.html();
		
		log.info("str : " +str);
	} //
	
	/**
	 * 상품 내용 검색 및 수집
	 * 
	 * @param branduid 상품 고유번호
	 * @param cateNum1 메인 카테고리 번호
	 * @param cateNum2 서브 카테고리 번호
	 * @return shopProductDTO 상품 상세 정보 DTO 
	 * 
	 */
	public ShopProductDTO searchProductContent(String branduid, String cateNum, String cateNum2) throws IOException {
		
		log.info("searchProductContent");
		
		// String branduid = "269309"; 
		// String siteLink = "https://www.ely-sia.net/shop/shopdetail.html?branduid="+branduid;
		// 상품 정보 => iframe 처리됨
		/*
		 https://snapfit.co.kr/Resultview_new/renderSnapfitMain/%7B%22sf_isshowsimilar%22%3Atrue%2C%22sf_isshowhistory%22%3Afalse%2C%22sf_get_bodyinfo_from%22%3A1%2C%22sf_is_live%22%3A1%2C%22logoimg%22%3A%22%252F%252Fsnapvi.ecn.cdn.infralab.net%252Fsnapfit%252Fstores%252Fcommon%252Flogo%252Fsnapfit_logo_3.png%22%2C%22logoimg_m%22%3A%22%22%2C%22is_use_history_sizecur_size%22%3A%220%22%2C%22similarskin_rec_type%22%3A%22rank%22%2C%22viewname%22%3A%22f_c_t%22%2C%22categender%22%3A%22f%22%2C%22device_type%22%3A%22pc%22%2C%22itemid%22%3A%22269672%22%2C%22cateid%22%3A%22054002%22%2C%22cat1id%22%3A%22054%22%2C%22cat2id%22%3A%22054002%22%2C%22cat3id%22%3A%22%22%2C%22is_use_order_buylist%22%3A%221%22%2C%22is_use_similarskin_satisfaction_ratio%22%3A%221%22%2C%22is_use_chartpopup%22%3A%220%22%2C%22isupotheruserbuyframe%22%3A%220%22%2C%22float_img_type%22%3A%22%252F%252Fcdn.snapfit.co.kr%252Fcommon%252Fbtn2.png%22%2C%22is_use_rec_item%22%3A%220%22%2C%22domain%22%3A%22ely-sia.net%22%2C%22store_type%22%3A%22makeshop%22%2C%22use_sync%22%3Atrue%2C%22result%22%3A%22success%22%2C%22is_show_main%22%3Atrue%2C%22ac%22%3A%22%22%2C%22sf_store_username%22%3A%22elysia%22%2C%22thumbnail_type%22%3A%221%22%2C%22float_img_use%22%3A%221%22%2C%22fit_version%22%3A%222%22%2C%22draw_target%22%3A%22iframe%22%2C%22main_frame_id%22%3A%22snapfit_resultview_iframe%22%2C%22constant_main_frame_id%22%3A%22snapfit_resultview_iframe%22%2C%22constant_main_popup_frame_id%22%3A%22snapfit_resultview_iframe_popup%22%7D 
		 */
		
		// 상품 정보 그리드가 포함된 페이지 
		String iframeSite = "https://snapfit.co.kr/Resultview_new/renderSnapfitMain/%7B%22sf_isshowsimilar%22%3Atrue%2C%22sf_isshowhistory%22%3Afalse%2C%22sf_get_bodyinfo_from%22%3A1%2C%22sf_is_live%22%3A1%2C%22logoimg%22%3A%22%252F%252Fsnapvi.ecn.cdn.infralab.net%252Fsnapfit%252Fstores%252Fcommon%252Flogo%252Fsnapfit_logo_3.png%22%2C%22logoimg_m%22%3A%22%22%2C%22is_use_history_sizecur_size%22%3A%220%22%2C%22similarskin_rec_type%22%3A%22rank%22%2C%22viewname%22%3A%22f_c_t%22%2C%22categender%22%3A%22f%22%2C%22device_type%22%3A%22pc%22%2C%22itemid%22%3A%22269672%22%2C%22cateid%22%3A%22054002%22%2C%22cat1id%22%3A%22054%22%2C%22cat2id%22%3A%22054002%22%2C%22cat3id%22%3A%22%22%2C%22is_use_order_buylist%22%3A%221%22%2C%22is_use_similarskin_satisfaction_ratio%22%3A%221%22%2C%22is_use_chartpopup%22%3A%220%22%2C%22isupotheruserbuyframe%22%3A%220%22%2C%22float_img_type%22%3A%22%252F%252Fcdn.snapfit.co.kr%252Fcommon%252Fbtn2.png%22%2C%22is_use_rec_item%22%3A%220%22%2C%22domain%22%3A%22ely-sia.net%22%2C%22store_type%22%3A%22makeshop%22%2C%22use_sync%22%3Atrue%2C%22result%22%3A%22success%22%2C%22is_show_main%22%3Atrue%2C%22ac%22%3A%22%22%2C%22sf_store_username%22%3A%22elysia%22%2C%22thumbnail_type%22%3A%221%22%2C%22float_img_use%22%3A%221%22%2C%22fit_version%22%3A%222%22%2C%22draw_target%22%3A%22iframe%22%2C%22main_frame_id%22%3A%22snapfit_resultview_iframe%22%2C%22constant_main_frame_id%22%3A%22snapfit_resultview_iframe%22%2C%22constant_main_popup_frame_id%22%3A%22snapfit_resultview_iframe_popup%22%7D";
		String decodedSite = URLDecoder.decode(iframeSite);
		
		// decoded Site 
		/*
		 https://snapfit.co.kr/Resultview_new/renderSnapfitMain/{"sf_isshowsimilar":true,"sf_isshowhistory":false,"sf_get_bodyinfo_from":1,"sf_is_live":1,"logoimg":"%2F%2Fsnapvi.ecn.cdn.infralab.net%2Fsnapfit%2Fstores%2Fcommon%2Flogo%2Fsnapfit_logo_3.png","logoimg_m":"","is_use_history_sizecur_size":"0","similarskin_rec_type":"rank","viewname":"f_c_t","categender":"f","device_type":"pc","itemid":"269672","cateid":"054002","cat1id":"054","cat2id":"054002","cat3id":"","is_use_order_buylist":"1","is_use_similarskin_satisfaction_ratio":"1","is_use_chartpopup":"0","isupotheruserbuyframe":"0","float_img_type":"%2F%2Fcdn.snapfit.co.kr%2Fcommon%2Fbtn2.png","is_use_rec_item":"0","domain":"ely-sia.net","store_type":"makeshop","use_sync":true,"result":"success","is_show_main":true,"ac":"","sf_store_username":"elysia","thumbnail_type":"1","float_img_use":"1","fit_version":"2","draw_target":"iframe","main_frame_id":"snapfit_resultview_iframe","constant_main_frame_id":"snapfit_resultview_iframe","constant_main_popup_frame_id":"snapfit_resultview_iframe_popup"} 
		 */
		
		// 인자 질의(param query GET 송신)
		// 중요 인자
		/*
		 "itemid":"269672",
		 "cateid":"054002",
		 "cat1id":"054",
		 "cat2id":"054002",
		 "cat3id":"", 
		 */
		String requestSite = "https://snapfit.co.kr/Resultview_new/renderSnapfitMain/";
		String jsonQuery = "{\"sf_isshowsimilar\":true," + 
							"\"sf_isshowhistory\":false," + 
							"\"sf_get_bodyinfo_from\":1," + 
							"\"sf_is_live\":1," + 
							"\"logoimg\":\"%2F%2Fsnapvi.ecn.cdn.infralab.net%2Fsnapfit%2Fstores%2Fcommon%2Flogo%2Fsnapfit_logo_3.png\"," + 
							"\"logoimg_m\":\"\"," + 
							"\"is_use_history_sizecur_size\":\"0\"," + 
							"\"similarskin_rec_type\":\"rank\"," + 
							"\"viewname\":\"f_c_t\"," + 
							"\"categender\":\"f\"," + 
							"\"device_type\":\"pc\"," + 
							"\"itemid\":\"269672\"," + 
							"\"cateid\":\"054002\"," + 
							"\"cat1id\":\"054\"," + 
							"\"cat2id\":\"054002\"," + 
							"\"cat3id\":\"\"," + 
							"\"is_use_order_buylist\":\"1\"," + 
							"\"is_use_similarskin_satisfaction_ratio\":\"1\"," + 
							"\"is_use_chartpopup\":\"0\"," + 
							"\"isupotheruserbuyframe\":\"0\"," + 
							"\"float_img_type\":\"%2F%2Fcdn.snapfit.co.kr%2Fcommon%2Fbtn2.png\"," + 
							"\"is_use_rec_item\":\"0\"," + 
							"\"domain\":\"ely-sia.net\"," + 
							"\"store_type\":\"makeshop\"," + 
							"\"use_sync\":true," + 
							"\"result\":\"success\"," + 
							"\"is_show_main\":true," + 
							"\"ac\":\"\"," + 
							"\"sf_store_username\":\"elysia\"," + 
							"\"thumbnail_type\":\"1\"," + 
							"\"float_img_use\":\"1\"," + 
							"\"fit_version\":\"2\"," + 
							"\"draw_target\":\"iframe\"," + 
							"\"main_frame_id\":\"snapfit_resultview_iframe\"," + 
							"\"constant_main_frame_id\":\"snapfit_resultview_iframe\"," + 
							"\"constant_main_popup_frame_id\":\"snapfit_resultview_iframe_popup\"" + 
							"}";
		
		// log.info("jsonQuery : " + jsonQuery);
		
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(jsonQuery, Map.class);
		
		// map.entrySet().forEach(x->{log.info(x+"");});
		
		// 여기서 기존 필수 인자를 빼고 필수 인자 대체 입력
		/*
		 "itemid":"269672",
		 "cateid":"054002",
		 "cat1id":"054",
		 "cat2id":"054002",
		 */
		
		map.put("itemid", branduid);
		map.put("cateid", cateNum + cateNum2);
		map.put("cat1id", cateNum);
		map.put("cat2id", cateNum2);		
		
		jsonQuery = gson.toJson(map);
		jsonQuery = URLEncoder.encode(jsonQuery); // 필히 할 것 !
		
		// log.info("jsonQuery : " + jsonQuery);
		log.info("iframe Site : " + requestSite + jsonQuery);
		
		Document doc = Jsoup.connect(requestSite + jsonQuery).get();
		
		// 상품 상세 정보 DTO
		ShopProductDTO shopProductDTO = new ShopProductDTO();
		
		Elements searchPrefixElements = null;
		
		// 개별 페이지에 따라 상품 정보 패널이 출력되는 규칙성 변경됨 
		if (doc.select("div.sfskin_fleft1").size() > 0) { // 일반적인 경우
			
			log.info("일반 유형");
			
			searchPrefixElements = doc.select("div.sfskin_fleft1")
								      .get(0)
									  .getElementsByClass("prd_value");
				
			// 대입
			shopProductDTO.setSize(searchPrefixElements.eq(0).text());
			shopProductDTO.setColors(searchPrefixElements.eq(1).text());
			shopProductDTO.setModel(searchPrefixElements.eq(2).text()); 
			shopProductDTO.setMaterials(searchPrefixElements.eq(3).text());
			shopProductDTO.setCountry(searchPrefixElements.eq(4).text());
			shopProductDTO.setYom(searchPrefixElements.eq(5).text());
			shopProductDTO.setQas(searchPrefixElements.eq(6).text());
		
		} else if (doc.select("ul.infoTxt").size() > 0) { // ex) 266881 의 경우
			
			log.info("특수 유형");
			
			searchPrefixElements = doc.select("ul.infoTxt")
								      .get(0)
									  .getElementsByTag("li");
			
			int cnt = 0;
			int limit = 7; // 총 항목수 (SIZE, COLOR, 소재, 모델, 제조국, 제조연월, 품질보증기준)
			
			// 대입
			shopProductDTO.setSize(searchPrefixElements.eq(cnt).text().split(":")[1]);  
			log.info((cnt + 1) + " : " + shopProductDTO.getSize());
			cnt++;
			
			shopProductDTO.setColors(searchPrefixElements.eq(cnt).text().split(":")[1]); 
			log.info((cnt + 1) + " : " + shopProductDTO.getColors());
			cnt++;
			
			// ex) 261240은 모델 없음
			
			log.info("모델 정보 : " + searchPrefixElements.eq(cnt).html());
			if (searchPrefixElements.eq(cnt).html().contains("MODEL") == false) {

				log.info("모델 정보 없음");
				shopProductDTO.setModel("모델 정보 없음");	
				limit--; // 점검 사항 1개 줄임
				
			} else {
				
				log.info("모델 정보 있음");
				shopProductDTO.setModel(searchPrefixElements.eq(cnt).text().split(":")[1]);	
				log.info((cnt + 1) + " : " + shopProductDTO.getModel());
				cnt++;
			} //
			
			shopProductDTO.setMaterials(searchPrefixElements.eq(cnt).text().split(":")[1]); 
			log.info((cnt + 1) + " : " + shopProductDTO.getMaterials());
			cnt++;
			
			shopProductDTO.setCountry(searchPrefixElements.eq(cnt).text().split(":")[1]); 
			log.info((cnt + 1) + " : " + shopProductDTO.getCountry());
			cnt++;
			
			shopProductDTO.setYom(searchPrefixElements.eq(cnt).text().split(":")[1]);
			log.info((cnt + 1) + " : " + shopProductDTO.getYom());
			cnt++;
			
			log.info("cnt : " +cnt);
			
			// 마지막 항목
			log.info("limit : " +limit);
			
			shopProductDTO.setQas(searchPrefixElements.eq(cnt).text().split(":")[1]);
			log.info((cnt + 1) + " : " + shopProductDTO.getQas());
			
		} else { // ex) 268082 의 경우
			
			// skip 처리
			return null;
		} //
		
		log.info("상품 상세정보 : " + shopProductDTO);
		
		return shopProductDTO;
	}
	
	/**
	 *  수집(크롤링) 항목 : 상품명 / 상품 옵션(상세 정보) / 상품단가  / 상품 이미지
	 * 
	 * @param realCateNum 프로젝트 구현상의 실제 카테고리명 ex) 티셔츠 => 01 
	 * @param cateNum (원천 사이트상의) 카테고리 명  ex) 아우터 => "054"
	 * @param subCateNum (원천 사이트상의) 세부 카테고리명  ex) 가디건 => "002"
	 * @param cateName 서브 카테고리명  ex) cardigan
	 * @param prlen 수집할 제품 수량 ex) 50
	 *  
	 * @return 세부 카테고리별 상품 정보 목록
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	// private List<ProductVO> crawlProducts() {
	public void crawlProducts(String realCateNum,
							  String cateNum, 
							  String subCateNum,
							  String cateName,
							  int prlen) throws IOException, InterruptedException {
		
		log.info("crawlProducts");
		
		List<ProductVO> prList = new ArrayList<>();
		ProductVO productVO;
		// 의류몰 베스트 상품 카테고리
		// https://www.ely-sia.net/index.html
		
		// 대상 카테고리 : 아우터 카테고리 -> 가디건, 자켓/코트 (2개 세부 카테고리에서만 수집)
		// https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X
		
		// 가디건     : https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X&mcode=002
		// 자켓/코트 : https://www.ely-sia.net/shop/shopbrand.html?xcode=054&type=X&mcode=003
		
		// 가디건
		String siteLink = "https://www.ely-sia.net/shop/shopbrand.html?xcode="+cateNum+"&type=X&mcode="+subCateNum;
		Document doc = Jsoup.connect(siteLink).get();
		
		// 사이트 카테고리 page source
		/* -- 원문(source text) : 카테고리 갤러리 페이지 소스 중에서
		   crawl_text : 카테고리_상품고유정보_html내용.txt 
		*/
		
		// 세부 카테고리별 한정 수량 수집 : ex) 50개
		// int prlen = doc.select("div.prd-info").size();
		log.info("수집할 상품 수 : {}", prlen);
		
		// 상품 상세 정보 DTO
		ShopProductDTO shopProductDTO;
		
		// 카테고리 보류 개별 상품 고유번호(branduid) 목록
		List<String> branduidList = new ArrayList<>(); 
		
		for (int i=0; i<prlen; i++) {
			
			log.info("번호 : "+ (i+1));
			
			// 50개의 상품의 고유번호 : branduid 수집 : data-product-code="269672"
			// ex) <span class="crema-product-reviews-count" data-product-code="269672"
			
			String branduid = "";
			
			// 갤러리 한 페이지의 요소수 한계량 : 36개 (4 * 9) : 행 * 열
			// 다음 페이지(2 페이지)로 넘어가기 
			if (i >= 36) {
				
				log.info("페이지 전환(다음 2페이지)");
				siteLink += "&sort=&page=2";
				doc = Jsoup.connect(siteLink).get();
				
			} 
			
			log.info("siteLink : " + siteLink);
			
			int realIndexPerpage = i >= 36 ?  i - 36 : i; // 페이지 전환시 인덱스(index) 설정

			try {
				
				branduid = doc.select("dd.prd-info")
						   	  .get(realIndexPerpage) // 주의) index 가 다시 실질적 초기화 !
							  .getElementsByClass("prd-price")
							  .get(0)
							  .getElementsByClass("crema-product-reviews-count")
							  .attr("data-product-code");
				
			} catch (Exception e) {
				log.error("{} 번째 요소 고유 번호 수집 에러 : 일반 DOM 유형이 아님 : {}", i+1, e);
			} // 
		
			log.info("상품 고유번호 (branduid) : " + branduid); 
		
			//////////////////////////////////////////////////////////////////////////////////
			
			// 개별 상품 정보 수집
			// 대상 페이지 : https://www.ely-sia.net/shop/shopdetail.html?branduid=269672
			
			// 사이트 카테고리 page source
			/* -- 원문(source text) : 카테고리 갤러리 페이지 소스 중에서
			   crawl_text : 개별상품_html내용.txt 
			 */
			
			log.info("///--------------------------------------------------------------------------------");
			
			log.info("개별 상품 정보 조회 : " + branduid);
			
			// 개별 상품 대표 이미지 주소
			String siteImageLink = "https://www.ely-sia.net/shop/shopdetail.html?branduid="+branduid;
			Document doc2 = Jsoup.connect(siteImageLink).get();
			
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
			
			// 상품 옵션 : 첫번째  선택 상자(사이즈)
			
			// 선택 상자 갯수
			int productSelect1Size = doc2.select("form#form1")
									   	 .get(0)
										 .getElementsByClass("basic_option")
										 .eq(0) // 첫번째 선택 상자
										 .get(0)
										 .getElementsByTag("option")
										 .size() - 1; // 첫 option 요소 제외(첫 요소 : <option value="">옵션 선택</option>) => -1
			
			log.info("상품 옵션(사이즈) : 첫번째  선택 상자 요소(option) 요소 갯수 : " + productSelect1Size);
			
			
			for (int j=0; j<productSelect1Size; j++) { // 주의) i 카운터 변수는 전체 상품 갯수 카운터 !
				
				String productSelect1Option = doc2.select("form#form1")
											   	  .get(0)
												  .getElementsByClass("basic_option")
												  .eq(0)
												  .get(0)
												  .getElementsByTag("option")
												  .eq(j+1) // 카운터 적용
												  .attr("title");
				
				log.info("상품 옵션(사이즈)-{} : {}", j+1, productSelect1Option);
				
			} // for
			
			log.info("///--------------------------------------------------------------------------------");
			
			int productSelect2Size = doc2.select("form#form1")
									   	 .get(0)
										 .getElementsByClass("basic_option")
										 .eq(1) // 두번째 선택 상자
										 .get(0)
										 .getElementsByTag("option")
										 .size() - 1; // 첫 option 요소 제외(첫 요소 : <option value="">옵션 선택</option>) => -1

			log.info("상품 옵션(색상) : 두번째  선택 상자 요소(option) 요소 갯수 : " + productSelect2Size); 
			
			for (int j=0; j<productSelect2Size; j++) { // 주의) i 카운터 변수는 전체 상품 갯수 카운터 !
				
				String productSelect2Option = doc2.select("form#form1")
											   	  .get(0)
												  .getElementsByClass("basic_option")
												  .eq(1) // 두번째 선택 상자
												  .get(0)
												  .getElementsByTag("option")
												  .eq(j+1) // 카운터 적용
												  .attr("title");
				
				log.info("상품 옵션(색상)-{} : {}", j+1, productSelect2Option);
				
			} // for
			
			
			log.info("///--------------------------------------------------------------------------------");
			
			// 상품 이미지
			
			// 상품 우측 대표 이미지
			String thumbImageRightFilename = doc2.select("div.thumb")
							 					 .get(0)
												 .getElementsByTag("img")
												 .attr("src")
												 .split("/")[3];

			thumbImageRightFilename = thumbImageRightFilename.substring(0, thumbImageRightFilename.indexOf("?"));
			
			// 우측 큰 파일     format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_thumbnail
			// 이미지 사이즈 : 610 * 686 px
			// ex) https://www.ely-sia.net/shopimages/park6594/0540020007112.jpg
			
			String siteFixURL = "https://www.ely-sia.net/shopimages/park6594/";					
			String thumbImageRightFilenameURL = siteFixURL + "/" + thumbImageRightFilename;
			
			log.info("이미지  경로 : " + thumbImageRightFilenameURL);			

			// 카테고리 폴더 점검 및 생성
			// 전체 저장 폴더 : "C:/joeunmall-teamproject/product-images";
			String targetImagePath = "C:/joeunmall-teamproject";
			
			this.createSaveFolder(targetImagePath); // C:/joeunmall-teamproject
			this.createSaveFolder(targetImagePath += "/product-images"); // C:/joeunmall-teamproject/product-images
			// this.createSaveFolder(targetImagePath + "/04_cardigan"); // C:/joeunmall-teamproject/product-images/04_cardigan
			this.createSaveFolder(targetImagePath + "/"+ realCateNum + "_" + cateName); // ex) C:/joeunmall-teamproject/product-images/04_cardigan
			
			// 이미지 다운로드 복사
			 InputStream in = new URL(thumbImageRightFilenameURL).openStream();
			
			// format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_thumbnail
			 String saveFileName = String.format("22_%s_%03d_thumbnail.jpg", realCateNum, i+1); // 실제 index <= i+1
			 Files.copy(in, Paths.get(targetImagePath + "/" + realCateNum + "_" + cateName + "/" + saveFileName), StandardCopyOption.REPLACE_EXISTING);
		
			log.info("썸네일(우측 큰 상품 이미지) (원파일명) : " + thumbImageRightFilename);
			
			///--------------------------------------------------------------------------------
			
			// 상품 좌측 썸네일 이미지 갯수
			int thumbImageLeftFilenamesSize = doc2.select("div.multiThumb")
							 					  .get(0)
												  .getElementsByTag("img")
												  .size();
			
			log.info("좌측 개별 상품 썸네일 이미지(원화 축소 사용) 갯수 : " + thumbImageLeftFilenamesSize); 
			
			for (int j=0; j<thumbImageLeftFilenamesSize; j++) {
				
				// 좌측 작은 썸네일들 : 썸네일 이지만 원화(이미지 사이즈 : 610 * 686 px)를 브라우저에서 축소 랜더링한 형태 
				// 가시적 이미지 사이즈 : 125 * 140 px
				// 좌측 작은 파일들 format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_상세이미지순서(1자리)(1~5)
				// ex) 
				// http://park6594.jpg3.kr/2022/1024/so_1024_3_13.jpg
				// http://park6594.jpg3.kr/2022/1024/so_1024_3_14.jpg
				// http://park6594.jpg3.kr/2022/1024/so_1024_3_15.jpg
				
				// ex) 특수사례 : 261445
				// 좌측 썸네일 이미지-1 : /shopimages/park6594/0540020006872.jpg?1567664348
				

				String thumbImageLeftFilePathname = doc2.select("div.multiThumb")
														.get(0)
														.getElementsByTag("img")
														.eq(j) // 주의) index (j) 주의 !
														.attr("src");
				
				// 특수사례에 대한 패치 : "?" 포함여부 재점검 포함되어 있으면 다시 token화
				if (thumbImageLeftFilePathname.contains("?") == true) { 
					thumbImageLeftFilePathname = "https://www.ely-sia.net/" + thumbImageLeftFilePathname.split("\\?")[0];
				}			
				
				log.info("좌측 썸네일 이미지-{} : {}", (j+1), thumbImageLeftFilePathname);
				
				// 이미지 다운로드 복사(원파일 : 이미지 사이즈 : 610 * 686 px)
				 in = new URL(thumbImageLeftFilePathname).openStream();
				
				// format) 좌측 작은 파일들 format) 상품등록연도(2자리)_상품카테고리번호(2자리)_상품등록순서(3자리)_상세이미지순서(1자리)(1~5)
				 String saveThumbFileName = String.format("22_%s_%03d_%d.jpg", realCateNum, i+1, j+1); // 실제 index <= i+1, j+1
				 Files.copy(in, Paths.get(targetImagePath + "/" + realCateNum + "_" + cateName + "/" + saveThumbFileName), StandardCopyOption.REPLACE_EXISTING);
				
			} // for : 썸네일 저장
			
			log.info("//-----------------------------------------------------------------------");
			
			// 상품 상세 정보 수집
			
			// 카테고리명 : 가디건(04) => 054 : 사이트 링크에서의 인자명(xcode)
			// 서브 카테고리명 : 아우터 카테고리에서 두번째 순서 => 02 : 사이트 링크에서의 인자명(mcode)
			
			// 주의) 상품 정보 취득이 안되는 경우는 skip 처리 : 일반적이지 않은 특수 포맷으로 인한 상품 수집 포기 
			try {
				shopProductDTO = this.searchProductContent(branduid, cateNum, subCateNum);
			} catch (Exception e) {
				log.error("특수 화면 레이아웃 포맷으로 인한 상품 수집 포기 : " + e);
				continue; // skip
			}
			
			log.info("상품({}) 상세정보 : {}", branduid, shopProductDTO);
			
			log.info("");
			log.info("//////////////////////////////////////////////////////////////////////////////////////////////////////////////");
			log.info("");
			
			// 휴지 시간 할당
			Thread.sleep(200);
			
			//------------------------------------------------------------------------------------------------------
			
			// 수집 정보들을 VO로 적재
			productVO = new ProductVO();
			
			// 상품번호			
			productVO.setProductIndex(this.makeProductIndex(cateNum, i));
			
			// 주문번호
			productVO.setOrderIndex(this.makeOrderIndex(i));
			
			// 주문자명
			productVO.setUserName(this.makeName());
			
			// 상품명 
			productVO.setProductName(productName);
			
			// 상품옵션(상품 상세 사항)
			
			// DTO => JSON 
			String productOption = new Gson().toJson(shopProductDTO); 
			productVO.setProductOption(productOption);
			
			// 상품가격
			productVO.setProductPrice(Integer.parseInt(productPrice));
			
			// 등록일자
			productVO.setProductDate(java.sql.Date.valueOf(this.makeRegDate()));

			log.info("{}", productVO);
			
			// 리스트  적재
			prList.add(productVO);
		} // for
		
		///////////////////////////////////////////////////////////////////////////////////////////////
		
		// 직렬화
		log.info("직렬화 시작");
		
		String fileLocation = new File("src\\main\\resources\\ser").getAbsolutePath() 
								+ "\\PerfectPrList_" + realCateNum + "_" + subCateNum + ".ser";
		
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
	 * @param filename 직렬화 파일명 ex) ser/PerfectPrList_01.ser
	 * 	 
	 * @return 상품 리스트
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public List<ProductVO> getProductsFromSerFile(String filePath) throws FileNotFoundException {
		
		File file = ResourceUtils.getFile("classpath:" + filePath);
		List<ProductVO> prList = null;
		
		try (FileInputStream     fis = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(fis);) {
		
			ObjectInputStream in = new ObjectInputStream(bis);
			
			prList = (List<ProductVO>)in.readObject();
			
		} catch (IOException e) {
			log.error("역직렬화 에러 : " + e);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error("직렬화 파일 로딩 오류 : " + e);
			e.printStackTrace();
		}
		
		return prList;
	} //
	
}
