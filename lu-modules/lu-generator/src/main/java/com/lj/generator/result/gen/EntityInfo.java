package com.lj.generator.result.gen;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/11/20 17:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EntityInfo extends AbstractTemplateInfo {

    /**
     * 父类实体名称
     */
    private String superEntityClass;


}
