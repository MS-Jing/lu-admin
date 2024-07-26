package com.lj.common_web.utils.validator;

import javax.validation.GroupSequence;

/**
 * @author luojing
 * @version 1.0
 * @date 2023/3/28 11:08
 * 校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {
}
