package com.ssafy.newbit.model;

import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Getter
@Setter


@ApiModel(value = "PostTextDto : 게시글 수정 정보", description = "게시글을 수정하기 위해 필요한 정보를 나타낸다.")
public class PostTextDto {
	@ApiModelProperty(value = "수정할 게시글 번호")
	private int postCode;
	@ApiModelProperty(value = "수정할 게시글 내용")
	private String postText;
}
