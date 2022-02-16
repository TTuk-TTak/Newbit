package com.ssafy.newbit.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@ApiModel(value = "RadarGraphDto : 방사형 그래프 정보", description = "방사형 그래프 정보를 나타낸다.")
@Getter
@Setter
@AllArgsConstructor
//@Data
public class RadarGraphDto {
	@ApiModelProperty(value = "카테고리")
	private List<String> category;
	@ApiModelProperty(value = "수")
	private List<Integer> data;
}
