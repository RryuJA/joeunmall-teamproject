package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.joeunmall.dao.ProductManageDAO;
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
			
}
