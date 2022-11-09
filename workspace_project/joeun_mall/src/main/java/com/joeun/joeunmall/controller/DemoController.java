/**
 * 
 */
package com.joeun.joeunmall.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.vo.CartVO;
import com.joeun.joeunmall.vo.InquiryDTO;
import com.joeun.joeunmall.vo.InquiryVO;
import com.joeun.joeunmall.vo.OrderVO;
import com.joeun.joeunmall.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
public class DemoController {
	
	@GetMapping("/")
	public String demo(Model model) {
		log.info("demo");
		// model.addAttribute("demo", "team3");
		//return "redirect:/admin/admin-productManage.do";
		return  "redirect:/admin/admin-login.do";
//		return "demo";
	}
	
	@GetMapping("/mypage.do")
	public String mypage() {
		log.info("mypage");
		return "mypage";
	}

	@GetMapping("/admin/admin-productRegistration.do")
	public String adminProductRegistration() {
		log.info("admin-productRegistration");
		return "/admin/admin-productRegistration";
	}
	
	@GetMapping("/admin/admin-login.do")
	public String adminLogin() {
		log.info("admin-login");
		return "/admin/admin-login";
	}
	
	@PostMapping("/admin/admin-loginProc.do")
	public String adminLoginProc(@RequestParam(value = "id",required = true) String id, 
		@RequestParam(value = "pw", required = true) String pw, 
		 Model model,HttpSession session) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPw = passwordEncoder.encode("joeun1234");
		boolean checkAuth = passwordEncoder.matches(pw.trim(), hashedPw);
		
		//관리자 인지 점검
		if ((id.trim().equals("admin") && checkAuth == true) ||  // 관리자
				(id.trim().equals("user") && checkAuth == true)) // 일반 회원
		{
			log.info("회원&관리자");
			//세션 생성
			if (session.getAttribute("SESS_LOGIN_ID")==null){
				session.setAttribute("SESS_LOGIN_ID", id);
			}
			return "redirect:/mypage.do";	//나중에 회원이 갈 수 있는 페이지로 바꿔주면 댐 
		
		} else if ((id.trim().equals("admin") && checkAuth == false) ||  // 관리자
				(id.trim().equals("user") && checkAuth == false)) // 일반 회원{
		{
			model.addAttribute("err_msg", "패스워드가 일치하지 않습니다.");
			return "/admin/admin-login";
			
		} else { 
			model.addAttribute("err_msg", "회원이 아닙니다.");
			return "/admin/admin-login";
		}
	}
	
	/* 메인페이지 로그아웃 */
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logoutMainGET(HttpSession session) throws Exception{
		
		log.info("로그아웃");
		if(session.getAttribute("SESS_LOGIN_ID")!=null) {
			session.invalidate();
		}
		return "redirect:/admin/admin-login.do";
	}
	
	@GetMapping("/admin/admin-orderDetails.do")
	public String adminOrderDetails(Model model) throws ParseException {
		log.info("admin-orderDetails");
		OrderVO orderVO = new OrderVO();
		orderVO.setOrderName("이XX");
		orderVO.setOrderIndex("220907_002");
		orderVO.setOrderStateIndex("판매자 확인중");	
		String dateString="2022-09-07 11:36:45";
		Date date = new java.sql.Date( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString).getTime());
		orderVO.setOrderDate(date);
		orderVO.setOrderPrice("90,000");
		orderVO.setOrderAddress("(00000) 서울 강남구...");
		orderVO.setOrderAddressDetail("1023호 (상세주소 입력란)");
		orderVO.setInquiryState("답변완료");
		orderVO.setInquiryTitle("문의제목");
		orderVO.setInquiryContent("문의내용");		
		List<CartVO> cartList = new ArrayList<>();
		CartVO cartVO;
		cartVO = CartVO.builder().cartIndex("0000000_CA1").userIndex("abcdefg")
					.productIndex("00_00_001").productOptionIndex("00_00_001_CA1").productCount(1)
					.productName("카라넥스트라이프티셔츠 (BLACK, 95)").productPrice(20000).build();
		cartList.add(cartVO);
		cartVO = CartVO.builder().cartIndex("0000000_CA2").userIndex("abcdefg")
				.productName("조직감 반넥 니트 베스트(BLACK, 95)").productIndex("00_00_002").productOptionIndex("00_00_002_CA2").productCount(2).productPrice(35000).build();
        cartList.add(cartVO);
        orderVO.setCartList(cartList);
		model.addAttribute("orderVO", orderVO);
		return "/admin/admin-orderDetails";
	}
		
	@PostMapping("/admin/admin-orderProc.do")
	public String adminOrderProc(OrderVO orderVO) {
		log.info("admin-orderProc");
		log.info("OrderVO = " + orderVO);
		return "/admin/admin-orderManage";
	}

	
	@GetMapping("/admin/admin-inquiryDetails.do")
	public String adminInquiryDetails(Model model) throws ParseException {
		log.info("admin-inquiryDetails");
		InquiryVO inquiryVO = new InquiryVO();
		inquiryVO.setUserIndex("1234567");
		inquiryVO.setInquiryTitle("문의제목11111111111");
		inquiryVO.setInquiryState("문의접수");
		inquiryVO.setInquiryIndex("220915022");
		String dateString = "2022-09-07 11:36:45";
		Date date = new java.sql.Date( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString).getTime());
		inquiryVO.setInquiryDate(date);
		inquiryVO.setInquiryContent("고객이 문의한 내용");
		inquiryVO.setInquiryCategory("교환/환불");
		inquiryVO.setUserName("이승은");
		inquiryVO.setProductIndex("BLACK, 95");
		inquiryVO.setProductName("카라넥스트라이프티셔츠");
		model.addAttribute("inquiryVO", inquiryVO);
		return "/admin/admin-inquiryDetails";
		
	}
	@PostMapping("/admin/admin-inquiryProc.do")
	public String adminInquiryProc(InquiryDTO inquiryDTO) {
		log.info("admin-inquiryProc");
		log.info("inquiryDTO = " + inquiryDTO);
		return "/admin/admin-inquiryManage";
	}

	
}
