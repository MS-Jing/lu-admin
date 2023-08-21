package com.lj.utils.query.enums;

import com.lj.utils.query.QueryWrapper;
import com.lj.utils.query.WrapperFun;

/**
 * @author luojing
 * @date 2023/8/14
 * 模糊查询的类型
 */
public enum LikeType {
    /**
     * %值
     */
    LEFT {
        @Override
        public <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, boolean not) {
            return not ? queryWrapper::notLikeLeft : queryWrapper::likeLeft;
        }
    },
    /**
     * 值%
     */
    RIGHT {
        @Override
        public <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, boolean not) {
            return not ? queryWrapper::notLikeRight : queryWrapper::likeRight;
        }
    },
    /**
     * %值%
     */
    AROUND {
        @Override
        public <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, boolean not) {
            return not ? queryWrapper::notLike : queryWrapper::like;
        }
    };

    /**
     * @param not 是否取反
     */
    public <T> WrapperFun getFun(QueryWrapper<T> queryWrapper, boolean not) {
        throw new RuntimeException("方法不被支持！");
    }
}
