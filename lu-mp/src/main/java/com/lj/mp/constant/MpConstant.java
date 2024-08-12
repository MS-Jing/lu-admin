package com.lj.mp.constant;

import cn.hutool.core.lang.func.LambdaUtil;
import com.lj.mp.standard.entity.GeneralStandardEntity;

/**
 * @author luojing
 * @date 2023/8/21
 * 一些基础字段的常量
 */
public interface MpConstant {

    String CREATE_TIME = LambdaUtil.getFieldName(GeneralStandardEntity::getCreateTime);

    String UPDATE_TIME = LambdaUtil.getFieldName(GeneralStandardEntity::getUpdateTime);

    String VERSION = LambdaUtil.getFieldName(GeneralStandardEntity::getVersion);

    String DELETED = LambdaUtil.getFieldName(GeneralStandardEntity::getDeleted);

}
