package com.ssafy.newbit.model;

import lombok.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
public class NotiDto {
	
	private String type; //타입 : 팔로우, 좋아요, 댓글
	private int userCode; //나한테 흔적을 남긴 userCode
	private String userNick; //나한테 흔적을 남긴 userNick
	private String date; //나한테 흔적 남긴 시간
	private int moving; //front에서 이동해야 하는 곳 code
	private String text; //댓글 또는 게시글 내용
	private boolean isRead; //알림 읽었는지 안 읽었는지
	
}
