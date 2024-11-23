package com.lj.generator.result.gen;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author luojing
 * @since 2024/11/20 18:40
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ControllerInfo extends AbstractTemplateInfo {

    /**
     * RequestMapping 后面的url
     */
    private String url;

    public ControllerInfo(GenTemplateInfo genTemplateInfo) {
        super(genTemplateInfo);
    }
}
