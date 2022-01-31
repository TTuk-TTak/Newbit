package com.ssafy.newbit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel(value = "ContentDto : 콘텐츠 정보", description = "콘텐츠 정보를 나타낸다.")
public class ContentDto {
	@ApiModelProperty(value = "콘텐츠 번호")
	private int contentCode;
	@ApiModelProperty(value = "콘텐츠 출처 기술블로그 번호")
	private int techblogCode;
	@ApiModelProperty(value = "콘턴츠 제목")
	private String contentTitle;
	@ApiModelProperty(value = "콘텐츠 내용")
	private String contentText;
	@ApiModelProperty(value = "콘텐츠 주소")
	private String contentUrl;
	@ApiModelProperty(value = "콘텐츠에 포함된 이미지 url")
	private String contentImg;
	@ApiModelProperty(value = "콘텐츠 좋아요 수")
	private int contentLike;
	@ApiModelProperty(value = "콘텐츠 작성일")
	private String contentDate;
	@ApiModelProperty(value = "콘텐츠 키워드")
	private String contentKeyword;
	@ApiModelProperty(value = "유저가 현재 컨텐츠를 좋아요 설정했는지 여부")
	private boolean liked;
	@ApiModelProperty(value = "유저가 현재 컨텐츠를 스크랩했는지 여부")
	private boolean scrapped;
	

}
