package com.lj.generator;

import cn.hutool.core.util.ClassUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @since 2024/8/13 10:58
 */
@Configuration
public class GeneratorConfigure {

    @Bean
    public GroupedOpenApi genApi() {
        // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("gen")
                .displayName("生成器模块")
                .packagesToScan(ClassUtil.getPackage(GeneratorConfigure.class) + ".controller")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }
}
