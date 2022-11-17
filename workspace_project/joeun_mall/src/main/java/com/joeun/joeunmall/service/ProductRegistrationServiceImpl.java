package com.joeun.joeunmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.joeun.joeunmall.dao.ProductRegistrationDAO;
import com.joeun.joeunmall.vo.ProductDTO;
import com.joeun.joeunmall.vo.ProductImageVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductRegistrationServiceImpl implements ProductRegistrationService {
	
	@Autowired
	ProductRegistrationDAO productDAO;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	@Override
	public String selectMaxProductIndex(String yearCate) {
		log.info("selectMaxProductIndex");
		return productDAO.selectMaxProductIndex(yearCate);
	}

	@Transactional
	@Override
	public boolean insertProduct(ProductDTO productDTO) {
		log.info("insertProduct");
		
		boolean result = false;
		TransactionStatus txStatus =
		        transactionManager.getTransaction(new DefaultTransactionDefinition());
	    try {
	    	productDAO.insertProduct(productDTO);;
	    } catch (Exception e) {
	    	log.error("상품 등록 오류 발생");
	    	result = false;
	    	transactionManager.rollback(txStatus); //등록 취소 
	    	throw e;
	    }
	    transactionManager.commit(txStatus); //등록 승인
	    result = true;
	    
		return result;
	}

	@Transactional
	@Override
	public boolean insertProductImages(ProductImageVO productImageVO) {
		log.info("insertProductImages");
		boolean result = false;
		
		result = transactionTemplate.execute(new TransactionCallback<Boolean>() {
			
			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				boolean result = false;
				
				try {
					productDAO.insertProductImages(productImageVO);
					result = true;
				} catch (Exception e) {
					log.error("상품 이미지 등록 오류 발생");
					result = false;
					status.setRollbackOnly();
				}
				
				return result;
			}
		});
		log.info("result=" + result);
		
		return result;
	}

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	@Override
	public String selectMaxProductImageIndex(String productIndex) {
		log.info("selectMaxProductImageIndex");
		return productDAO.selectMaxProductImageIndex(productIndex);
	}

	@Override
	public String getCatePath(String cateNum) {
		log.info("getCatePath");
		String catePath = "";
		
		switch (cateNum) {
		case "01": catePath = "01_tshirt"; break;
		case "02": catePath = "02_pants"; break;
		case "03": catePath = "03_onepiece"; break;
		case "04": catePath = "04_cardigan"; break;
		case "05": catePath = "05_jacket"; break;
		
		}
		return catePath;
	}

}
