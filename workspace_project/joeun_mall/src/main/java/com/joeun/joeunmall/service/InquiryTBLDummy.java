package com.joeun.joeunmall.service;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;


import com.joeun.joeunmall.vo.InquiryTBLVO;

@Service
@Slf4j
public class InquiryTBLDummy {
	
	int month = 1 + (int)Math.random()*12;
	int date = 1 + (int)Math.random()*31;
	
	public String inqCategories[] = {"교환", "환불", "기타"};
	public String inqCategory = inqCategories[(int)(Math.random()*2)];
	
	
//	public String getInqCategory() {
//		return inqCategory;
//	}
//
//	public void setInqCategory(String inqCategory) {
//		this.inqCategory = inqCategory;
//	}
//	
//	public void setInqCategory(int ranNum) {
////		ranNum = (int)Math.random()*2;
//		log.info("setter 랜덤값 : " + ranNum);
//		this.inqCategory = inqCategories[ranNum];
//	}

	//문의번호 dummy 
	private String makeInquiryIndex(int i) {
		return "22" + String.format("%02d", month) + String.format("%02d", date) + String.format("%03d", i);
	}
	
	//고객번호 dummy
	private String makeUserindex(int i) {
		
		String result ="2022";
		
		return result + String.format("%03d", i);
	}
	
	//문의일자 dummy
	private String makeInquiryDate() {
		return "2022" + String.format("%02d", month) + String.format("%02d", date);
	}
	
	//문의항목 dummy 교환/환불/기타
	//전역변수로 random 뭐시기 설정해서 교환일 경우 문의 내용에도 영향 미치게 해야됨
	private String makeInquiryCategory() {
		return inqCategory;
	}
	
	//문의처리상태 dummy
	private String makeInquiryState() {
		int randomNum = (int)(Math.random()*10);
		//무작위 숫자가 짝수 일 경우 문의접수 return, 홀수 일 경우 답변완료 return
		if(randomNum %2 == 1){
			return "문의접수";
		} else {
			return "답변완료";
		}
	}

	//문의내용 dummy --> 예제 몇개 만들어서 랜덤으로 그중 추출되도록
	private String makeInquiryContent() {
		
		String randomContent = null; //변수 초기화(초기화란 표현이 맞나?)
		
		// 교환 요청문의 내용
		String inqContent1[] = {
				"사이즈가 안맞아서 교환 요청",
				"옷에 얼룩이 묻어있어서 교환 요청",
				"단순 변심에 의한 교환 요청",
				"옵션 착오로 인한 교환 요청"
		};
		
		// 환불 요청문의 내용
		String inqContent2[] = {
				"디자인이 마음에 들지 않아 환불 요청",
				"옷에 얼룩이 묻어있어서 환불 요청",
				"옷이 찍어져 있어서 환불 요청",
				"배송이 너무 늦어서 환불 요청"
		};
		
		
		// 기타 문의 내용
		String inqContent3[] = {
				"s사이즈 재고 언제 들어오나요?",
				"m사이즈 재고 언제 들어오나요?",
				"l사이즈 재고 언제 들어오나요?",
				"xl사이즈 재고 언제 들어오나요?"
		};
	
		if(inqCategory.equals("교환")) {
			randomContent = inqContent1[(int)(Math.random()*inqContent1.length)];
		} 
		else if(inqCategory.equals("환불")) {
			randomContent = inqContent2[(int)(Math.random()*inqContent2.length)];
		} 
		else if(inqCategory.equals("기타")) {
			randomContent = inqContent3[(int)(Math.random()*inqContent3.length)];
		}
		
		return randomContent;
	}
	
