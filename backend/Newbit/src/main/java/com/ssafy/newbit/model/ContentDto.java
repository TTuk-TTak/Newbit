package com.ssafy.newbit.model;

import lombok.*;

@Getter
@Setter
public class ContentDto {
	private int postCode;
	private int userCode;
	private int contentCode;
	private int techblogCode;
	private String postText;
	private int postLike;
	private int postComment;
	private String postDate;
	private int postScrap;
	//private int postReport;
	private boolean postEdit;
}
