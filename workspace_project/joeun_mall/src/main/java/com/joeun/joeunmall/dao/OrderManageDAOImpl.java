package com.joeun.joeunmall.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * 주문관리페이지 DAO 
 * @author LSE
 */
@Repository
@Slf4j
public class OrderManageDAOImpl implements OrderManageDAO {

	@Autowired
	SqlSession sqlSession;
	
	private static final String MAPPER_NS = "com.joeun.joeunmall.mapper.order_tbl.";
	
	@Override
	public List<Map<String, Object>> getAllOrderByPaging(int currentPage, int recordsPerPage) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		return sqlSession.selectList(MAPPER_NS+"getAllOrderByPaging", map);
	}

	@Override
	public int getAllOrderRecordNum() {
		return sqlSession.selectOne(MAPPER_NS+"getAllOrderRecordNum");
	}

	@Override
	public void updateOrderState(String orderIndex, String orderStateIndex) {
		Map<String, Object> map = new HashMap<>();
		map.put("orderIndex", orderIndex);
		map.put("orderStateIndex", orderStateIndex);
		sqlSession.update(MAPPER_NS+"updateOrderState", map);
	}

	@Override
	public int getOrderStateCheck(String orderIndex) {
		return sqlSession.selectOne(MAPPER_NS+"getOrderStateCheck", orderIndex);
	}

	@Override
	public List<String> getOrderProductsName(String orderIndex) {
		return sqlSession.selectList(MAPPER_NS+"getOrderProductsName", orderIndex);
	}

	@Override
	public List<Map<String, Object>> getOrderSearchByPage(int currentPage, int recordsPerPage, String searchWord) {
		log.info("currentPage: " + currentPage);
		log.info("recordsPerPage: " + recordsPerPage);
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		map.put("searchWord", searchWord);
		return sqlSession.selectList(MAPPER_NS+"getOrderSearchByPage", map);
	}

	@Override
	public int getAllOrderRecordNumSearch(String searchWord) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchWord", searchWord);
		return sqlSession.selectOne(MAPPER_NS+"getAllOrderRecordNumSearch", map);
	}
}	