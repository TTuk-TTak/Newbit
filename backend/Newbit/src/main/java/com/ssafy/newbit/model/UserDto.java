package com.ssafy.newbit.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
@Getter
@Setter
//@Data
public class UserDto {
	@ApiModelProperty(value = "회원 코드")
	private int userCode;
	@ApiModelProperty(value = "이메일")
	private String userEmail;
	@ApiModelProperty(value = "아이디")
	private String userId;
	@ApiModelProperty(value = "닉네임")
	private String userNick;
	@ApiModelProperty(value = "비밀번호")
	private String userPassword;
	@ApiModelProperty(value = "가입일")
	private String userDate;
	@ApiModelProperty(value = "프로필 사진")
	private String userImg;
	@ApiModelProperty(value = "게시글 수")
	private int userPostCount;
	@ApiModelProperty(value = "팔로워 수")
	private int userFollowerCount;
	@ApiModelProperty(value = "팔로잉 수")
	private int userFollowingCount;
	@ApiModelProperty(value = "한줄 소개")
	private String userIntro;
	@ApiModelProperty(value = "관심키워드")
	private String userKeyword;
	@ApiModelProperty(value = "팔로우여부")
	private Boolean isFollow;
	
	/*
	public UserDto(String userEmail, String userId, String userNick, String userPassword) {
		this.userEmail = userEmail;
		this.userId = userId;
		this.userNick = userNick;
		this.userPassword = userPassword;
	}
	
	public UserDto(String userEmail, String userId, String userNick, String userPassword, String userDate) {
		this.userEmail = userEmail;
		this.userId = userId;
		this.userNick = userNick;
		this.userPassword = userPassword;
		this.userDate = userDate;
	}
	
	public UserDto(int userCode, String userId, String userNick, String userPassword, String userImg, String userIntro,
			String userKeyword) {
		this.userCode = userCode;
		this.userId = userId;
		this.userNick = userNick;
		this.userPassword = userPassword;
		this.userImg = userImg;
		this.userIntro = userIntro;
		this.userKeyword = userKeyword;
	}
	
	//select user_id `userId`, user_nick `userNick`, user_img `userImg`, user_postcount `userPostCount`, user_followercount `userFollowerCount`, user_followingcount `userFollowingCount`, user_intro `userIntro`, user_keyword `userKeyword`
	public UserDto(String userId, String userNick, String userImg, int userPostCount, int userFollowerCount, int userFollowingCount, String userIntro, String userKeyword) {
		this.userId = userId;
		this.userNick = userNick;
		this.userImg = userImg;
		this.userPostCount = userPostCount;
		this.userFollowerCount = userFollowerCount;
		this.userFollowingCount = userFollowingCount;
		this.userIntro = userIntro;
		this.userKeyword = userKeyword;
	}*/

	
	
	public int getUserCode() {
		return userCode;
	}

	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDate() {
		return userDate;
	}

	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public int getUserPostCount() {
		return userPostCount;
	}

	public void setUserPostCount(int userPostCount) {
		this.userPostCount = userPostCount;
	}

	public int getUserFollowerCount() {
		return userFollowerCount;
	}

	public void setUserFollowerCount(int userFollowerCount) {
		this.userFollowerCount = userFollowerCount;
	}

	public int getUserFollowingCount() {
		return userFollowingCount;
	}

	public void setUserFollowingCount(int userFollowingCount) {
		this.userFollowingCount = userFollowingCount;
	}

	public String getUserIntro() {
		return userIntro;
	}

	public void setUserIntro(String userIntro) {
		this.userIntro = userIntro;
	}

	public String getUserKeyword() {
		return userKeyword;
	}

	public void setUserKeyword(String userKeyword) {
		this.userKeyword = userKeyword;
	}

	public Boolean getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(Boolean isFollow) {
		this.isFollow = isFollow;
	}
	
}
