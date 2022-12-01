package com.joeun.joeunmall.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joeun.joeunmall.vo.ProductDTO;
import com.joeun.joeunmall.vo.ProductImageVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ProductRegistrationDAOImpl implements ProductRegistrationDAO {
	
	@Autowired
	SqlSession sqlSession;

	private static final String NS_NAME = "com.joeun.joeunmall.mapper.product_tbl.";
	
	@Override
	public String selectMaxProductIndex(String yearCate) {
		log.info("selectMaxProductIndex");
		yearCate += "_%";
		return sqlSession.selectOne(NS_NAME + "selectMaxProductIndex", yearCate);
	}

	@Override
	public void insertProduct(ProductDTO productDTO) {
		log.info("insertProduct");
		sqlSession.insert(NS_NAME + "insertProduct", productDTO);
	}

	@Override
	public void insertProductImages(ProductImageVO productImageVO) {
		log.info("insertProductImages");
		sqlSession.insert(NS_NAME + "insertProductImages", productImageVO);
	}

	@Override
	public String selectMaxProductImageIndex(String productIndex) {
		log.info("selectMaxProductImageIndex");
		return sqlSession.selectOne(NS_NAME + "selectMaxProductImageIndex", productIndex);
	}
}
