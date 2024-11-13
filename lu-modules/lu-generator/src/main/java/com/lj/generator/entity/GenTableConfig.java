package com.lj.generator.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lj.sys.standard.SysStandardEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 生成器表配置
 * </p>
 *
 * @author lj
 * @since 2024-11-12
 */
@Getter
@Setter
@TableName("gen_table_config")
public class GenTableConfig extends SysStandardEntity {

    private static final long serialVersionUID = 1L;

    private String tableName;

    private String comment;

    private String author;

    private String moduleName;

    private String packageName;

    private String superClass;

    private String tablePrefix;

    private Boolean unprefix;

    private Boolean genPage;

    private Boolean genInfo;

    private Boolean genSave;

    private Boolean genUpdate;

    private Boolean genDeleted;


}
