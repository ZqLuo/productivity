package com.productivity.web.mapper;

import com.productivity.web.entity.WorkAccountStatementDetail;

public interface WorkAccountStatementDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkAccountStatementDetail record);

    int insertSelective(WorkAccountStatementDetail record);

    WorkAccountStatementDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkAccountStatementDetail record);

    int updateByPrimaryKey(WorkAccountStatementDetail record);
}