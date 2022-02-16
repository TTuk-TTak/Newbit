package com.ssafy.newbit.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel(value = "DailyGraphDto : 데일리 그래프 정보", description = "데일리 그래프의 정보를 나타낸다.")
@Getter
@Setter
@AllArgsConstructor
//@Data
public class DailyGraphDto {
	private List<Integer> Sun;
	private List<Integer> Mon;
	private List<Integer> Tue;
	private List<Integer> Wed;
	private List<Integer> Thu;
	private List<Integer> Fri;
	private List<Integer> Sat;
}
