package com.joeun.joeunmall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joeun.joeunmall.service.FileUploadService;
import com.joeun.joeunmall.service.ProductManageService;
import com.joeun.joeunmall.service.ProductRegistrationService;
import com.joeun.joeunmall.vo.PageDTO;
import com.joeun.joeunmall.vo.PageMaker;
import com.joeun.joeunmall.vo.ProductDTO;
import com.joeun.joeunmall.vo.ProductImageVO;
import com.joeun.joeunmall.vo.ProductOptionVO;
import com.joeun.joeunmall.vo.ProductVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
@RequestMapping
public class AdminProductManageController {

	@Autowired
	ProductManageService productManageService; 
	
	@Autowired 
	FileUploadService fileuploadService;

	@Autowired 
	ProductRegistrationService productService;
	
	
	@GetMapping("/productManage.do")
	public String adminProductManage(Model model) {
		log.info("admin-productManage");
		model.addAttribute("admin", "productManage");
		return "redirect:/admin/admin-productManage.do";
	}
		
	@GetMapping("/admin/admin-productManage.do")
	public String adminProductManage(@RequestParam(value="currentPage", defaultValue="1") int currentPage,  
			Model model) {
		log.info("admin-productManage");
		
		PageDTO pageDTO = new PageDTO();
		PageMaker pageMaker = new PageMaker();	
		
		pageDTO.setRecordsPerPage(8);
		int maxNum = productManageService.getAllProductRecordNum(); 
		int maxPage = (int)(maxNum / pageDTO.getRecordsPerPage() + 0.95) + 1;
		pageDTO.setMaxPage(maxPage);
		pageDTO.setCurrentPage(currentPage  < pageDTO.getMaxPage() ? currentPage : pageDTO.getMaxPage());
		
		pageMaker.setPageDTO(pageDTO);
					
		List<ProductVO> productmanageList = productManageService.getAllProductByPaging(pageDTO.getCurrentPage(), pageDTO.getRecordsPerPage());
				
		model.addAttribute("productManageList", productmanageList);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/admin-productManage";
	}
	
	//-------------------------상품등록페이지-------------------------
	@GetMapping("/admin/admin-productRegistration.do")
	public String adminProductRegistration() {

		log.info("admin-productRegistration");

		return "/admin/admin-productRegistration";
	}
	
