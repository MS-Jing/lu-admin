package com.lj.common.module;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luojing
 * @date 2024/10/26
 */
@Getter
@Component
public class ModuleHelper {

    private final List<ModuleInfo> moduleInfoList;

    public ModuleHelper(List<ModuleInfo> moduleInfoList) {
        this.moduleInfoList = moduleInfoList;
    }

    /**
     * @return 获取所有模块名
     */
    public Set<String> getAllModuleName() {
        if (CollUtil.isEmpty(moduleInfoList)) {
            return Collections.emptySet();
        }
        return moduleInfoList
                .stream()
                .map(ModuleInfo::getModuleName)
                .collect(Collectors.toSet());
    }
}
