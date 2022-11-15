/**
 * 
 */
package com.joeun.joeunmall.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 고객관리페이지 DAO 
 * @author LSE
 */
@Repository
@Slf4j
public class CustomerManageDAOImpl implements CustomerManageDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String MAPPER_NS = "com.joeun.joeunmall.mapper.user_tbl.";

	@Override
	public List<UserVO> getAllUserByPaging(int currentPage, int recordsPerPage) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);		
		return sqlSession.selectList(MAPPER_NS+"getAllUserByPaging", map);
	}

	@Override
	public int getAllUserRecordNum() {
		return sqlSession.selectOne(MAPPER_NS+"getAllUserRecordNum");
	}

}
