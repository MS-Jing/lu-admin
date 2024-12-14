package com.lj.common;


import cn.hutool.extra.spring.EnableSpringUtil;
import com.lj.common.module.ModuleInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lj.common")
@EnableSpringUtil
public class CommonAutoConfiguration {

    @Bean
    public ModuleInfo commonModuleInfo(){
        return new ModuleInfo().setModuleName("common");
    }
}