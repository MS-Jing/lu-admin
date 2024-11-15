package com.lj.auth;

import com.lj.common.module.ModuleInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @since 2024/11/15 11:07
 */
@Configuration
@ComponentScan("com.lj.auth")
public class AuthAutoConfiguration {
    @Bean
    public ModuleInfo authModuleInfo(){
        return new ModuleInfo().setModuleName("auth");
    }

}