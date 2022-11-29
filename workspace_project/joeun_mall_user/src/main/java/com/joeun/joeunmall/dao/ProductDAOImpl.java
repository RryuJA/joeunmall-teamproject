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

@Repository
@Slf4j
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired 
	SqlSession sqlSession;
	
	private static final String MAPPER_NS = "com.joeun.joeunmall.mapper.product.";

	@Override
	public List<ProductVO> selectProductsByPaging(int currentPage, int recordsPerPage) {
		log.info("selectProductsByPaging");
		Map<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		
		return sqlSession.selectList(MAPPER_NS + "selectProductsByPaging", map);
	}

	@Override
	public int selectProductCount() {
		log.info("selectProductCount");
		return sqlSession.selectOne(MAPPER_NS + "selectProductCount");
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

}
