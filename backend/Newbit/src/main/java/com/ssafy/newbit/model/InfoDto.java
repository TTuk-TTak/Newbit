package com.ssafy.newbit.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Getter
@Setter
@AllArgsConstructor
public class InfoDto {
	private int users;
	private int posts;
	private int contents;
}
