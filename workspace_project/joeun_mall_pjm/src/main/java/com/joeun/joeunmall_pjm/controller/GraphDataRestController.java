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
	public ResponseEntity<List<GraphDataVO>> getGraphJsonVO(@RequestParam("graphType") String graphType) {
		log.info("getGraphJsonList");
		log.info("graphType: " + graphType);
		
		//1)가격
		//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
		//data: [231, 227, 132, 121, 80]
		
		//2)수량
		//categories: ['티셔츠', '팬츠/스커트', '원피스', '니트/가디건', '자켓']
		//data: [1211000, 3211000, 1412000, 1821000, 5483000]
		
		List<GraphDataVO> list = new ArrayList<>();
		GraphDataVO graphDataVO;

		list.add(new GraphDataVO(23213, "2209", "ct-1"));
		list.add(new GraphDataVO(13213, "2209", "ct-2"));
		list.add(new GraphDataVO(23113, "2208", "ct-3"));
		list.add(new GraphDataVO(31213, "2209", "ct-4"));
		list.add(new GraphDataVO(13213, "2207", "ct-5"));
		list.add(new GraphDataVO(13113, "2206", "ct-5"));
		list.add(new GraphDataVO(23213, "2207", "ct-5"));
		list.add(new GraphDataVO(33213, "2206", "ct-4"));
		list.add(new GraphDataVO(11213, "2208", "ct-4"));
		list.add(new GraphDataVO(12213, "2209", "ct-3"));
		list.add(new GraphDataVO(12113, "2209", "ct-3"));
		list.add(new GraphDataVO(13203, "2208", "ct-2"));
		list.add(new GraphDataVO(10213, "2207", "ct-2"));
		list.add(new GraphDataVO(13013, "2206", "ct-2"));
		list.add(new GraphDataVO(43213, "2209", "ct-2"));
		list.add(new GraphDataVO(10213, "2209", "ct-2"));
		list.add(new GraphDataVO(13313, "2208", "ct-1"));
		list.add(new GraphDataVO(23213, "2208", "ct-5"));
		list.add(new GraphDataVO(13213, "2207", "ct-5"));
		
		//Json 생성인데 배열구조임 -> ["티셔츠,23321","팬츠/스커트,232127","원피스,132312","니트/가디건,121212","자켓,82110"] ("키, 값", "키, 값",) 이런 구조
		
		return new ResponseEntity<> (list, HttpStatus.OK);
	}
	
	
	
}
