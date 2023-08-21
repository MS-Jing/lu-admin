package com.lj.core.constant;

import cn.hutool.core.lang.func.LambdaUtil;
import com.lj.core.model.BaseStandardEntity;

/**
 * @author luojing
 * @date 2023/8/21
 */
public interface CoreConstant {

    String CREATE_TIME = LambdaUtil.getFieldName(BaseStandardEntity::getCreateTime);

    String UPDATE_TIME = LambdaUtil.getFieldName(BaseStandardEntity::getUpdateTime);

    String VERSION = LambdaUtil.getFieldName(BaseStandardEntity::getVersion);

    String DELETED = LambdaUtil.getFieldName(BaseStandardEntity::getDeleted);

}
