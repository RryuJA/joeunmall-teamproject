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
public class PaymentTBLDummy {
	
	String paymentMethods[] = {"무통장입금", "신용카드", "휴대폰 결제"};
	int ranNum = (int)(Math.random()*3);
	String paymentMethod = paymentMethods[ranNum];
	
	//결제유형번호 dummy
	private String makePaymentIndex() {
		String paymentIndex = "PAY" + (ranNum + 1);
		return paymentIndex;
	}
	
	//결제방식 dummy
	private String makePaymentMethod() {
		return paymentMethod;
	}
	
	
}