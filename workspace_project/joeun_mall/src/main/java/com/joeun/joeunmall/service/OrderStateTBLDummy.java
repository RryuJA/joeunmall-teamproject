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


import com.joeun.joeunmall.vo.OrderStateTBLVO;

@Service
@Slf4j
public class OrderStateTBLDummy {
	
	String orderStates[] = {"판매자 확인중", "상품 준비", "배송처리", "배송완료", "주문취소", "반품 처리중", "환불 완료", "교환 처리중", "교환 완료"};
	int ranNum = (int)(Math.random()*orderStates.length);
	String orderState = orderStates[ranNum];
	
	//log를 찍을 때는 항상 함수나 영역 내에서 찍어야 됨
	
	//결제유형번호 dummy
	private String makeOrderStateIndex() {
		
		log.info("ranNum 값: " + ranNum);
		
		String orderStateIndex = "STA" + (ranNum + 1);
		return orderStateIndex;
	}
	
	//결제방식 dummy
	private String makeOrderStateInfo() {
		
		log.info("ranNum 값: " + ranNum);
		
		return orderState;
	}
	
	
//	OrderStateTBLVO orderStateVO = new OrderStateTBLVO();
	
	public void dummySet() {
		
		OrderStateTBLVO orderStateVO = new OrderStateTBLVO();
		
		//결제유형번호 dummy set
		orderStateVO.setOrderStateIndex(this.makeOrderStateIndex());
	
		//결제방식 dummy set
		orderStateVO.setOrderStateInfo(this.makeOrderStateInfo());
		
	}
	
	
	
}