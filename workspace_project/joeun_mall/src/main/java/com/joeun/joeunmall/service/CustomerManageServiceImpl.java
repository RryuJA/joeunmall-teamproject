package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joeun.joeunmall.dao.CustomerManageDAO;
import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

	/**
	 * @author LSE
	 *
	 */
@Service
@Slf4j
public class CustomerManageServiceImpl implements CustomerManageService {
	
	@Autowired
	CustomerManageDAO customerManageDAO;

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<UserVO> getAllUserByPaging(int currentPage, int recordsPerPage) {
		
		return customerManageDAO.getAllUserByPaging(currentPage, recordsPerPage);
	}
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int getAllUserRecordNum() {
		return customerManageDAO.getAllUserRecordNum();
	}

	//고객명 '만' 검색
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<UserVO> getSearchByPage(int currentPage, int recordsPerPage, String searchWord) {
		return customerManageDAO.getSearchByPage(currentPage, recordsPerPage, searchWord);
	}
	
	//검색된 레코드 수 전체 계산. 
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int getAllUserRecordNumSearch(String searchWord) {
		return customerManageDAO.getAllUserRecordNumSearch(searchWord);
	}
}
