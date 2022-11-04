package com.joeun.joeunmall_pjm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.joeunmall_pjm.vo.GraphDataVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GraphDataRestController {
	
	//sellPeriod와 clothType이 둘다 전체기간/전체종류가 아닐경우 선별하는 함수
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
	
	//clothType이 전체종류일 경우 선별하는 함수
	private List<GraphDataVO> searchList_typeAll(List<GraphDataVO> listAll, String sellPeriod){
		
		List<GraphDataVO> resultListTypeAll = new ArrayList<>();  
		for(int i=0; i<listAll.size(); i++) {
			GraphDataVO graphdataVO = listAll.get(i);
			if(graphdataVO.getPeriod().equals(sellPeriod)) {
				resultListTypeAll.add(graphdataVO);
			}
		}
		return resultListTypeAll;
	}
	
	//sellPeriod가 전체기간일 경우 선별하는 함수
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
	 * ex) http://localhost:8282/joeunmall_pjm/graphJson/priceQuantity?selectGraph=price
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
	 * ex) http://localhost:8282/joeunmall_pjm/graphJson/priceQuantity?selectGraph=price
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
	 * ex) http://localhost:8282/joeunmall_pjm/graphJson/priceQuantity?selectGraph=price
	 * @param selectGraph
	 * @return
	 */
	@GetMapping(value = "/graphJsonVO/priceQuantity", produces = "application/json; charset=UTF-8")//value는 controller의 가상 주소라고 할 수 있음
	public ResponseEntity<List<GraphDataVO>> getGraphJsonVO(@RequestParam("sellPeriod") String sellPeriod, @RequestParam("clothType") String clothType) {
		log.info("getGraphJsonList");
		log.info("sellPeriod: " + sellPeriod);
		log.info("clothType: " + clothType);
		
		
		List<GraphDataVO> listAll = new ArrayList<>();
		GraphDataVO graphDataVO;
		
		listAll.add(new GraphDataVO("t-001", 23213, "2209", "ct-1"));
		listAll.add(new GraphDataVO("p-001", 13213, "2206", "ct-2"));
		listAll.add(new GraphDataVO("o-001", 23113, "2208", "ct-3"));
		listAll.add(new GraphDataVO("n-001", 31213, "2209", "ct-4"));
		listAll.add(new GraphDataVO("j-001", 13213, "2207", "ct-5"));
		listAll.add(new GraphDataVO("j-002", 13113, "2206", "ct-5"));
		listAll.add(new GraphDataVO("j-003", 23213, "2207", "ct-5"));
		listAll.add(new GraphDataVO("n-001", 33213, "2206", "ct-4"));
		listAll.add(new GraphDataVO("n-002", 11213, "2208", "ct-4"));
		listAll.add(new GraphDataVO("o-002", 12213, "2209", "ct-3"));
		listAll.add(new GraphDataVO("o-003", 12113, "2209", "ct-3"));
		listAll.add(new GraphDataVO("p-002", 13203, "2208", "ct-2"));
		listAll.add(new GraphDataVO("p-001", 10213, "2207", "ct-2"));
		listAll.add(new GraphDataVO("p-003", 13013, "2206", "ct-2"));
		listAll.add(new GraphDataVO("p-004", 43213, "2209", "ct-2"));
		listAll.add(new GraphDataVO("p-003", 10213, "2209", "ct-2"));
		listAll.add(new GraphDataVO("t-002", 23213, "2208", "ct-1"));
		listAll.add(new GraphDataVO("j-004", 23213, "2208", "ct-5"));
		listAll.add(new GraphDataVO("j-003", 13213, "2207", "ct-5"));
		
		//Json 생성인데 배열구조임
		if(clothType.equals("ct-all") && !sellPeriod.equals("allPeriod")) {
			List<GraphDataVO> resultListTypeAll = this.searchList_typeAll(listAll, sellPeriod);
			return new ResponseEntity<> (resultListTypeAll, HttpStatus.OK);
		} 
		else if(sellPeriod.equals("allPeriod") && !clothType.equals("ct-all")) {
			List<GraphDataVO> resultListPeriodAll = this.searchList_periodAll(listAll, clothType);
			return new ResponseEntity<> (resultListPeriodAll, HttpStatus.OK);
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
	
}//
