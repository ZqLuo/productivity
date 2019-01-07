package com.productivity.web.mapper;

import com.productivity.web.entity.WorkIncomeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkIncomeDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkIncomeDetail record);

    int insertSelective(WorkIncomeDetail record);

    WorkIncomeDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkIncomeDetail record);

    int updateByPrimaryKey(WorkIncomeDetail record);

    /**
     * 获取所有销售详细信息
     * @param incomeId
     */
    List<WorkIncomeDetail> listWorkIncomeDetail(@Param("incomeId") Integer incomeId);

    /**
     * 删除所有销售信息
     * @param incomeId
     */
    void deleteByIncomeId(@Param("incomeId") Integer incomeId);
}