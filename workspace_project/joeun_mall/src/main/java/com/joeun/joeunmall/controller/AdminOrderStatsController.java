package com.joeun.joeunmall.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.joeunmall.vo.OrderStatsVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping
public class AdminOrderStatsController{
	@GetMapping("/orderStats.do")
	public String demo(Model model) {
		log.info("demo");
		model.addAttribute("admin", "orderStats");
		return "redirect:/admin/admin-orderStats.do";
	}
	
	@GetMapping("/admin/admin-orderStats.do")
	public String adminOrderStats(Model model) {
		log.info("admin-orderStats");
		
		//변수 선언
		List<OrderStatsVO> orderStatsList = new ArrayList<>();
		OrderStatsVO orderStatsVO = null;
		
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
				orderStatsList.add(new OrderStatsVO(rs.getString("ORDER_PRODUCT_INDEX"), rs.getString("ORDER_PRODUCT_INDEX").substring(0, 6), rs.getString("PRODUCT_NAME"), rs.getString("PRODUCT_INDEX").substring(3, 5), Integer.parseInt(rs.getString("PRODUCT_COUNT")), Integer.parseInt(rs.getString("PRODUCT_PRICE")), Integer.parseInt(rs.getString("PRODUCT_COUNT"))*Integer.parseInt(rs.getString("PRODUCT_PRICE"))));
			}
			log.info(orderStatsList.toString());
			model.addAttribute("orderStatsList", orderStatsList);
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
		return "/admin/admin-orderStats";
	}
	
	@GetMapping("/admin/admin-orderStatsGraph.do")
	public String orderStatsGraph() {
		log.info("admin-orderStatsGraph.do");
		return "/admin/admin-orderStatsGraph";
	}
	
	@GetMapping("/admin/admin-orderStatsMonthly.do")
	public String orderStatsMonthly() {
		log.info("admin-orderStatsMonthly.do");
		return "/admin/admin-orderStatsMonthly";
	}
		
	
	
}
