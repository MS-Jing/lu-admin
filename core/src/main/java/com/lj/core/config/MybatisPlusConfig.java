package com.lj.core.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.lj.core.Injector.MybatisPlusSqlInjector;
import com.lj.core.constant.CoreConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/20 11:49
 * todo 能不能由配置文件配置要扫描的mapper?
 */
@Configuration
@MapperScan("com.lj.**.mapper")
@ConditionalOnClass(MybatisConfiguration.class)
public class MybatisPlusConfig implements MetaObjectHandler {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.fillStrategy(metaObject, CoreConstant.CREATE_TIME, now);
        this.fillStrategy(metaObject, CoreConstant.UPDATE_TIME, now);
        this.fillStrategy(metaObject, CoreConstant.VERSION, 0);
        this.fillStrategy(metaObject, CoreConstant.DELETED, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, CoreConstant.UPDATE_TIME, LocalDateTime.now());
    }

    @Bean
    public MybatisPlusSqlInjector mybatisPlusSqlInjector() {
        return new MybatisPlusSqlInjector();
    }
}
