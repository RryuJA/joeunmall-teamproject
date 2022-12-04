package com.joeun.joeunmall.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joeun.joeunmall.vo.ProductDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {
	
	@Autowired
	private FileSystemResource uploadDirResource;
	
	@Override
	public String storeUploadFile(MultipartFile file, String catePath) {
		
		String result = "";
		FileOutputStream fos = null;
		
		// 저장 경로 확보
	 	String savePath = uploadDirResource.getPath();
		
		log.info("##### 자원 경로(업로드) : " + savePath);
	 	
		// 업로드 파일 처리
	 	// 첨부 파일이 없을 때
		if (file.isEmpty() || file == null) {
			
			result = "첨부 파일이 없습니다.";
			log.error(result);
		
		} else { // 파일 유효성 점검
			
			// 저장 폴더 존재 점검
			Path path = Paths.get(savePath);
			 
			if (Files.exists(path)) {
				
				log.info("파일 업로드 저장소(폴더)가 존재합니다.");
				 
			} else {
				
				result = "파일 업로드 저장소(폴더)가 존재하지 않습니다.";
				log.error(result);
				 
				// 폴더 생성
				try {
					Files.createDirectory(path);	
				} catch (Exception e) {
					result = "파일 업로드 저장소(폴더)가 생성되지 않았습니다.";
					log.error(result);
				}
			} //
		   
		     
		    try {
	    	 	// 업로드 파일 형식 변환(시작) : 추가
		    	String filename = file.getOriginalFilename();
				log.info("실제 업로드 파일명 : {}", filename);
				// 업로드 파일 형식 변환(끝) : 추가
				// 기존파일 존재시에 삭제 후 신규 업로드 파일 저장
				
				if(Files.exists(Paths.get(savePath + "/" + catePath + "/" + file.getOriginalFilename()))== true) {
					Files.delete(Paths.get(savePath + "/" + catePath + "/" + file.getOriginalFilename()));
					log.info("기존 업로드 파일 삭제");
				}
				
	    	 	byte[] bytes = file.getBytes();
	    	 	
	    	 	log.info("### savePath : " + savePath);

	            File outFileName = new File(savePath + "/" + catePath + "/" + filename);
	            fos = new FileOutputStream(outFileName);
	             
	            fos.write(bytes);
	            
	            // 추가 : docker 런칭시 파일 권한 변경
	            log.info("파일 읽기 모드 점검 : " 
	            		+ Files.isReadable(Paths.get(savePath + "/" + catePath + "/" + filename)));
	            
	            result = "파일이 업로드 되었습니다.";	
	            
	            log.info("______________");
		         
		    } catch (IOException e) {
		    	 
		    	result = "파일 처리중 오류가 발생하였습니다. ";
		        log.error(result);
		        e.printStackTrace();
		         		        		 
		    } finally { // 자원 반납
		           
				try {    
				    if (fos!=null) fos.close();
				       
				} catch (IOException e) {
					result = "파일 처리중 오류가 발생하였습니다. ";
					log.error("FileUploadService storeUploadFile IOE : " + result);
				    e.printStackTrace();
				}
				 
		    } // try
		     
		} // 업로드 파일 처리
		
		return result;
	}

	@Override
	public boolean isValidUploadFile(MultipartFile file) {

		log.info("업로드 유효 파일 점검");
		boolean result = false;
		
		//파일객체 = null(선택되지 않은 파일)이거나 파일 이름 = 공백(선택되었지만 업로드 되지 않은 파일필드) >> 제외
		result = (file==null || file.isEmpty()) == true ? false : file.getOriginalFilename().equals("") ? false : true;
		return result;
	}

	@Override
	public String storeUploadFile(MultipartFile file, String filename, String catePath) {
			
			String result = "";
			FileOutputStream fos = null;
			
			// 저장 경로 확보
		 	String savePath = uploadDirResource.getPath();
			
			log.info("##### 자원 경로(업로드) : " + savePath);
		 	
			// 업로드 파일 처리
		 	// 첨부 파일이 없을 때
			if (file.isEmpty() || file == null) {
				
				result = "첨부 파일이 없습니다.";
				log.error(result);
			
			} else { // 파일 유효성 점검
				
				// 저장 폴더 존재 점검
				Path path = Paths.get(savePath);
				 
				if (Files.exists(path)) {
					
					log.info("파일 업로드 저장소(폴더)가 존재합니다.");
					 
				} else {
					
					result = "파일 업로드 저장소(폴더)가 존재하지 않습니다.";
					log.error(result);
					 
					// 폴더 생성
					try {
						Files.createDirectory(path);	
					} catch (Exception e) {
						result = "파일 업로드 저장소(폴더)가 생성되지 않았습니다.";
						log.error(result);
					}
				} //
			   
			     
			    try {
		    	 	// 업로드 파일 형식 변환(시작) : 추가
					log.info("실제 업로드 파일명 : {}", filename);
					// 업로드 파일 형식 변환(끝) : 추가
					// 기존파일 존재시에 삭제 후 신규 업로드 파일 저장
					
					if(Files.exists(Paths.get(savePath + "/" + filename))== true) {
						Files.delete(Paths.get(savePath + "/" + filename));
						log.info("기존 업로드 파일 삭제");
					}
					
		    	 	byte[] bytes = file.getBytes();
		    	 	
		    	 	log.info("### savePath : " + savePath);

		            File outFileName = new File(savePath + "/" + filename);
		            fos = new FileOutputStream(outFileName);
		             
		            fos.write(bytes);
		            
		            // 추가 : docker 런칭시 파일 권한 변경
		            log.info("파일 읽기 모드 점검 : " 
		            		+ Files.isReadable(Paths.get(savePath + "/" + filename)));
		            
		            result = "파일이 업로드 되었습니다.";	
		            
		            log.info("______________");
			         
			    } catch (IOException e) {
			    	 
			    	result = "파일 처리중 오류가 발생하였습니다. ";
			        log.error(result);
			        e.printStackTrace();
			         		        		 
			    } finally { // 자원 반납
			           
					try {    
					    if (fos!=null) fos.close();
					       
					} catch (IOException e) {
						result = "파일 처리중 오류가 발생하였습니다. ";
						log.error("FileUploadService storeUploadFile IOE : " + result);
					    e.printStackTrace();
					}
					 
			    } // try
			     
			} // 업로드 파일 처리
			
			return result;
		}

	@Override
	public String setUploadFileName(ProductDTO productDTO, boolean isThumbnail, int index) {
		String result="";
		//카테고리별 업로드 파일명 규칙(일정) ex)01_tshirt/22_01_002_thumbnail.jpg   
		//ex)01_tshirt/22_01_005_thumbnail.jpg
		//productCategoryIndex >> 01 >> 01_tshirt
		//productIndex >> 22_01_005
		//대표이미지 = thumbnail 접미사 첨가 , 그외에는 미첨가
		//uploadFiles5 >> 005 
		//확장자는 jpg만 사용
		
		
		String cate = productDTO.getProductCategoryIndex().equals("01") ? "01_tshirt" : 
					  productDTO.getProductCategoryIndex().equals("02") ? "02_pants" :
				      productDTO.getProductCategoryIndex().equals("03") ? "03_onepiece" :
					  productDTO.getProductCategoryIndex().equals("04") ? "04_cardigan" : "05_jacket";

		result = cate + "/" + productDTO.getProductIndex() + (isThumbnail==true ? "_thumbnail" : "_" + index) + ".jpg";
		
		log.info("저장될 파일명=" + result);
		
		return result;
	}

	@Override
	public String deleteUploadFile(String filename) {
		String errMsg="";
		String savePath = uploadDirResource.getPath();
		
		if(Files.exists(Paths.get(savePath + "/" + filename))== true) {
			try {
				Files.delete(Paths.get(savePath + "/" + filename));
				log.info("기존 업로드 파일 삭제");
				errMsg="기존 업로드 파일 삭제";
			} catch (IOException e) {
				log.error("이미지 파일 삭제 실패");
				e.printStackTrace();
			}
			
		}
		
		return errMsg;
	}

}
