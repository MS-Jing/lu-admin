package com.lj.generator.result.gen;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author luojing
 * @since 2024/11/20 18:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ControllerInfo extends AbstractTemplateInfo {

    /**
     * RequestMapping 后面的url
     */
    private String url;
}
