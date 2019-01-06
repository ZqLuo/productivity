package com.productivity.web.mapper;

import com.productivity.web.entity.WorkCustomer;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据姓名且ID不等于传入的ID获取用户
     * @param customerName
     * @param notId 非必填
     * @return
     */
    WorkCustomer getWorkCustomerByNameWithoutId(@Param("customerName") String customerName, @Param("notId") Integer notId);
}