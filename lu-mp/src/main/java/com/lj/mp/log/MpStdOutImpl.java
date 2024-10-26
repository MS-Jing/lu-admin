package com.lj.mp.log;

import org.apache.ibatis.logging.stdout.StdOutImpl;

/**
 * @author luojing
 * @date 2024/10/26
 */
public class MpStdOutImpl extends StdOutImpl {
    public MpStdOutImpl(String clazz) {
        super(clazz);
    }

    @Override
    public boolean isDebugEnabled() {
        return MpLogHelper.peek();
    }

    @Override
    public boolean isTraceEnabled() {
        return MpLogHelper.peek();
    }
}
