package com.ssafy.newbit.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
@Getter
@Setter
@AllArgsConstructor
//@Data
public class DailyGraphDto {
	@ApiModelProperty(value = "개수")
	private int count;
	@ApiModelProperty(value = "날짜")
	private String data;
}
