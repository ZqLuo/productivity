package com.productivity.web.service;

import com.productivity.web.entity.SysUser;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;

/**
 * 用户接口
 * @author luozeqiang
 * @date 2019-01-06
 */
public interface SysUserService {

    /**
     * 查询用户列表
     * @param sysUser
     * @param pagination
     * @return
     */
    DataGridReturn<SysUser> listSysUser(SysUser sysUser, Pagination pagination);

    /**
     * 删除用户
     * @param id
     */
    void delSysUser(Integer id);

    /**
     * 获取用户
     * @param id
     */
    SysUser getSysUser(Integer id);

    /**
     * 添加用户
     * @param sysUser
     */
    void addSysUser(SysUser sysUser);

    /**
     * 更新用户
     * @param sysUser
     */
    void updateSysUser(SysUser sysUser);

    /**
     * 根据用户名获取系统用户
     * @param loginName
     * @return
     */
    SysUser findSysUserByLoginName(String loginName);

}