package com.joeun.joeunmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SoldAmountVO {
	//01의류 판매량
	private int soldAmount01 = 0;
	
	//02의류 판매량
	private int soldAmount02 = 0;
	
	//03의류 판매량
	private int soldAmount03 = 0;
	
	//04의류 판매량
	private int soldAmount04 = 0;
	
	//05의류 판매량
	private int soldAmount05 = 0;
}
