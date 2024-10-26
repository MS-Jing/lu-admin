package com.lj.common.module;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author luojing
 * @date 2024/10/26
 * 模块信息
 */
@Data
@Accessors(chain = true)
public class ModuleInfo {
    private String moduleName;
}
