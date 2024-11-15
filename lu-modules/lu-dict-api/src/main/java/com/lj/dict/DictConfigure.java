package com.lj.dict;

import cn.hutool.core.util.ClassUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @date 2024/11/15
 */
@Configuration
public class DictConfigure {
    @Bean
    public GroupedOpenApi dictApi() {
        // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("dict")
                .displayName("字典模块")
                .packagesToScan(ClassUtil.getPackage(DictConfigure.class) + ".controller")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }
}
