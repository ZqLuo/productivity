package com.productivity.web.service;

import com.productivity.web.entity.WorkCustomer;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;

/**
 * 客户接口
 * @author luozeqiang
 * @date 2019-01-06
 */
public interface WorkCustomerService {

	/**
	 * 查询客户列表
	 * @param workCustomer
	 * @param pagination
	 * @return
	 */
	DataGridReturn<WorkCustomer> listWorkCustomer(WorkCustomer workCustomer, Pagination pagination);

	/**
	 * 删除客户
	 * @param id
	 */
	void delWorkCustomer(Integer id);

	/**
	 * 获取客户
	 * @param id
	 */
	WorkCustomer getWorkCustomer(Integer id);

	/**
	 * 添加客户
	 * @param workCustomer
	 */
	void addWorkCustomer(WorkCustomer workCustomer);

	/**
	 * 更新客户
	 * @param workCustomer
	 */
	void updateWorkCustomer(WorkCustomer workCustomer);

	/**
	 * 根据姓名且ID不等于传入的ID获取用户
	 * @param customerName
	 * @param notId 非必填
	 * @return
	 */
	WorkCustomer getWorkCustomerByNameWithoutId(String customerName,Integer notId);

}