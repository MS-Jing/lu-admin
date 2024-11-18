package com.lj.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.sys.standard.SysStandardEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 生成器列配置
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
@Getter
@Setter
@TableName("gen_column_config")
public class GenColumnConfig extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    private Long tableId;

    private Boolean columnPk;

    private String columnName;

    private String columnTypeName;

    private Integer columnSize;
    private Integer columnDigit;

    private String fieldName;

    private String fieldType;

    /**
     * 账号类型可以下拉，然后选择对应类型的字典
     */
    private String enumDictType;

    private Integer fieldSort;

    private String comment;

    private Boolean required;

    private Boolean showInList;

    private Boolean showInQuery;

    private Integer queryType;

    private Boolean showInSave;

    private Boolean showInUpdate;

    private Integer formType;


}
