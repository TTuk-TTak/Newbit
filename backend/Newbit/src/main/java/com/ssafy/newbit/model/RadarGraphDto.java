package com.ssafy.newbit.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
@Getter
@Setter
//@Data
public class RadarGraphDto {
	@ApiModelProperty(value = "회원 코드")
	private List<String> category;
	private List<Integer> data;
}
