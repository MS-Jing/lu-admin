package com.lj.generator;

import com.lj.common.module.ModuleInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author luojing
 * @since 2024/11/11 16:27
 */
@Configuration
@ComponentScan("com.lj.generator")
@MapperScan("com.lj.generator.mapper")
@Profile("dev")
public class GeneratorAutoConfiguration {

    @Bean
    public ModuleInfo generatorModuleInfo() {
        return new ModuleInfo().setModuleName("generator");
    }
}
