package com.lj.sys;

import cn.hutool.core.util.ClassUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @since 2024/8/13 10:58
 */
@Configuration
public class SysConfigure {

    @Bean
    public GroupedOpenApi sysApi() {
        // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("sys")
                .displayName("系统模块")
                .packagesToScan(ClassUtil.getPackage(SysConfigure.class) + ".controller")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }
}
