package com.ssafy.newbit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel(value = "TechblogDto : 기술블로그 정보", description = "기술블로그 정보를 나타낸다.")
public class TechblogDto {
	@ApiModelProperty(value = "콘텐츠 출처 기술블로그 번호")
	private int techblogCode;
	@ApiModelProperty(value = "기술블로그 이름")
	private String techblogName;
	@ApiModelProperty(value = "기술블로그 로고 이미지 url")
	private String techblogImg;
	
	
}
