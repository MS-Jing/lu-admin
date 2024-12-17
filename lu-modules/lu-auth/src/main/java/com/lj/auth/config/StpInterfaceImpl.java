package com.lj.auth.config;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.lj.sys.constant.SysConstant;
import com.lj.sys.entity.SysRole;
import com.lj.sys.service.SysMenuService;
import com.lj.sys.service.SysRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author luojing
 * @since 2024/11/8 17:17
 * Sa-token 的权限认证实现
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleService sysRoleService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Long userId = getUserId(loginId);
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            // 超管拥有所有权限
            return Collections.singletonList("*");
        }
        List<String> permissionList = (List<String>) StpUtil.getTokenSession().get(SaSession.PERMISSION_LIST);
        if (permissionList == null) {
            permissionList = new ArrayList<>();
            // 将所有的权限加入
            Map<String, List<String>> buttonPermission = sysMenuService.buttonPermission(userId);
            for (List<String> value : buttonPermission.values()) {
                permissionList.addAll(value);
            }
            StpUtil.getTokenSession().set(SaSession.PERMISSION_LIST, permissionList);
        }
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long userId = getUserId(loginId);
        if (SysConstant.SUPER_ADMIN_ID.equals(userId)) {
            // 超管拥有所有权限
            return Collections.singletonList("*");
        }
        List<String> roleList = (List<String>) StpUtil.getTokenSession().get(SaSession.ROLE_LIST);
        if (roleList == null) {
            // 将用户拥有的角色加入
            List<SysRole> sysRoleList = sysRoleService.getSysRoleByUserId(userId);
            roleList = sysRoleList.stream().map(SysRole::getRoleCode).toList();
            StpUtil.getTokenSession().set(SaSession.ROLE_LIST, roleList);
        }
        return roleList;
    }

    private Long getUserId(Object loginId) {
        if (loginId instanceof Long) {
            return (Long) loginId;
        }
        return NumberUtil.parseLong(StrUtil.utf8Str(loginId));
    }
}
