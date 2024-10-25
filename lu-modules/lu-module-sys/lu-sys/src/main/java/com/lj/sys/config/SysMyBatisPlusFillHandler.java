package com.lj.sys.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.func.LambdaUtil;
import com.lj.mp.config.MyBatisPlusFillHandler;
import com.lj.sys.standard.SysStandardEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

/**
 * @author luojing
 * @since 2024/10/25 18:10
 */
@Configuration
public class SysMyBatisPlusFillHandler extends MyBatisPlusFillHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        super.insertFill(metaObject);
        this.fillStrategy(metaObject, LambdaUtil.getFieldName(SysStandardEntity::getCreateUser), getUserId());
        this.fillStrategy(metaObject, LambdaUtil.getFieldName(SysStandardEntity::getUpdateUser), getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        super.updateFill(metaObject);
        this.fillStrategy(metaObject, LambdaUtil.getFieldName(SysStandardEntity::getUpdateUser), getUserId());
    }

    /**
     * 获取用户id
     */
    private Long getUserId() {
        try {
            return StpUtil.getLoginIdAsLong();
        } catch (Exception e) {
            return -1L;
        }
    }
}
