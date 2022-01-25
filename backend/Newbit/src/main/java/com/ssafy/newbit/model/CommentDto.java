package com.ssafy.newbit.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private int commentCode;
	private int userCode;
	private int postCode;
	private String commentTime;
	private String commentText;
	private int commentReport;
	private boolean commentDepth;
	private int commentParent;
}
