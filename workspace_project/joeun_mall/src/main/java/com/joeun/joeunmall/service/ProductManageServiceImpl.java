package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.joeunmall.dao.ProductManageDAO;
import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
import com.joeun.joeunmall.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author TJ
 *
 */

@Service
@Slf4j
public class ProductManageServiceImpl implements ProductManageService {

	@Autowired
	ProductManageDAO productManageDAO;
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<ProductVO> getAllProductByPaging(int currentPage, int recordsPerPage) {
		
		return productManageDAO.getAllProductByPaging(currentPage, recordsPerPage);
	}
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int getAllProductRecordNum() {
		return productManageDAO.getAllProductRecordNum();
		}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public ProductVO selectProductInfo(String productIndex) {
		return productManageDAO.selectProductInfo(productIndex);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<ProductImageVO> selectProductImage(String productIndex) {
		return productManageDAO.selectProductImage(productIndex);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<ProductOptionVO> selectProductOption(String productIndex) {
		return productManageDAO.selectProductOption(productIndex);
	}

	@Override
	public List<ProductVO> getProductSearchByPage(int currentPage, int recordsPerPage, String searchWord) {
		return productManageDAO.getProductSearchByPage(currentPage, recordsPerPage, searchWord);
	}

	@Override
	public int getAllProductRecordNumSearch(String searchWord) {
		return productManageDAO.getAllProductRecordNumSearch(searchWord);
	}
			
}
