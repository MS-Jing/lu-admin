package com.lj.sys.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.sys.constant.SysConstant;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author luojing
 * @since 2024/11/8 17:17
 * Sa-token 的权限认证实现
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Long userId = getUserId(loginId);
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            // 超管拥有所有权限
            return Collections.singletonList("*");
        }
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long userId = getUserId(loginId);
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            // 超管拥有所有权限
            return Collections.singletonList("*");
        }
        return Collections.emptyList();
    }

    private Long getUserId(Object loginId) {
        if (loginId instanceof Long) {
            return (Long) loginId;
        }
        return NumberUtil.parseLong(StrUtil.utf8Str(loginId));
    }
}
