package com.ssafy.newbit.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel(value = "DailyDataDto : 일일 활동 정보", description = "데일리 그래프에 넣을 정보를 받아오기 위한 일일 활동 정보")
@Getter
@Setter
@AllArgsConstructor
//@Data
public class DailyDataDto {
	@ApiModelProperty(value = "개수")
	private int count;
	@ApiModelProperty(value = "날짜")
	private String data;
}
