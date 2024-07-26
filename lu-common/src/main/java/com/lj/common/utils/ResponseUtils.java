//package com.jcy.common.utils;
//
//import cn.hutool.core.util.ObjUtil;
//import com.jcy.common.exception.BusinessException;
//import com.jcy.common.response.ResponseCode;
//import com.jcy.common.response.ResponseVO;
//
///**
// * @author luojing
// * @version 1.0
// * @date 2023/3/16 9:24
// * 响应工具
// */
//public class ResponseUtils {
//
//    /**
//     * 获取统一响应体中的数据
//     *
//     * @param responseVO 统一响应体
//     * @param <T>        数据类型
//     * @return 数据
//     */
//    public static <T> T getData(ResponseVO<T> responseVO) {
//        return getData(responseVO, responseVO == null ? ResponseCode.BAD_REQUEST.getMsg() : responseVO.getMsg());
//    }
//
//    /**
//     * 获取统一响应体中的数据
//     *
//     * @param responseVO 统一响应体
//     * @param errorMsg   错误信息
//     * @param <T>        数据类型
//     * @return 数据
//     */
//    public static <T> T getData(ResponseVO<T> responseVO, String errorMsg) {
//        if (ObjUtil.isNull(responseVO) || !ResponseCode.SUCCESS.getCode().equals(responseVO.getCode())) {
//            throw new BusinessException(errorMsg);
//        }
//        return responseVO.getData();
//    }
//}
