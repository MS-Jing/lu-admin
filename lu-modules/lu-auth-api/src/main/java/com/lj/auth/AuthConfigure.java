package com.lj.auth;

import cn.hutool.core.util.ClassUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @since 2024/11/15 11:10
 */
@Configuration
public class AuthConfigure {
    @Bean
    public GroupedOpenApi authApi() {
        // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("auth")
                .displayName("认证模块")
                .packagesToScan(ClassUtil.getPackage(AuthConfigure.class) + ".controller")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }
}