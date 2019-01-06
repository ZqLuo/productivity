package com.productivity.web.mapper;

import com.productivity.web.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名获取系统用户
     * @param loginName
     * @return
     */
    SysUser findSysUserByLoginName(@Param("loginName") String loginName);

    /**
     * 获取系统用户集合
     * @param sysUser
     * @return
     */
    List<SysUser> listSysUser(SysUser sysUser);
}