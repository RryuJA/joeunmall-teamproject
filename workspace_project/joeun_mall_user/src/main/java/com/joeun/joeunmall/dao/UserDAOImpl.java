package com.joeun.joeunmall.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joeun.joeunmall.vo.OrderVO;
import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	private static final String NS_NAME = "com.joeun.joeunmall.mapper.user.";

	@Override
	public UserVO selectUser(String userId) {
		log.info("selectUser");
		return sqlSession.selectOne(NS_NAME + "selectUser", userId);
	}

	@Override
	public String selectMaxUserIndex() {
		log.info("selectMaxUserIndex");
		return sqlSession.selectOne(NS_NAME + "selectMaxUserIndex");
	}

	@Override
	public void insertUser(UserVO userVO) {
		log.info("insertUser");
		sqlSession.insert(NS_NAME + "insertUser", userVO); 
	}

	@Override
	public void updateUser(UserVO userVO) {
		log.info("updateUser");
		sqlSession.insert(NS_NAME + "updateUser", userVO); 
	}

	@Override
	public void deleteUser(String userId) {
		log.info("deleteUser");
		sqlSession.insert(NS_NAME + "deleteUser", userId); 
	}

	@Override
	public List<OrderVO> selectMypageUserIndex(int currentPage, int recordsPerPage, String userIndex) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("currentPage", currentPage);
		map.put("recordsPerPage", recordsPerPage);
		map.put("userIndex", userIndex);
		return sqlSession.selectList(NS_NAME+"selectMypageUserIndex", map);
	}

	@Override
	public int selectMypageUserIndexNum(String userIndex) {
		return sqlSession.selectOne(NS_NAME+"selectMypageUserIndexNum", userIndex);	
	}

	@Override
	public List<String> getOrderProductsName(String orderIndex) {
		return sqlSession.selectList(NS_NAME+"getOrderProductsName", orderIndex);
	}
}
