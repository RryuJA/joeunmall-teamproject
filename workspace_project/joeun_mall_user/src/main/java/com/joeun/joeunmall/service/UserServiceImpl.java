package com.joeun.joeunmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.joeun.joeunmall.dao.UserDAO;
import com.joeun.joeunmall.vo.OrderVO;
import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired UserDAO userDAO;
	
	@Autowired
	TransactionTemplate transactionTemplate;
	
	@Transactional(readOnly = true)
	@Override
	public UserVO selectUser(String userId) {
		log.info("selectUser");
		return userDAO.selectUser(userId);
	}

	@Transactional(readOnly = true)
	@Override
	public int selectMaxUserIndex() {
		log.info("selectMaxUserIndex");
		String str = userDAO.selectMaxUserIndex();
		return Integer.parseInt(str);
	}

	@Transactional
	@Override
	public boolean insertUser(UserVO userVO) {
		log.info("insertUser");
		boolean result = false;
		
		result = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				boolean result = false;
				try {
					userDAO.insertUser(userVO);
					result = true;
				} catch (Exception e) {
					log.error("회원 정보 저장 실패");
					result = false;
					status.setRollbackOnly();
				}
				return result;
			}
			
		});
		return result;
	}

	@Transactional
	@Override
	public boolean updateUser(UserVO userVO) {
		log.info("updateUser");
		boolean result = false;
		
		result = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				boolean result = false;
				try {
					userDAO.updateUser(userVO);
					result = true;
				} catch (Exception e) {
					log.error("회원 정보 수정 실패");
					result = false;
					status.setRollbackOnly();
				}
				return result;
			}
			
		});
		
		return result;
	}

	@Transactional
	@Override
	public boolean deleteUser(String userId) {
		log.info("deleteUser");
		boolean result = false;
		
		result = transactionTemplate.execute(new TransactionCallback<Boolean>() {

			@Override
			public Boolean doInTransaction(TransactionStatus status) {
				boolean result = false;
				try {
					userDAO.deleteUser(userId);
					result = true;
				} catch (Exception e) {
					log.error("회원 정보 삭제 실패");
					result = false;
					status.setRollbackOnly();
				}
				return result;
			}
			
		});
		
		return result;
	}
	
	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public List<OrderVO> selectMypageUserIndex(int currentPage, int recordsPerPage, String userIndex) {
		return userDAO.selectMypageUserIndex(currentPage, recordsPerPage, userIndex);
	}

	@Transactional(readOnly=true, rollbackFor=Exception.class)
	@Override
	public int selectMypageUserIndexNum(String userIndex) {
		return userDAO.selectMypageUserIndexNum(userIndex);
	}

	/**
	 * 주문번호에 포함되는 모든 제품명을 뽑는 쿼리
	 * 
	 * @param orderIndex
	 * @return 주문번호에 포함되는 모든 상품명
	 */
	@Override
	public List<String> getOrderProductsName(String orderIndex) {
		return userDAO.getOrderProductsName(orderIndex);
	}


}
