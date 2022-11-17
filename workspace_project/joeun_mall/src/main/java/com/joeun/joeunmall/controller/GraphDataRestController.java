package com.joeun.joeunmall.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.joeunmall.vo.GraphDataVO;
import com.joeun.joeunmall.vo.SoldAmountVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GraphDataRestController {
	
	//sellPeriod와 clothType이 둘다 전체기간/전체종류가 아닐경우 선별하는 함수
	private List<GraphDataVO> searchList(List<GraphDataVO> listAll, String clothType, String sellPeriod){
		
		List<GraphDataVO> resultList = new ArrayList<>();  
		for(int i=0; i<listAll.size(); i++) {
			GraphDataVO graphdataVO = listAll.get(i);
			if(graphdataVO.getCt().equals(clothType) && graphdataVO.getPeriod().substring(0, 4).equals(sellPeriod)) {
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
			if(graphdataVO.getPeriod().substring(0, 4).equals(sellPeriod)) {
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
	 * ex) http://localhost:8282/joeunmall/graphJson/priceQuantity?selectGraph=price
	 * @param selectGraph
	 * @return
	 */
	@GetMapping(value = "/graphJsonVO/priceQuantity", produces = "application/json; charset=UTF-8")//value는 controller의 가상 주소라고 할 수 있음
	public ResponseEntity<List<GraphDataVO>> getGraphJsonVO(@RequestParam("sellPeriod") String sellPeriod, @RequestParam("clothType") String clothType) {
		log.info("getGraphJsonList");
		log.info("sellPeriod: " + sellPeriod);
		log.info("clothType: " + clothType);
		
		//변수 선언
		List<GraphDataVO> listAll = new ArrayList<>();
		GraphDataVO graphDataVO = null;
		SoldAmountVO soldAmountVO = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String uid = "project";
		String pwd = "1111";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String sql = "select * from ORDER_PRODUCT_TBL left join PRODUCT_TBL on ORDER_PRODUCT_TBL.PRODUCT_INDEX=PRODUCT_TBL.PRODUCT_INDEX";
		
		try {
			//데이터베이스 접속 위한 드라이버 sw 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//데이터베이스에 연결하는 작업
			conn = DriverManager.getConnection(url, uid, pwd);
			log.info("connecting database");
			//쿼리 생성할 객체 생성
			stmt = conn.createStatement();
			log.info("stmt: " + stmt);
			//쿼리 생성
			rs = stmt.executeQuery(sql);
			log.info("rs: " + rs);
			
			//쿼리 수행 결과의 데이터 읽어옴
			while(rs.next()) {
				//상품 이름 function key 뭐시기 써서 불려와야됨
				listAll.add(new GraphDataVO(rs.getString("PRODUCT_NAME"), Integer.parseInt(rs.getString("PRODUCT_PRICE")), Integer.parseInt(rs.getString("PRODUCT_COUNT")), rs.getString("ORDER_PRODUCT_INDEX").substring(0, 6), rs.getString("PRODUCT_INDEX").substring(3, 5)));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
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