	//문의답변 dummy --> 예제 몇개 만들어서 랜덤으로 그중 추출되도록
	public String makeInquiryAnswer() {
		String randomAnswer = null;
		List<String> list = Arrays.asList(inqCategories);
		Collections.shuffle(list);
		this.inqCategory = list.get(0);
		
		log.info("inqCategory: " + inqCategory);
		
		
		// 교환 문의 결과
		String inqAnswer1[] = {
				"교환 해드리겠습니다.",
				"해당 디자인은 원래 그렇게 디자인 된 제품이라 교환 어렵습니다.",
				"해당 사유로 인한 교환은 어렵습니다. 죄송합니다."
		};
		
		// 환불 문의 결과
		String inqAnswer2[] = {
				"환불 해드리겠습니다.",
				"해당 사유로 인한 환불은 어렵습니다. 죄송합니다."
			
		};
		
		// 기타 문의 결과
		String inqAnswer3[] = {
				"이번달 말즈음 입고될 예정입니다.",
				"입고 예정 없습니다. 죄송합니다."
		};
		
		log.info("inqCategory: " + inqCategory);
		if(inqCategory.equals("교환")) {
			randomAnswer = inqAnswer1[(int)(Math.random()*inqAnswer1.length)];
		}
		else if(inqCategory.equals("환불")) {
			randomAnswer = inqAnswer2[(int)(Math.random()*inqAnswer2.length)];
		}
		else if(inqCategory.equals("기타")) {
			randomAnswer = inqAnswer3[(int)(Math.random()*inqAnswer3.length)];
		}
		
		return randomAnswer;
	}
	
	
	
	/** 
	 * 크롤링 항목 : 문의 제목(상품명)
	 * @return
	 * @throws IOException
	 */

	// private List<InquiryTBLVO> creawlProducts(){
	public void crawlInquiries() throws IOException {
		
		log.info("crawlInquireis");
		
		List<InquiryTBLVO> inqList = new ArrayList<>();
		InquiryTBLVO inquiryVO;
		
		// 의류몰 사이트
		String siteLink1 = "http://www.midasb.co.kr/shop/bestseller.html?xcode=BEST&type=Y";
		Document doc1 = Jsoup.connect(siteLink1).get();
		
		
		log.info("상품명 크롤");
		
		int prlen = doc1.select("div.prod-body").size();
		
		log.info("상품 수 : {}", prlen);
		
		for (int i = 0; i<prlen; i++) {
			// 상품명 크롤
			String prName = doc1.select("div.prod-body")
								.get(i)
								.getElementsByClass("prod-name")
								.html()
								.split(">")[2];
			
			/////////////////////////////////////////////////////////////
			
			inquiryVO = new InquiryTBLVO();
			
			//문의번호
			inquiryVO.setInquiryIndex(this.makeInquiryIndex(i));
			
			//고객번호
			inquiryVO.setUserIndex(this.makeUserindex(i));
			
			//문의일자
			inquiryVO.setInquiryDate(java.sql.Date.valueOf(this.makeInquiryDate()));
			
			//문의항목
			inquiryVO.setInquiryContent(this.makeInquiryCategory());
			
			//문의 처리 상태
			inquiryVO.setInquiryState(this.makeInquiryState());
			
			//문의제목(상품명)
			inquiryVO.setInquiryTitle(prName);
			
			//문의내용
			inquiryVO.setInquiryContent(this.makeInquiryContent());

			//문의답변
			inquiryVO.setInquiryAnswer(this.makeInquiryAnswer());
			
			log.info("{}", inquiryVO);
			
			inqList.add(inquiryVO);
							
		}
		
		log.info("직렬화 시작");
		
		String fileLocation = new File("src\\main\\resources\\ser").getAbsolutePath() + "\\InqList.ser";
		
		try  (FileOutputStream	   fos = new FileOutputStream(fileLocation);
			  BufferedOutputStream bos = new BufferedOutputStream(fos);)
		{
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(inqList);
		} catch (IOException e) {
			log.error("직렬화 저장 에러: " + e);
			e.printStackTrace();
		}
		
		log.info("직렬화 종료");
	}	
	
	/**
	 * 역직렬화 : ~~.ser => List 치환
	 * 	 
	 * @return 상품 리스트
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
		
	
	
}