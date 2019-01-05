package com.productivity.web.service.impl;

import com.productivity.web.entity.SysUser;
import com.productivity.web.mapper.SysUserMapper;
import com.productivity.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;


    @Override
    public void addSysUser(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }

    @Override
    public SysUser findSysUserByLoginName(String loginName) {
        return sysUserMapper.findSysUserByLoginName(loginName);
    }
}
