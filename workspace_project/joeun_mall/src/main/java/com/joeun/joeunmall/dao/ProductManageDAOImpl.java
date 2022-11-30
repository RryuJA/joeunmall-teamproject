package com.joeun.joeunmall.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
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
	
	@Override
	public List<ProductVO> getProductSearchByPage(int currentPage, int recordsPerPage, String searchWord) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		map.put("searchWord", searchWord);
		return sqlSession.selectList(MAPPER_NS+"getProductSearchByPage", map);
	}
	
	@Override
	public int getAllProductRecordNumSearch(String searchWord) {
		return sqlSession.selectOne(MAPPER_NS+"getAllProductRecordNumSearch", searchWord);
	}

	@Override
	public ProductVO selectProductInfo(String productIndex) {
		return sqlSession.selectOne(MAPPER_NS+"selectProductData", productIndex);
	}

	@Override
	public List<ProductImageVO> selectProductImage(String productIndex) {
		return sqlSession.selectList(MAPPER_NS+"selectProductImage", productIndex);
	}

	@Override
	public List<ProductOptionVO> selectProductOption(String productIndex) {
		return sqlSession.selectList(MAPPER_NS+"selectProductOption", productIndex);
	}

	@Override
	public List<ProductVO> selectProductsByPagingAndCategory(int currentPage, int recordsPerPage,
			String productCategoryIndex) {
		log.info("selectProductsByPagingAndCategory");
		Map<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		map.put("productCategoryIndex", productCategoryIndex);
		return sqlSession.selectList(MAPPER_NS + "selectProductsByPagingAndCategory", map);
	}

	@Override
	public int selectProductsCountByCategory(String productCategoryIndex) {
		log.info("selectProductsCountByCategory");
		return sqlSession.selectOne(MAPPER_NS + "selectProductsCountByCategory", productCategoryIndex);
	}



}
