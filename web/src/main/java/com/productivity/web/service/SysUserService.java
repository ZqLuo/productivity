package com.productivity.web.service;

import com.productivity.web.entity.SysUser;

public interface SysUserService {
    /**
     * 添加系统用户
     * @param sysUser
     */
    void addSysUser(SysUser sysUser);

    /**
     * 根据用户名获取系统用户
     * @param loginName
     * @return
     */
    SysUser findSysUserByLoginName(String loginName);
}
