package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.joeunmall.dao.UserMypageInquiryDAO;
import com.joeun.joeunmall.vo.InquiryVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LSE
 *
 */
@Service
@Slf4j
public class UserMypageInquiryServiceImpl implements UserMypageInquiryService {

	@Autowired
	UserMypageInquiryDAO userMypageInquiryDAO;
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<InquiryVO> selectMyPageInquiryIndex(int currentPage, int recordsPerPage, String userIndex) {
		return userMypageInquiryDAO.selectMyPageInquiryIndex(currentPage, recordsPerPage, userIndex);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int selectMyPageInquiryIndexNum(String userIndex) {
		return userMypageInquiryDAO.selectMyPageInquiryIndexNum(userIndex);
	}

}
