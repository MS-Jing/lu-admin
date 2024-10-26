package com.lj.mp.log;

/**
 * @author luojing
 * @date 2024/10/26
 */
public class MpLogHelper {
    private static final ThreadLocal<Boolean> CONTEXT = new ThreadLocal<>();

    /**
     * 获得当前线程是否打开mp日志
     *
     * @return 是否打开mp日志
     */
    public static Boolean peek() {
        Boolean enable = CONTEXT.get();
        return enable != null && enable;
    }

    /**
     * 设置当前线程是否打开mp日志
     *
     * @param enable 是否打开mp日志
     */
    public static void push(Boolean enable) {
        CONTEXT.set(enable);
    }

    /**
     * 清空当前线程
     */
    public static void poll() {
        CONTEXT.remove();
    }
}
