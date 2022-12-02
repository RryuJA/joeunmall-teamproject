package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.joeunmall.dao.ProductDAO;
import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
import com.joeun.joeunmall.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	@Autowired ProductDAO productDAO;

	@Transactional(readOnly = true, rollbackFor=Exception.class)
	@Override
	public List<ProductVO> selectProductsByPaging(int currentPage, int recordsPerPage) {
		log.info("selectProductsByPaging");
		return productDAO.selectProductsByPaging(currentPage, recordsPerPage);
	}
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int selectProductCount() {
		log.info("selectProductCount");
		return productDAO.selectProductCount();
	}

	@Override
	public List<ProductVO> selectProductsByPagingAndCategory(int currentPage, int recordsPerPage,
			String productCategoryIndex) {
		log.info("selectProductsByPagingAndCategory");
		return productDAO.selectProductsByPagingAndCategory(currentPage, recordsPerPage, productCategoryIndex);
	}

	@Override
	public int selectProductsCountByCategory(String productCategoryIndex) {
		log.info("selectProductsCountByCategory");
		return productDAO.selectProductsCountByCategory(productCategoryIndex);
	}
	
	//--
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public ProductVO selectProductInfo(String productIndex) {
		return productDAO.selectProductInfo(productIndex);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<ProductImageVO> selectProductImage(String productIndex) {
		return productDAO.selectProductImage(productIndex);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<ProductOptionVO> selectProductOption(String productIndex) {
		return productDAO.selectProductOption(productIndex);
	}

}
