package com.joeun.joeunmall.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joeun.joeunmall.vo.InquiryVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 마이페이지-내문의글 
 * @author LSE
 */
@Repository
@Slf4j
public class UserMypageInquiryDAOImpl implements UserMypageInquiryDAO {
	
	@Autowired	
	SqlSession sqlSession;
	
	private static final String NS_NAME = "com.joeun.joeunmall.mapper.inquiry.";

	@Override
	public List<InquiryVO> selectMyPageInquiryIndex(int currentPage, int recordsPerPage, String userIndex) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		map.put("userIndex", userIndex);
		return sqlSession.selectList(NS_NAME+"selectMyPageInquiryIndex", map);
	}

	@Override
	public int selectMyPageInquiryIndexNum(String userIndex) {
		return sqlSession.selectOne(NS_NAME+"selectMyPageInquiryIndexNum", userIndex);
	}

}
