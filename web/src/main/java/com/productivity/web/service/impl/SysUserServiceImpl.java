package com.productivity.web.service.impl;

import com.productivity.util.MD5Util;
import com.productivity.web.entity.SysUser;
import com.productivity.web.mapper.SysUserMapper;
import com.productivity.web.service.SysUserService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public DataGridReturn<SysUser> listSysUser(SysUser sysUser, Pagination pagination){
        DataGridReturn<SysUser> dataGridReturn = new DataGridReturn<>(pagination);
        dataGridReturn.setPageHelperList(sysUserMapper.listSysUser(sysUser));
        return dataGridReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delSysUser(Integer id){
        sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SysUser getSysUser(Integer id){
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSysUser(SysUser sysUser){
        sysUser.setPassword(MD5Util.encode("111111"));
        sysUserMapper.insert(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysUser(SysUser sysUser){
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public SysUser findSysUserByLoginName(String loginName) {
        return sysUserMapper.findSysUserByLoginName(loginName);
    }

}