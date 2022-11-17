package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.joeunmall.dao.OrderManageDAO;
import com.joeun.joeunmall.vo.OrderVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class OrderManageServiceImpl implements OrderManageService {

	/**
	 * @author LSE
	 *
	 */


	@Autowired
	OrderManageDAO orderManageDAO;
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<OrderVO> getAllOrderByPaging(int currentPage, int recordsPerPage) {
		
		return orderManageDAO.getAllOrderByPaging(currentPage, recordsPerPage);
	}
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int getAllOrderRecordNum() {
		return orderManageDAO.getAllOrderRecordNum();
		}
			
}

