package com.joeun.joeunmall.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joeun.joeunmall.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 제품관리페이지 DAO 
 * @author LSE
 */
@Repository
@Slf4j
public class ProductManageDAOImpl implements ProductManageDAO{

	@Autowired
	SqlSession sqlSession;
	
	private static final String MAPPER_NS = "com.joeun.joeunmall.mapper.product_tbl.";
	
	@Override
	public List<ProductVO> getAllProductByPaging(int currentPage, int recordsPerPage) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		return sqlSession.selectList(MAPPER_NS+"getAllProductByPaging", map);
	}

	@Override
	public int getAllProductRecordNum() {
		return sqlSession.selectOne(MAPPER_NS+"getAllProductRecordNum");
	}

}
