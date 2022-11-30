package com.joeun.joeunmall.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joeun.joeunmall.vo.InquiryVO;

import lombok.extern.slf4j.Slf4j;
/**
 * 1:1문의관리페이지 DAO
 * @author LSE
 * 
 * */
@Repository
@Slf4j
public class InquiryManageDAOImpl implements InquiryManageDAO{

	@Autowired
	SqlSession sqlSession;
	
	private static final String MAPPER_NS = "com.joeun.joeunmall.mapper.inquiry_tbl.";
	
	@Override
	public List<InquiryVO> getAllInquiryByPaging(int currentPage, int recordsPerPage) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		return sqlSession.selectList(MAPPER_NS+"getAllInquiryByPaging", map);
	}
	
	@Override
	public int getAllInquiryRecordNum() {
		return sqlSession.selectOne(MAPPER_NS+"getAllInquiryRecordNum");
	}

	//고객명 '만' 검색
	@Override
	public List<InquiryVO> getInquirySearchByPage(int currentPage, int recordsPerPage, String searchWord) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		map.put("searchWord", searchWord);
		return sqlSession.selectList(MAPPER_NS+"getInquirySearchByPage", map);
	}

	//고객명 검색 후 검색된 레코드 수 전체 계산. 
	@Override
	public int getAllInquiryRecordNumSearch(String searchWord) {
		return sqlSession.selectOne(MAPPER_NS+"getAllInquiryRecordNumSearch", searchWord);
	}

	@Override
	public InquiryVO selectInquiryData(String inquiryIndex) {
		return sqlSession.selectOne(MAPPER_NS+"selectInquiryData", inquiryIndex);
	}
}