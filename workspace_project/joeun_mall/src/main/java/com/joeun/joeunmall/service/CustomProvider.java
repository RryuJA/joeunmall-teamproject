package com.joeun.joeunmall.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.joeun.joeunmall.vo.CustomUser;
import com.joeun.joeunmall.vo.MemberRoleVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomProvider 
		implements AuthenticationProvider, UserDetailsService {
	
	// legacy project 에서 wiring 문제 발생 !
	// @Autowired
	// private BCryptPasswordEncoder passwordEncoder;
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
    @Override
	public CustomUser loadUserByUsername(String username) {
    	
    	log.info("loadUserByUsername");
    	
    	CustomUser customUser = new CustomUser();  
    	
    	try {
	    	customUser = jdbcTemplate.queryForObject(
	    			"SELECT * FROM member_auth_tbl WHERE id=?",
				     new Object[]{ username },
				     new BeanPropertyRowMapper<CustomUser>(CustomUser.class));
	    	
	    	customUser.setUsername(username);
	    	
	    } catch (EmptyResultDataAccessException e) {
	    	log.info("회원 정보 없음");
	    	return null;
	    }
    	
    	return customUser;
    }
    
	private List<MemberRoleVO> loadUserRole(String username) {
	
		log.info("loadUserRole");
		List<MemberRoleVO> memberRoleVOs = new ArrayList<>();
		
		try {
			memberRoleVOs = jdbcTemplate.query(
	   			 	"SELECT id, id as \"username\", role FROM member_roles_tbl WHERE id=?", 
				     new Object[]{ username },
				     new BeanPropertyRowMapper<MemberRoleVO>(MemberRoleVO.class));
			
		} catch (EmptyResultDataAccessException e) {
			log.info("회원 롤(Role) 정보 없음");
	    	return null;
	    }
		
		return memberRoleVOs;
	
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) 
				throws AuthenticationException {
		
		log.info("authenticate");
		
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        log.info("password : {}", password);

        CustomUser user = null;
        Collection<? extends GrantedAuthority> authorities = null;
        
        try {
    			user = this.loadUserByUsername(username);
	        		
        		log.info("user : {}", user);
        		
        		if (user == null) {
        			throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        		}
        		
	        	
	        	List<MemberRoleVO> memberRoleVOs = this.loadUserRole(username);
	        	
	        	log.info("롤 정보-1 : " + memberRoleVOs.get(0).getRole());
	        	
	        	// 다중 롤이 있으면...
	        	if (memberRoleVOs.size() == 2) {
	        		log.info("롤 정보-2 : " + memberRoleVOs.get(1).getRole());
	        	}
	        	
	        	user.setAuthorities(memberRoleVOs);
	            
	            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            
	            if (passwordEncoder.matches(password, user.getPassword())) { 
	            	
	            	log.info("비밀번호 일치 !");	
	            	
        		} else {
        			
	            	throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
	            }	
	            
	            authorities = user.getAuthorities();
            
        } catch(UsernameNotFoundException e) {
            log.info(e.toString());
            throw new UsernameNotFoundException(e.getMessage());
        } catch(BadCredentialsException e) {
            log.info(e.toString());
            throw new BadCredentialsException(e.getMessage());
        } catch(Exception e) {
            log.info(e.toString());
            e.printStackTrace();
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

} //