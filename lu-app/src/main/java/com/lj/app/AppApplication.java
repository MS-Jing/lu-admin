package com.lj.app;

import cn.hutool.core.util.ClassUtil;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * @author luojing
 * @since 2024/8/9 15:27
 */
@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Resource
    private OpenApiExtensionResolver openApiExtensionResolver;


    @Bean(value = "appApi")
    public Docket appApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("app")
                        .description("app")
                        .version("1.0.0")
                        .build())
                .groupName("app")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage(ClassUtil.getPackage(AppApplication.class) + ".controller"))
                .paths(PathSelectors.any())
                .build().extensions(openApiExtensionResolver.buildExtensions("app"));
    }

}
