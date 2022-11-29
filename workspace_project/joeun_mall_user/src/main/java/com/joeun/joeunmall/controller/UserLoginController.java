package com.joeun.joeunmall.controller;

import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joeun.joeunmall.service.UserService;
import com.joeun.joeunmall.vo.UserVO;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class UserLoginController {
	
	@Autowired UserService userService;
		
	@GetMapping("/demo.do")
	public String demo() {
		log.info("demo");
		return "demo";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		log.info("home");
		return  "redirect:/user/user-productlistCarousel.do";
	}
	
	
	@GetMapping("/user/user-login.do")
	public String userLogin() {
		log.info("userLogin");
		return "/user/user-login";
	}
	
	@GetMapping("/user/user-logout.do")
	public String userLogout(HttpSession session) {
		log.info("userLogout");
		//세션 제거 
		if (session.getAttribute("SESS_LOGIN_ID")!=null){
			session.invalidate();
		}
		return "redirect:/user/user-login.do";
	}
	
	@PostMapping("/user/user-loginProc.do")
	public String userLoginProc(@RequestParam("userId") String userId, 
			@RequestParam("userPw") String userPw, HttpSession session, Model model) {
		log.info("userLoginProc");
		log.info("id =" + userId);
		log.info("pw =" + userPw);
		String msg = ""; //메시지
		String movePath = ""; //이동 페이지
		
		//id 존재 여부 점검
		UserVO userVO = userService.selectUser(userId);
		
		if (userVO == null) {
			
			msg = "회원정보가 존재하지 않습니다.";
			movePath = "/user/user-login.do"; //로그인 페이지 
			
		} else { //회원 정보 존재할때 
			
			BCryptPasswordEncoder bce = new BCryptPasswordEncoder();
			log.info("패쓰워드 점검 =" + (bce.matches(userPw, userVO.getUserPw())));
			
			if (bce.matches(userPw, userVO.getUserPw()) == true) {//id , pw 일치
				
				//세션 생성 
				if (session.getAttribute("SESS_LOGIN_ID")==null){
					session.setAttribute("SESS_LOGIN_ID", userId);
				}
				
				msg = "로그인에 성공하였습니다.";
				movePath = "/user/user-productlistCarousel.do"; //상품 리스트 페이지 
				
			} else {//pw가 불일치시
				msg = "패쓰워드가 일치하지 않습니다.";
				movePath = "/user/user-login.do"; //로그인 페이지 
			}
			
		}
		 
		model.addAttribute("errMsg", msg); 
		model.addAttribute("movePath", movePath);
		return "/error/error";
	}
	
	
	
	@GetMapping("/user/user-mypageOrder.do")
	public String mypageORder() {
		log.info("mypageORder");
		return "/user/user-mypageOrder";
	}
	
	// ------------------------------------------------------------------	
	
	@GetMapping("/user/user-shoppingBasket.do")
	public String userShoppingBasket() {
		log.info("userShoppingBasket");
		return "/user/user-shoppingBasket";
	}

	@GetMapping("/user/user-join.do")
	public String userJoin() {
		log.info("userJoin");
		return "/user/user-join";
	}
	
	@GetMapping(value = "/user/user-IdCheck.do",produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public ResponseEntity<String> userIdCheck(@RequestParam("userId") String userId){
		log.info("userIdCheck");
		boolean result = false;
		result = userService.selectUser(userId) == null ? false : true;
		return new ResponseEntity<>(result + "", HttpStatus.OK);
	}
	
	@PostMapping("/user/user-joinProc.do")
	public String userJoinProc(@ModelAttribute UserVO userVO, Model model) {
		log.info("userJoinProc");
		String msg = ""; //메시지
		String movePath = ""; //이동경로
		
		//생일 필드 치환 
        java.util.Date date = new java.util.Date();
	    date = new java.util.Date(java.sql.Date.valueOf(userVO.getUserBirthStr()).getTime());
		userVO.setUserBirth(date);
		
		//고객번호 발행:2022***(년도뒤 세자리 번호 ex.2022029)
		Calendar cal = Calendar.getInstance();
		String thisYear = cal.get(Calendar.YEAR)+"";
		
		int num = userService.selectMaxUserIndex()+1; 
		String newUserIndex = thisYear + (num < 100 ? "0" + num : num +"");
		userVO.setUserIndex(newUserIndex);
		
		//패스워드 암호화
		BCryptPasswordEncoder bce = new BCryptPasswordEncoder();
		userVO.setUserPw(bce.encode(userVO.getUserPw()));
		
		log.info("userVO(join) ="+ userVO);
		
		//회원정보 저장
		if (userService.insertUser(userVO) == true) {
			msg = "회원정보 저장에 성공하셨습니다."; 
			movePath = "/user/user-login.do";
		} else {
			msg = "회원정보 저장에 실패하였습니다."; 
			movePath = "/user/user-join.do";
		}
		
		model.addAttribute("errMsg", msg);
		model.addAttribute("movePath", movePath);
		
		return "/error/error";
	}
	
	@GetMapping("/user/user-view.do")
	public String userView(HttpSession session, Model model) {
		log.info("userView");
		String userId = session.getAttribute("SESS_LOGIN_ID").toString();
		model.addAttribute("user", userService.selectUser(userId));
		return "/user/user-view";
	}
	
	@GetMapping("/user/user-mypageHistory.do")
	public String userMypageHistory() {
		log.info("userMypageHistory");
		return "/user/user-mypageHistory";
	}
	
	@GetMapping("/user/user-mypageInquiry.do")
	public String userMypageInquiry() {
		log.info("userMypageInquiry");
		return "/user/user-mypageInquiry";
	}
	
	@GetMapping("/user/user-mypageModify.do")
	public String userMypageModify(HttpSession session) {
		log.info("userMypageModify");
		String userId = session.getAttribute("SESS_LOGIN_ID").toString();
		UserVO userVO = userService.selectUser(userId);
		session.setAttribute("defaultUser", userVO); //기존 정보
		return "/user/user-mypageModify";
	}
	
	@PostMapping("/user/user-mypageModifyProc.do")
	public String userMypageModifyProc(@ModelAttribute UserVO userVO, Model model,HttpSession session) {
		log.info("userMypageModifyProc");
		String msg = ""; //메시지
		String movePath = ""; //이동경로
		
		//패스워드 암호화
		BCryptPasswordEncoder bce = new BCryptPasswordEncoder();
		userVO.setUserPw(bce.encode(userVO.getUserPw()));
		
		log.info("userVO(update) ="+ userVO);
		
		//회원정보 수정
		if (userService.updateUser(userVO) == true) {
			msg = "회원정보 수정에 성공하셨습니다."; 
			movePath = "/user/user-mypageModify.do";
			session.removeAttribute("defaultUser");//기존 정보 세션 삭제
		} else {
			msg = "회원정보 수정에 실패하였습니다."; 
			movePath = "/user/user-mypageModify.do";
		}
		
		model.addAttribute("errMsg", msg);
		model.addAttribute("movePath", movePath);
		
		return "/error/error";
	}
	
	@GetMapping("/user/user-mypageModifyBeforeCheck.do")
	public String userMypageModifyBeforeCheck() {
		log.info("userMypageModifyBeforeCheck");
		return "/user/user-mypageModifyBeforeCheck";
	}
	
	@GetMapping("/user/user-delete.do")
	public String userDelete(@RequestParam(value = "userId", required = true) String userId, HttpSession session,
			Model model) {
		log.info("userDelete");
		log.info("userId=" + userId);
		String msg = ""; //메시지
		String movePath = ""; //이동경로
		
		//회원 정보 삭제 

		if (userService.deleteUser(userId) == true) {
			msg = "회원정보 삭제에 성공하셨습니다."; 
			movePath = "/user/user-login.do";
			session.invalidate();//사용자 세션 종료 (로그아웃)
		} else {
			msg = "회원정보 삭제에 실패하였습니다."; 
			movePath = "/user/user-mypageOrder.do";
		}
		
		model.addAttribute("errMsg", msg);
		model.addAttribute("movePath", movePath);
		
		return "/error/error";
	}
}
