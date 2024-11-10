package com.lj.app;

import cn.hutool.core.util.ClassUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author luojing
 * @since 2024/8/9 15:27
 */

//# Controller注解更新
//@Api → @Tag
//@ApiSort → @ApiSupport
//
//# 类接口注解更新
//@ApiIgnore→@Parameter(hidden = true)或@Operation(hidden = true)或@Hidden
//@ApiImplicitParam → @Parameter
//@ApiImplicitParams → @Parameters
//@ApiOperation(value = "foo", notes = "bar") → @Operation(summary = "foo", description = "bar")
//@ApiResponse(code = 404, message = "foo") → @ApiResponse(responseCode = "404", description = "foo")
//
//# 实体类注解更新
//@ApiModel → @Schema
//@ApiModelProperty(hidden = true) → @Schema(accessMode = READ_ONLY)
//@ApiModelProperty → @Schema
//@ApiParam → @Parameter
@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Bean
    public GroupedOpenApi appApi() {
        // 创建了一个api接口的分组
        return GroupedOpenApi.builder()
                .group("app")
                .displayName("app模块")
                .packagesToScan(ClassUtil.getPackage(AppApplication.class) + ".controller")
                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Operation.class))
                .build();
    }

}
