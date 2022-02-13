package com.ssafy.newbit.model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "CommentDto : 댓글정보", description = "댓글 정보를 나타낸다.")
public class CommentDto {
	
	@ApiModelProperty(value = "댓글 번호")
	private int commentCode;
	@ApiModelProperty(value = "댓글 작성자")
	private int userCode;
	@ApiModelProperty(value = "댓글이 달린 글 번호")
	private int postCode;
	@ApiModelProperty(value = "댓글 작성일시")
	private String commentDate;
	@ApiModelProperty(value = "댓글 내용")
	private String commentText;
	//private int commentReport;
	@ApiModelProperty(value = "댓글과 답글 구분")
	private boolean commentDepth;
	@ApiModelProperty(value = "답글이 달린 원 댓글 번호")
	private int commentParent;
}
