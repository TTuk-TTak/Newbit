package com.ssafy.newbit.model;

import lombok.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@ApiModel(value = "PostDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class PostDto {
	
	@ApiModelProperty(value = "게시글 번호")
	private int postCode;
	@ApiModelProperty(value = "게시글 작성자")
	private int userCode;
	@ApiModelProperty(value = "게시글에 포함된 콘텐츠 번호")
	private int contentCode;
	@ApiModelProperty(value = "게시글에 포함된 콘텐츠의 테크블로그 번호")
	private int techblogCode;
	@ApiModelProperty(value = "게시글 내용")
	private String postText;
	@ApiModelProperty(value = "게시글 좋아요 수")
	private int postLike;
	@ApiModelProperty(value = "게시글 댓글 수")
	private int postComment;
	@ApiModelProperty(value = "게시글 작성일")
	private String postDate;
	@ApiModelProperty(value = "게시글 스크랩 수")
	private int postScrap;
	//private int postReport;
	@ApiModelProperty(value = "게시글 수정 여부")
	private boolean postEdit;
	@ApiModelProperty(value = "유저가 현재 게시글을 좋아요 설정했는지 여부")
	private boolean liked;
	@ApiModelProperty(value = "유저가 현재 컨텐츠를 스크랩했는지 여부")
	private boolean scrapped;
	
	//글 작성자 정보
	@ApiModelProperty(value = "작성자 닉네임")
	private String userNick;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "작성자 사진 url")
	private String userImg;
	
	//좋아요 한 사용자 정보
	private int likeUserCode; //좋아요 한 사용자 코드
	private String likeUserNick; //좋아요 한 사용자 닉
	
	
}
