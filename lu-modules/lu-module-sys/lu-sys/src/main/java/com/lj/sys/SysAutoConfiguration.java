package com.lj.sys;

import com.lj.common.module.ModuleInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @since 2024/8/5 17:54
 */
@Configuration
@ComponentScan("com.lj.sys")
@MapperScan("com.lj.sys.mapper")
public class SysAutoConfiguration {

    @Bean
    public ModuleInfo sysModuleInfo(){
        return new ModuleInfo().setModuleName("sys");
    }

}
