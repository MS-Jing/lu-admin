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

    private static final String CREATE_USER = LambdaUtil.getFieldName(SysStandardEntity::getCreateUser);
    private static final String UPDATE_USER = LambdaUtil.getFieldName(SysStandardEntity::getUpdateUser);

    @Override
    public void insertFill(MetaObject metaObject) {
        super.insertFill(metaObject);
        this.fillStrategy(metaObject, CREATE_USER, getUserId());
        this.fillStrategy(metaObject, UPDATE_USER, getUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        super.updateFill(metaObject);
        this.fillStrategy(metaObject, UPDATE_USER, getUserId());
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
