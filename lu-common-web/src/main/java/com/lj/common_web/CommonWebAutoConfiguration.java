package com.lj.common_web;

import cn.hutool.core.util.ClassUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lj.common_web")
public class CommonWebAutoConfiguration {


    @Bean
    public GroupedOpenApi commonApi() {
        // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("common")
                .displayName("公共模块")
                .packagesToScan(ClassUtil.getPackage(CommonWebAutoConfiguration.class) + ".controller")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }
}
