package com.joeun.joeunmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.joeunmall.service.FileUploadService;
import com.joeun.joeunmall.service.ProductManageService;
import com.joeun.joeunmall.service.ProductRegistrationService;
import com.joeun.joeunmall.vo.ProductDTO;
import com.joeun.joeunmall.vo.ProductImageVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Team3
 *
 */
@Controller
@Slf4j
@RequestMapping
public class AdminProductUpdateManageController {

	@Autowired
	ProductManageService productManageService; 
	
	@Autowired 
	FileUploadService fileuploadService;

	@Autowired 
	ProductRegistrationService productService;
	
	
	//-----------------------상품 수정, 처리 페이지-----------------------
	@PostMapping("/admin/admin-productUpdate.do")
	public String adminProductUpdate(@ModelAttribute ProductDTO productDTO, Model model) {

		log.info("admin-productUpdate");
		log.info("productDTO =" + productDTO);

		//6개 파일중에서 업로드 유효 파일 점검 
		//파일객체 = null(선택되지 않은 파일)이거나 파일 이름 = 공백(선택되었지만 업로드 되지 않은 파일필드) >> 제외
		//카테고리별 업로드 파일명 규칙(일정) ex)01_tshirt/22_01_002_thumbnail.jpg   
		//ex)01_tshirt/22_01_005_thumbnail.jpg
		//productCategoryIndex >> 01 >> 01_tshirt
		//productIndex >> 22_01_005
		//대표이미지 = thumbnail 접미사 첨가 , 그외에는 미첨가
		//uploadFiles5 >> 005 
		//확장자는 jpg만 사용
		
		//각 업로드 이미지 삭제 여부(uploadImageDeleteYn...)에 따라 삭제 결정
		
		String errMsg = ""; //에러 메시지
		
		if (fileuploadService.isValidUploadFile(productDTO.getUploadFile1()) == true) {//대표 이미지 저장(삭제불가,변경가능)
			String filename = fileuploadService.setUploadFileName(productDTO, true, 0);
			log.info("대표이미지1="+ filename);
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFile1(), filename, productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		}

		String filename = fileuploadService.setUploadFileName(productDTO, false, 1);
		
		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles1()) == true ) {//상품 이미지 1 
			log.info("상품이미지1="+ filename);
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles1(), filename, productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		} else {
			if (productDTO.getUploadImageDeleteYn1().equals("Y")) {
				errMsg = fileuploadService.deleteUploadFile(filename);
			}
		}

	    filename = fileuploadService.setUploadFileName(productDTO, false, 2);
	    
		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles2()) == true ) {//상품 이미지 2 
			
			log.info("상품이미지2="+ filename);
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles2(), filename, productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		} else {
			if (productDTO.getUploadImageDeleteYn2().equals("Y")) {
				errMsg = fileuploadService.deleteUploadFile(filename);
			}
		}

		filename = fileuploadService.setUploadFileName(productDTO, false, 3);
		
		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles3()) == true ) {//상품 이미지 3
			
			log.info("상품이미지3="+ filename);
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles3(), filename, productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		} else {
			if (productDTO.getUploadImageDeleteYn3().equals("Y")) {
				errMsg = fileuploadService.deleteUploadFile(filename);
			}
		}

		
		filename = fileuploadService.setUploadFileName(productDTO, false, 4);
		
		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles4()) == true ) {//상품 이미지 4
			
			log.info("상품이미지4="+ filename);
				errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles4(), filename, productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		} else {
			if (productDTO.getUploadImageDeleteYn4().equals("Y")) {
				errMsg = fileuploadService.deleteUploadFile(filename);
			}
		}

		filename = fileuploadService.setUploadFileName(productDTO, false, 5);
		
		if (fileuploadService.isValidUploadFile(productDTO.getUploadFiles5()) == true ) {//상품 이미지 5
			
			log.info("상품이미지5="+ filename);
			errMsg = fileuploadService.storeUploadFile(productDTO.getUploadFiles5(), filename, productService.getCatePath(productDTO.getProductCategoryIndex()));
			log.info("errMsg =" + errMsg);
		} else {
			if (productDTO.getUploadImageDeleteYn5().equals("Y")) {
				errMsg = fileuploadService.deleteUploadFile(filename);
			}
		}

		log.info("errMsg =" + errMsg);

		/////////////////////////////////////////////////////////////////////////////////////////////////////

		
		// 상품/이미지 DB등록
		// 상품 대표 이미지 (MultipartFile) >> 파일명(String) 추출 >> DB저장
		//productDTO.setUpdateFile1Filename(productDTO.getUploadFile1().getOriginalFilename());
		productDTO.setUpdateFile1Filename(fileuploadService.setUploadFileName(productDTO, true, 0));
		
		String productImageIndex = productDTO.getProductIndex() + "_" + 1;
		
		if (productService.updateProduct(productDTO) == true) {

			//최대 5개 이미지 저장(상품 옵션) 
			ProductImageVO productImageVO = new ProductImageVO();

			//저장할 상품 옵션이 있을 때 
			if (productDTO.getUploadFiles1() != null) {

				productImageVO.setProductDetailImage(fileuploadService.setUploadFileName(productDTO, false, 1));
				productImageVO.setProductIndex(productDTO.getProductIndex());
				
				//productImageIndex 1번부터 가도록 수정 
				
				productImageVO.setProductImageIndex(productImageIndex);
				
				log.info("productImageIndex1="+productImageVO.getProductImageIndex());

				//DB에서 이미지 등록 점검
				if(productService.selectProductImageByImageIndex(productImageIndex)!=null) {//수정/삭제
					log.info("이미지 1 수정/삭제");
					boolean flag1 = false;
					flag1 = productService.updateProductImages(productImageVO);
					log.info("flag1=" + flag1);

					if ( flag1 == false) {
						model.addAttribute("errMsg", "상품 이미지 (1) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}else {//이미지 생성
					log.info("이미지 1 생성");
					boolean flag1 = productService.insertProductImages(productImageVO);
					log.info("flag1=" + flag1);
	
					if ( flag1 == false) {
						model.addAttribute("errMsg", "상품 이미지 (1) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}//
				
			} else {//삭제
				if (productDTO.getUploadImageDeleteYn1().equals("Y")) {
					log.info("이미지 1 삭제");
					log.info("flag1=" + productService.deleteProductImages(productImageIndex));
				} 
			}
			//-----------------------------------------------------------------------------------------
			

			productImageIndex = productDTO.getProductIndex() + "_" + 2;
			
			if(productDTO.getUploadFiles2() != null) {

				productImageVO = new ProductImageVO();
				
				productImageVO.setProductDetailImage(fileuploadService.setUploadFileName(productDTO, false, 2));
				productImageVO.setProductIndex(productDTO.getProductIndex());

				//productImageIndex 2번부터 가도록 수정 
				
				productImageVO.setProductImageIndex(productImageIndex);
				
				log.info("productImageIndex2="+productImageVO.getProductImageIndex());

				//DB에서 이미지 등록 점검
				if(productService.selectProductImageByImageIndex(productImageIndex)!=null) {//수정
					log.info("이미지 2 수정/삭제");
					boolean flag2 = false;
					flag2 = productService.updateProductImages(productImageVO);
					log.info("flag2=" + flag2);

					if ( flag2 == false) {
						model.addAttribute("errMsg", "상품 이미지 (2) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}else {//이미지 생성
					log.info("이미지 2 생성");
					boolean flag2 = productService.insertProductImages(productImageVO);
					log.info("flag2=" + flag2);
	
					if ( flag2 == false) {
						model.addAttribute("errMsg", "상품 이미지 (2) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}//
			} else {//삭제
				if (productDTO.getUploadImageDeleteYn2().equals("Y")) {
					log.info("이미지 2 삭제");
					log.info("flag2=" + productService.deleteProductImages(productImageIndex));
				} 
			}		
//--------------------------------------------------------------------------------------------------------------
			
			productImageIndex = productDTO.getProductIndex() + "_" + 3;
			
			if (productDTO.getUploadFiles3() != null) {

				productImageVO = new ProductImageVO();
				
				productImageVO.setProductDetailImage(fileuploadService.setUploadFileName(productDTO, false, 3));
				productImageVO.setProductIndex(productDTO.getProductIndex());

				//productImageIndex 3번부터 가도록 수정 
				
				productImageVO.setProductImageIndex(productImageIndex);
				
				log.info("productImageIndex3="+productImageVO.getProductImageIndex());
				
				
				//DB에서 이미지 등록 점검
				if(productService.selectProductImageByImageIndex(productImageIndex)!=null) {//수정
					log.info("이미지 3 수정/삭제");
					boolean flag3 = false;
					flag3 = productService.updateProductImages(productImageVO);
					log.info("flag3=" + flag3);

					if ( flag3 == false) {
						model.addAttribute("errMsg", "상품 이미지 (3) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}else {//이미지 생성
					log.info("이미지 3 생성");
					boolean flag3 = productService.insertProductImages(productImageVO);
					log.info("flag3=" + flag3);
	
					if ( flag3 == false) {
						model.addAttribute("errMsg", "상품 이미지 (3) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}//
			} else {//삭제
				if (productDTO.getUploadImageDeleteYn3().equals("Y")) {
					log.info("이미지 3 삭제");
					log.info("flag3=" + productService.deleteProductImages(productImageIndex));
				} 
			}

			//--------------------------------------------------------------------------------------------------------------
			
			productImageIndex = productDTO.getProductIndex() + "_" + 4;
			
			if (productDTO.getUploadFiles4() != null) {
				
				productImageVO = new ProductImageVO();

				productImageVO.setProductDetailImage(fileuploadService.setUploadFileName(productDTO, false, 4));
				productImageVO.setProductIndex(productDTO.getProductIndex());
				
				//productImageIndex 4번부터 가도록 수정 
				
				productImageVO.setProductImageIndex(productImageIndex);
				
				log.info("productImageIndex4="+productImageVO.getProductImageIndex());
				
				//DB에서 이미지 등록 점검
				if(productService.selectProductImageByImageIndex(productImageIndex)!=null) {//수정
					log.info("이미지 4 수정/삭제");

					boolean flag4 = false;
					flag4 = productService.updateProductImages(productImageVO);
					log.info("flag4=" + flag4);

					if ( flag4 == false) {
						model.addAttribute("errMsg", "상품 이미지 (4) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}else {//이미지 생성
					log.info("이미지 4 생성");
					boolean flag4 = productService.insertProductImages(productImageVO);
					log.info("flag4=" + flag4);
	
					if ( flag4 == false) {
						model.addAttribute("errMsg", "상품 이미지 (4) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}//
				
			} else {//삭제
				if (productDTO.getUploadImageDeleteYn4().equals("Y")) {
					log.info("이미지 4 삭제");
					log.info("flag4=" + productService.deleteProductImages(productImageIndex));
				} 
			}

			//--------------------------------------------------------------------------------------------------------------
			
			productImageIndex = productDTO.getProductIndex() + "_" + 5;
			if (productDTO.getUploadFiles5() != null) {
				
				productImageVO = new ProductImageVO();

				productImageVO.setProductDetailImage(fileuploadService.setUploadFileName(productDTO, false, 5));
				productImageVO.setProductIndex(productDTO.getProductIndex());
				
				//productImageIndex 5번부터 가도록 수정 
				
				productImageVO.setProductImageIndex(productImageIndex);
				
				log.info("productImageIndex5="+productImageVO.getProductImageIndex());
				
				//DB에서 이미지 등록 점검
				if(productService.selectProductImageByImageIndex(productImageIndex)!=null) {//수정
					log.info("이미지 5 수정/삭제");
					
					boolean flag5 = false;
					flag5 = productService.updateProductImages(productImageVO);
					log.info("flag5=" + flag5);

					if ( flag5 == false) {
						model.addAttribute("errMsg", "상품 이미지 (5) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}else {//이미지 생성
					log.info("이미지 5 생성");
					boolean flag5 = productService.insertProductImages(productImageVO);
					log.info("flag5=" + flag5);
	
					if ( flag5 == false) {
						model.addAttribute("errMsg", "상품 이미지 (5) 수정에 실패했습니다.");
						model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
						return "/error/error";
					}
				}//
				
			} else {//삭제
				if (productDTO.getUploadImageDeleteYn5().equals("Y")) {
					log.info("이미지 5 삭제");
					log.info("flag5=" + productService.deleteProductImages(productImageIndex));
				} 
			}

			//////////////////////////////////////////////////////////////////////////////////////////////

		} else {
			model.addAttribute("errMsg", "상품수정에 실패했습니다.");
			model.addAttribute("movePath", "/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex());
			return "/error/error";
		}
		
		//
		return "redirect:/admin/admin-productDetails.do?productIndex="+ productDTO.getProductIndex();
	}
	
	

}
