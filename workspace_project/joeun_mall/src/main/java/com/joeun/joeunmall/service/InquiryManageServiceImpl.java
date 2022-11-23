package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.joeunmall.dao.InquiryManageDAO;
import com.joeun.joeunmall.vo.InquiryVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InquiryManageServiceImpl implements InquiryManageService {
	
	@Autowired
	InquiryManageDAO InquiryManageDAO;
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<InquiryVO> getAllInquiryByPaging(int currentPage, int recordsPage) {
	
		return InquiryManageDAO.getAllInquiryByPaging(currentPage, recordsPage);
	}
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int getAllInquiryRecordNum() {
		return InquiryManageDAO.getAllInquiryRecordNum();
	}
	

}