	@PostMapping("/admin/admin-productRegistration.do")
	public String adminProductRegistration(@ModelAttribute ProductDTO productDTO, Model model) {

		log.info("admin-productRegistration");
		log.info("productDTO =" + productDTO);

		//6개 파일중에서 업로드 유효 파일 점검 
		//파일객체 = null(선택되지 않은 파일)이거나 파일 이름 = 공백(선택되었지만 업로드 되지 않은 파일필드) >> 제외

		String errMsg = ""; //에러 메시지

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFile1()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFile1(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles1()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles1(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles2()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles2(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles3()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles3(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles4()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles4(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles5()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles5(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		log.info("errMsg =" + errMsg);

		/////////////////////////////////////////////////////////////////////////////////////////////////////

		// 상품/이미지 DB등록
		// 상품 대표 이미지 (MultipartFile) >> 파일명(String) 추출 >> DB저장
		productDTO.setUpdateFile1Filename(productDTO.getUploadFile1().getOriginalFilename());

		if (productService.insertProduct(productDTO) == true) {

			//최대 5개 이미지 저장(상품 옵션) 
			ProductImageVO productImageVO = new ProductImageVO();

			//저장할 상품 옵션이 있을 때 
			if (productDTO.getUploadFiles1() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles1().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				// 미등록 상품일 경우 (최초등록상품) >>null 
				String tempImgNum = productService.selectMaxProductImageIndex(productDTO.getProductIndex());
				tempImgNum = tempImgNum == null ? "0" : tempImgNum.split("_")[3];
				int lastProductIndex = Integer.parseInt(tempImgNum);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				boolean flag1 = productService.insertProductImages(productImageVO);
				log.info("flag1=" + flag1);

				if ( flag1 == false) {
					model.addAttribute("errMsg", "상품 이미지 (1) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}

			if (productDTO.getUploadFiles2() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles2().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (2) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}		

			if (productDTO.getUploadFiles3() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles3().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (3) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}

			if (productDTO.getUploadFiles4() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles4().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (4) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}

			if (productDTO.getUploadFiles5() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles5().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (5) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}		

			//////////////////////////////////////////////////////////////////////////////////////////////

		} else {
			model.addAttribute("errMsg", "상품등록에 실패했습니다.");
			model.addAttribute("movePath", "/admin/admin-productRegistration.do");
			return "/error/error";
		}

		//
		return "redirect:/admin/admin-productManage.do";
	}
	
	//-----------------------상품상세,수정페이지-----------------------
	@GetMapping("/admin/admin-productDetails.do")
	public String adminProductDetails(@RequestParam(value="productIndex") String productIndex, Model model) {
		log.info("admin-productDetails");
		
		ProductVO productVO = productManageService.selectProductInfo(productIndex);
		List<ProductImageVO> productImageList = productManageService.selectProductImage(productIndex);
		List<ProductOptionVO> productOptionList = productManageService.selectProductOption(productIndex);
		
		model.addAttribute("productVO", productVO);
		model.addAttribute("productImageList", productImageList);
		model.addAttribute("productOptionList", productOptionList);
		
		return "/admin/admin-productDetails";
	}
	
	/*
	@PostMapping("/admin/admin-productDetails.do")
	public String adminProductDetails(@ModelAttribute ProductDTO productDTO, String productIndex, Model model) {
		log.info("admin-productDetails");
		log.info("productDTO =" + productDTO);

		//6개 파일중에서 업로드 유효 파일 점검 
		//파일객체 = null(선택되지 않은 파일)이거나 파일 이름 = 공백(선택되었지만 업로드 되지 않은 파일필드) >> 제외

		String errMsg = ""; //에러 메시지

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFile1()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFile1(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles1()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles1(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles2()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles2(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles3()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles3(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles4()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles4(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles5()) == true ) {
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles5(), productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		log.info("errMsg =" + errMsg);

		/////////////////////////////////////////////////////////////////////////////////////////////////////

		// 상품/이미지 DB등록
		// 상품 대표 이미지 (MultipartFile) >> 파일명(String) 추출 >> DB저장
		productDTO.setUpdateFile1Filename(productDTO.getUploadFile1().getOriginalFilename());

		if (productService.insertProduct(productDTO) == true) {

			//최대 5개 이미지 저장(상품 옵션) 
			ProductImageVO productImageVO = new ProductImageVO();

			//저장할 상품 옵션이 있을 때 
			if (productDTO.getUploadFiles1() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles1().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				// 미등록 상품일 경우 (최초등록상품) >>null 
				String tempImgNum = productService.selectMaxProductImageIndex(productDTO.getProductIndex());
				tempImgNum = tempImgNum == null ? "0" : tempImgNum.split("_")[3];
				int lastProductIndex = Integer.parseInt(tempImgNum);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				boolean flag1 = productService.insertProductImages(productImageVO);
				log.info("flag1=" + flag1);

				if ( flag1 == false) {
					model.addAttribute("errMsg", "상품 이미지 (1) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}

			if (productDTO.getUploadFiles2() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles2().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (2) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}		

			if (productDTO.getUploadFiles3() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles3().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (3) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}

			if (productDTO.getUploadFiles4() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles4().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (4) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}

			if (productDTO.getUploadFiles5() != null) {

				productImageVO.setProductDetailImage(productDTO.getUploadFiles5().getOriginalFilename());
				productImageVO.setProductIndex(productDTO.getProductIndex());
				// 상품 이미지 번호 22_01_002_3 >> "_"로 분리 >> 마지막 번호(3)을 추출
				int lastProductIndex = Integer.parseInt(productService.selectMaxProductImageIndex(productDTO.getProductIndex()).split("_")[3]);

				String productImageIndex = productDTO.getProductIndex() + "_" + (lastProductIndex+1);
				productImageVO.setProductImageIndex(productImageIndex);

				if (productService.insertProductImages(productImageVO) == false) {
					model.addAttribute("errMsg", "상품 이미지 (5) 등록에 실패했습니다.");
					model.addAttribute("movePath", "/admin/admin-productRegistration.do");
					return "/error/error";
				}
			}		

			//////////////////////////////////////////////////////////////////////////////////////////////

		} else {
			model.addAttribute("errMsg", "상품등록에 실패했습니다.");
			model.addAttribute("movePath", "/admin/admin-productRegistration.do");
			return "/error/error";
		}

		//
		return "redirect:/admin/admin-productManage.do";
	}
	*/
}
