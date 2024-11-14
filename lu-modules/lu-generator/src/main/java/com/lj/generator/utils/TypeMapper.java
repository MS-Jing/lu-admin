package com.lj.generator.utils;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luojing
 * @since 2024/11/14 18:14
 * jabc与java类型映射器
 * 参考：{@link java.sql.Types}
 */
public class TypeMapper {
    private static final String byteArrType = "byte[]";
    private static final String byteType = "Byte";
    private static final String longType = "Long";
    private static final String booleanType = "Boolean";
    private static final String shortType = "Short";
    private static final String stringType = "String";
    private static final String localDateTimeType = "java.time.LocalDateTime";
    private static final String localDateType = "java.time.LocalDate";
    private static final String localTimeType = "java.time.LocalTime";
    private static final String doubleType = "Double";
    private static final String integerType = "Integer";
    private static final String bigDecimalType = "java.math.BigDecimal";
    private static final String objectType = "Object";
    private static final Map<Integer, String> typeMap = new HashMap<>();

    static {
        // byte[]
        typeMap.put(Types.BINARY, byteArrType);
        typeMap.put(Types.BLOB, byteArrType);
        typeMap.put(Types.LONGVARBINARY, byteArrType);
        typeMap.put(Types.VARBINARY, byteArrType);
        //byte
        typeMap.put(Types.TINYINT, byteType);
        //long
        typeMap.put(Types.BIGINT, longType);
        //boolean
        typeMap.put(Types.BIT, booleanType);
        typeMap.put(Types.BOOLEAN, booleanType);
        //short
        typeMap.put(Types.SMALLINT, shortType);
        //string
        typeMap.put(Types.CHAR, stringType);
        typeMap.put(Types.CLOB, stringType);
        typeMap.put(Types.VARCHAR, stringType);
        typeMap.put(Types.LONGVARCHAR, stringType);
        typeMap.put(Types.LONGNVARCHAR, stringType);
        typeMap.put(Types.NCHAR, stringType);
        typeMap.put(Types.NCLOB, stringType);
        typeMap.put(Types.NVARCHAR, stringType);
        //date
        typeMap.put(Types.DATE, localDateType);
        typeMap.put(Types.TIME, localTimeType);
        //timestamp
        typeMap.put(Types.TIMESTAMP, localDateTimeType);
        typeMap.put(Types.TIMESTAMP_WITH_TIMEZONE, localDateTimeType);
        //double
        typeMap.put(Types.FLOAT, doubleType);
        typeMap.put(Types.REAL, doubleType);
        typeMap.put(Types.DOUBLE, doubleType);
        //int
        typeMap.put(Types.INTEGER, integerType);
        //bigDecimal
        typeMap.put(Types.NUMERIC, bigDecimalType);
        typeMap.put(Types.DECIMAL, bigDecimalType);
    }


    /**
     * 通过jdbc的类型获取java类型
     *
     * @param jdbcType   jdbc的类型 参考：{@link java.sql.Types}
     * @param columnSize 有些jdbc类型需要通过字段大小来判断
     * @return java类型 如果非lang包下的会携带包路径
     */
    public static String getJavaType(Integer jdbcType, Integer columnSize) {
        return switch (jdbcType) {
            case Types.BIT -> getBitType(columnSize);
            case Types.NUMERIC -> getNumber(columnSize);
            default -> typeMap.getOrDefault(jdbcType, objectType);
        };
    }

    private static String getBitType(Integer columnSize) {
        if (columnSize > 1) {
            return byteArrType;
        }
        return booleanType;
    }

    private static String getNumber(Integer columnSize) {
        if (columnSize > 9) {
            return longType;
        } else if (columnSize > 4) {
            return integerType;
        } else {
            return shortType;
        }
    }
}
