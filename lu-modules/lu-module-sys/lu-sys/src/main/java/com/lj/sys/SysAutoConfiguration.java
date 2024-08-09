package com.lj.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @since 2024/8/5 17:54
 */
@Configuration
@ComponentScan("com.lj.sys")
@MapperScan("com.lj.sys.*.mapper")
public class SysAutoConfiguration {
}