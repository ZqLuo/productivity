package com.productivity.web.mapper;

import com.productivity.web.entity.WorkIncome;

import java.util.List;

public interface WorkIncomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkIncome record);

    int insertSelective(WorkIncome record);

    WorkIncome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkIncome record);

    int updateByPrimaryKey(WorkIncome record);

    /**
     * 获取销售集合
     * @param workIncome
     * @return
     */
    List<WorkIncome> listWorkIncome(WorkIncome workIncome);
}