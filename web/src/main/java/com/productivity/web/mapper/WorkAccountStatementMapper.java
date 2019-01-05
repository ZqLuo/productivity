package com.productivity.web.mapper;

import com.productivity.web.entity.WorkAccountStatement;

public interface WorkAccountStatementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkAccountStatement record);

    int insertSelective(WorkAccountStatement record);

    WorkAccountStatement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkAccountStatement record);

    int updateByPrimaryKey(WorkAccountStatement record);
}