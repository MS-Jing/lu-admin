package com.lj.common.utils.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.idev.excel.converters.Converter;
import cn.idev.excel.converters.WriteConverterContext;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;
import com.lj.common.enums.ICommonEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luojing
 * @since 2024/11/26 14:29
 * 将枚举的desc导出到excel中
 */
public class CommentEnumConverter implements Converter<ICommonEnum<?>> {

    /**
     * 只有导入，字符串转换为java枚举类型时会用到，防止多次反射，只有第一个数据会进行初始化
     */
    private List<ICommonEnum<?>> commonEnumList;

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<ICommonEnum<?>> context) throws Exception {
        ICommonEnum<?> value = context.getValue();
        if (value != null) {
            return new WriteCellData<>(value.getDesc());
        }
        return new WriteCellData<>();
    }

    @Override
    public ICommonEnum<?> convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (CollUtil.isEmpty(commonEnumList)) {
            Class<?> type = contentProperty.getField().getType();
            if (!ClassUtil.isAssignable(ICommonEnum.class, type) || !ClassUtil.isEnum(type)) {
                // 该转换器只能用于实现ICommonEnum的枚举字段导出导入转换
                throw new UnsupportedOperationException(contentProperty.getField().getName() + " 字段的类型不匹配，无法使用该转换器导入");
            }
            Object[] enumConstants = type.getEnumConstants();
            commonEnumList = Arrays.stream(enumConstants).map(enumConstant -> (ICommonEnum<?>) enumConstant).collect(Collectors.toList());
        }
        String data = cellData.getStringValue();
        for (ICommonEnum<?> commonEnum : commonEnumList) {
            String desc = commonEnum.getDesc();
            if (desc.equals(data)) {
                return commonEnum;
            }
        }
        return null;
    }
}
