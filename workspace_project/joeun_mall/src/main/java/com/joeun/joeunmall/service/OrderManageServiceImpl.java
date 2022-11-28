package com.joeun.joeunmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.joeun.joeunmall.dao.OrderManageDAO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LSE
 *
 */
@Service
@Slf4j
public class OrderManageServiceImpl implements OrderManageService {

	@Autowired
	OrderManageDAO orderManageDAO;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	/**
	 * 주문관리 paging <br>
	 * ex>한 페이지당 8레코드 <br>
	 * 
	 * @param currentPage 현재 페이지
	 * @param recordsPerPage 한 페이지당 레코드 수
	 * @return 페이지 당 vo
	 */
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<Map<String, Object>> getAllOrderByPaging(int currentPage, int recordsPerPage) {
		return orderManageDAO.getAllOrderByPaging(currentPage, recordsPerPage);
	}
	
	/**
	 * 주문관리 전체 레코드 수
	 * 
	 * @return 게시판 전체 레코드 수
	 */
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int getAllOrderRecordNum() {
		return orderManageDAO.getAllOrderRecordNum();
	}
	
	/**
	 * 주문상태번호 수정
	 * 
	 * @param orderIndex 주문번호
	 * @param orderStateIndex 주문상태번호(ex)STA0, STA1...
	 * @return 수정성공여부
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateOrderState(String orderIndex, String orderStateIndex) {
		boolean result = false;
		
		if(this.getOrderStateCheck(orderIndex) == false) {
			return false;
		}
		
		result = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				boolean result = false;
				
				try {
					orderManageDAO.updateOrderState(orderIndex, orderStateIndex);
					result = true;
				} catch (Exception e) {
					log.error("주문상태 업데이트를 실패했습니다");
					result = false;
					status.setRollbackOnly();
				}
				return result;
			}
			
		});
		return result;
	}
	
	/**
	 * 주문번호 존재확인
	 * 
	 * @param orderIndex 주문번호
	 * @return 주문번호 존재확인!
	 */
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public boolean getOrderStateCheck(String orderIndex) {
		return orderManageDAO.getOrderStateCheck(orderIndex) == 1 ? true : false;
	}
	
	/**
	 * 주문번호에 포함되는 모든 제품명을 뽑는 쿼리
	 * 
	 * @param orderIndex
	 * @return 주문번호에 포함되는 모든 상품명
	 */
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<String> getOrderProductsName(String orderIndex) {
		return orderManageDAO.getOrderProductsName(orderIndex);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<Map<String, Object>> getOrderSearchByPage(int currentPage, int recordsPerPage, String searchWord) {
		return orderManageDAO.getOrderSearchByPage(currentPage, recordsPerPage, searchWord);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int getAllOrderRecordNumSearch(String searchWord) {
		return orderManageDAO.getAllOrderRecordNumSearch(searchWord);
	}
}
