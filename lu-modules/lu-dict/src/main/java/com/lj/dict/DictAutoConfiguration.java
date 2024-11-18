package com.lj.dict;

import com.lj.common.module.ModuleInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @date 2024/11/15
 */
@Configuration
@ComponentScan("com.lj.dict")
@MapperScan("com.lj.dict.mapper")
public class DictAutoConfiguration {

    @Bean
    public ModuleInfo dictModuleInfo() {
        return new ModuleInfo().setModuleName("dict");
    }
}
