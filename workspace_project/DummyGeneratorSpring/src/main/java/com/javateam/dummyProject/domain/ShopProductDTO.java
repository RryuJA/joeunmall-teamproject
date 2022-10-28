package com.javateam.dummyProject.domain;

import lombok.Data;

@Data
public class ShopProductDTO {
	
	/** 사이즈 */
	private String size;
	
	/** 컬러 */
	private String colors;
	
	/** 모델 */
	private String model;
	
	/** 소재 */
	private String materials;
	
	/** 제조국 */
	private String country;
	
	/** 제조연월 (yearOfManufacture) */
	private String yom;
	
	/** 품질 보증 기준 (qualityAssuranceStandards;) */
	private String qas;  
	
	
	

}
