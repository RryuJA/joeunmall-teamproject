package com.joeun.joeunmall.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.joeunmall.vo.GraphDataVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MonthlyGraphDataRestController {
	
/*	//sellPeriod와 clothType이 둘다 전체기간/전체종류가 아닐경우 선별하는 함수
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
*/
	//sellPeriod와 clothType이 둘다 전체기간/전체종류가 아닐경우 선별하는 함수
		private List<GraphDataVO> searchList(List<GraphDataVO> listAll, String clothType, String sellPeriod){
			
			List<GraphDataVO> resultList = new ArrayList<>();  
			for(int i=0; i<listAll.size(); i++) {
				GraphDataVO graphdataVO = listAll.get(i);
				if(graphdataVO.getCt().equals(clothType) && graphdataVO.getPeriod().substring(0, 2).equals(sellPeriod.substring(2))) {
					resultList.add(graphdataVO);
				}
			}
			return resultList;
		}
	
	
	//clothType이 전체종류일 경우 선별하는 함수(판매기간만 선별하는 함수)
	private List<GraphDataVO> searchList_typeAll(List<GraphDataVO> listAll, String sellPeriod){
		
		List<GraphDataVO> resultListTypeAll = new ArrayList<>();  
		for(int i=0; i<listAll.size(); i++) {
			GraphDataVO graphdataVO = listAll.get(i);
			if(graphdataVO.getPeriod().substring(0, 2).equals(sellPeriod.substring(2))) {
				resultListTypeAll.add(graphdataVO);
			}
		}
		return resultListTypeAll;
	}
	
	//sellPeriod가 전체기간일 경우 선별하는 함수(clothType만 선별하는 함수)
	private List<GraphDataVO> searchList_periodAll(List<GraphDataVO> listAll, String clothType){
		
		List<GraphDataVO> resultListPeriodAll = new ArrayList<>();  
		for(int i=0; i<listAll.size(); i++) {
			GraphDataVO graphdataVO = listAll.get(i);
			if(graphdataVO.getCt().equals(clothType)) {
				resultListPeriodAll.add(graphdataVO);
			}
		}
		return resultListPeriodAll;
	}

	
	/**
	 * ex) http://localhost:8282/joeunmall/graphJson/priceQuantity?selectGraph=price
	 * @param selectGraph
	 * @return
	 */
	
	/*
	@GetMapping(value = "/graphJson/priceQuantity", produces = "application/json; charset=UTF-8")//value는 controller의 가상 주소라고 할 수 있음
	public ResponseEntity<Map<String, Integer>> getGraphJson(@RequestParam("selectGraph") String selectGraph) {
		log.info("getGraphJson");
		log.info("selectGraph: " + selectGraph);
		
		//1)가격
		//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
		//data: [231, 227, 132, 121, 80]
		
		//2)수량
		//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
		//data: [1211000, 3211000, 1412000, 1821000, 5483000]
		
		Map<String, Integer> map = new HashMap<>();
		
		//가격일 경우
		if(selectGraph.equals("price") == true) {
			map.put("티셔츠", 231);
			map.put("팬츠/스커트", 227);
			map.put("원피스", 132);
			map.put("니트/가디건", 121);
			map.put("자켓", 80);
		} 
		//수량일 경우
		else { 
			map.put("티셔츠", 23321);
			map.put("팬츠/스커트", 232127);
			map.put("원피스", 132312);
			map.put("니트/가디건", 121212);
			map.put("자켓", 82110);
		}
		
		//Json 생성 -> {"팬츠/스커트":227,"티셔츠":231} 완전 맵구조임
		return new ResponseEntity<> (map, HttpStatus.OK);
	}
	*/
	
	/**
	 * ex) http://localhost:8282/joeunmall/graphJson/priceQuantity?selectGraph=price
	 * @param selectGraph
	 * @return
	 */
	
	/*
	@GetMapping(value = "/graphJsonList/priceQuantity", produces = "text/plain; charset=UTF-8")//value는 controller의 가상 주소라고 할 수 있음
	public ResponseEntity<List<String>> getGraphJsonList(@RequestParam("selectGraph") String selectGraph) {
		log.info("getGraphJsonList");
		log.info("selectGraph: " + selectGraph);
		
		//1)가격
		//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
		//data: [231, 227, 132, 121, 80]
		
		//2)수량
		//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
		//data: [1211000, 3211000, 1412000, 1821000, 5483000]
		
		List<String> list = new ArrayList<>();
		
		//가격일 경우
		if(selectGraph.equals("price") == true) {
			list.add("티셔츠," + 231);
			list.add("팬츠/스커트," + 227);
			list.add("원피스," + 132);
			list.add("니트/가디건," + 121);
			list.add("자켓," + 80);
		} 
		//수량일 경우
		else { 
			list.add("티셔츠," + 23321);
			list.add("팬츠/스커트," + 232127);
			list.add("원피스," + 132312);
			list.add("니트/가디건," + 121212);
			list.add("자켓," + 82110); //"자켓,82110"
		}
		
		//Json 생성인데 배열구조임 -> ["티셔츠,23321","팬츠/스커트,232127","원피스,132312","니트/가디건,121212","자켓,82110"] ("키, 값", "키, 값",) 이런 구조
		
		return new ResponseEntity<> (list, HttpStatus.OK);
	}
	
	*/
	
	/**
	 * ex) http://localhost:8282/joeunmall/graphJson/priceQuantity?selectGraph=price
	 * @param selectGraph
	 * @return
	 */
	@GetMapping(value = "/graphJsonVO/monthlyGraphData", produces = "application/json; charset=UTF-8")//value는 controller의 가상 주소라고 할 수 있음
	public ResponseEntity<List<GraphDataVO>> getMonthlyGraphJsonVO(@RequestParam("sellPeriod") String sellPeriod, @RequestParam("clothType") String clothType) {
		log.info("getGraphJsonList");
		log.info("sellPeriod: " + sellPeriod);
		log.info("clothType: " + clothType);
		log.info("판매년도: " + sellPeriod.substring(2)); //allPeriod일 경우 앞에 두글자 짤려서 log에 나오긴 함
		
		List<GraphDataVO> listAll = new ArrayList<>();
		GraphDataVO graphDataVO;
		
		listAll.add(new GraphDataVO("t-001", 23213, "220908", "01"));
		listAll.add(new GraphDataVO("p-001", 13213, "220620", "02"));
		listAll.add(new GraphDataVO("o-001", 23113, "220821", "03"));
		listAll.add(new GraphDataVO("n-001", 31213, "220913", "04"));
		listAll.add(new GraphDataVO("j-001", 13213, "220715", "05"));
		listAll.add(new GraphDataVO("j-002", 13113, "220630", "05"));
		listAll.add(new GraphDataVO("j-003", 23213, "220708", "05"));
		listAll.add(new GraphDataVO("n-001", 33213, "220601", "04"));
		listAll.add(new GraphDataVO("n-002", 11213, "220816", "04"));
		listAll.add(new GraphDataVO("o-002", 12213, "220907", "03"));
		listAll.add(new GraphDataVO("o-003", 12113, "220927", "03"));
		listAll.add(new GraphDataVO("p-002", 13203, "220824", "02"));
		listAll.add(new GraphDataVO("p-001", 10213, "220721", "02"));
		listAll.add(new GraphDataVO("p-003", 13013, "220606", "02"));
		listAll.add(new GraphDataVO("p-004", 43213, "220903", "02"));
		listAll.add(new GraphDataVO("p-003", 10213, "220929", "02"));
		listAll.add(new GraphDataVO("t-002", 23213, "220817", "01"));
		listAll.add(new GraphDataVO("j-004", 23213, "220819", "05"));
		listAll.add(new GraphDataVO("j-003", 13213, "220711", "05"));
		listAll.add(new GraphDataVO("t-001", 232131, "210908", "01"));
		listAll.add(new GraphDataVO("p-001", 13213, "210620", "02"));
		listAll.add(new GraphDataVO("o-001", 23113, "210821", "03"));
		listAll.add(new GraphDataVO("n-001", 31213, "210913", "04"));
		listAll.add(new GraphDataVO("j-001", 13213, "210715", "05"));
		listAll.add(new GraphDataVO("j-002", 13113, "210630", "05"));
		listAll.add(new GraphDataVO("j-003", 23213, "210708", "05"));
		listAll.add(new GraphDataVO("n-001", 33213, "210601", "04"));
		listAll.add(new GraphDataVO("n-002", 11213, "210816", "04"));
		listAll.add(new GraphDataVO("o-002", 12113, "210907", "03"));
		listAll.add(new GraphDataVO("o-003", 12113, "210927", "03"));
		listAll.add(new GraphDataVO("p-002", 13203, "210824", "02"));
		listAll.add(new GraphDataVO("p-001", 10213, "210721", "02"));
		listAll.add(new GraphDataVO("p-003", 13013, "210606", "02"));
		listAll.add(new GraphDataVO("p-004", 43213, "210903", "02"));
		listAll.add(new GraphDataVO("p-003", 10213, "210929", "02"));
		listAll.add(new GraphDataVO("t-002", 23213, "210817", "01"));
		listAll.add(new GraphDataVO("j-004", 23213, "210819", "05"));
		listAll.add(new GraphDataVO("j-003", 13213, "210711", "05"));
		
		//Json 생성인데 배열구조임
		if(clothType.equals("ct-all") && !sellPeriod.equals("allPeriod")) {
			List<GraphDataVO> resultListTypeAll = this.searchList_typeAll(listAll, sellPeriod);
			return new ResponseEntity<> (resultListTypeAll, HttpStatus.OK);
		} 
		else if(sellPeriod.equals("allPeriod") && !clothType.equals("ct-all")) {
			List<GraphDataVO> resultListPeriodAll = this.searchList_periodAll(listAll, clothType);
			return new ResponseEntity<> (resultListPeriodAll, HttpStatus.OK);//
		} 
		else if(!sellPeriod.equals("allPeriod") && !clothType.equals("ct-all")) {
			List<GraphDataVO> resultList = this.searchList(listAll, clothType, sellPeriod);
			return new ResponseEntity<> (resultList, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<> (listAll, HttpStatus.OK);
		}
		//if문 끝
	
	}
}	//
