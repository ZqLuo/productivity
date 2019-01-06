package com.productivity.web.mapper;

import com.productivity.web.entity.WorkCustomer;

import java.util.List;

public interface WorkCustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkCustomer record);

    int insertSelective(WorkCustomer record);

    WorkCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkCustomer record);

    int updateByPrimaryKey(WorkCustomer record);

    /**
     * 查询客户列表
     * @param workCustomer
     * @return
     */
    List<WorkCustomer> listWorkCustomer(WorkCustomer workCustomer);
}