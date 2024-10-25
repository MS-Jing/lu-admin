package com.lj.mp.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lj.mp.constant.MpConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @author luojing
 * @since 2024/10/25 18:04
 * mp自动填充处理器
 */
@Configuration
@ConditionalOnClass(MetaObjectHandler.class)
public class MyBatisPlusFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.fillStrategy(metaObject, MpConstant.CREATE_TIME, now);
        this.fillStrategy(metaObject, MpConstant.UPDATE_TIME, now);
        this.fillStrategy(metaObject, MpConstant.VERSION, 0);
        this.fillStrategy(metaObject, MpConstant.DELETED, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fillStrategy(metaObject, MpConstant.UPDATE_TIME, LocalDateTime.now());
    }
}
