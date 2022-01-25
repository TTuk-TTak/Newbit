package com.ssafy.newbit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.ssafy.newbit.model.mapper"
)
public class DatabaseConfig {}