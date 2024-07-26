package com.lj.common;


import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lj.common")
@EnableSpringUtil
public class CommonAutoConfiguration {
}