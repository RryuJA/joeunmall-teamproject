package com.joeun.joeunmall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.joeun.joeunmall.vo.GraphDataVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MonthlyOrderStatsController {
	private List<GraphDataVO> searchList(List<GraphDataVO> listAll, String clothType, String sellPeriod){
		
		List<GraphDataVO> resultList = new ArrayList<>();  
		for(int i=0; i<listAll.size(); i++) {
			GraphDataVO graphdataVO = listAll.get(i);
			if(graphdataVO.getCt().equals(clothType) && graphdataVO.getPeriod().equals(sellPeriod)) {
				resultList.add(graphdataVO);
			}
		}
		return resultList;
	}
}
