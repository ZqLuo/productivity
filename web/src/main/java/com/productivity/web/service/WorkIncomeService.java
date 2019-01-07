package com.productivity.web.service;

import com.productivity.web.entity.WorkIncome;
import com.productivity.web.entity.WorkIncomeDetail;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;

import java.util.List;

/**
 * 销售接口
 * @author luozeqiang
 * @date 2019-01-06
 */
public interface WorkIncomeService {

	/**
	 * 查询销售列表
	 * @param workIncome
	 * @param pagination
	 * @return
	 */
	DataGridReturn<WorkIncome> listWorkIncome(WorkIncome workIncome, Pagination pagination);

	/**
	 * 删除销售
	 * @param id
	 */
	void delWorkIncome(Integer id);

	/**
	 * 获取销售
	 * @param id
	 */
	WorkIncome getWorkIncome(Integer id);

	/**
	 * 添加销售
	 * @param workIncome
	 */
	void addWorkIncome(WorkIncome workIncome);

	/**
	 * 更新销售
	 * @param workIncome
	 */
	void updateWorkIncome(WorkIncome workIncome);

	/**
	 * 保存销售详细
	 * @param incomeId
	 * @param workIncomeDetailList
	 */
	void saveWorkIncomeDetail(Integer incomeId, List<WorkIncomeDetail> workIncomeDetailList);

	/**
	 * 获取所有销售详细信息
	 * @param incomeId
	 */
	List<WorkIncomeDetail> listWorkIncomeDetail(Integer incomeId);

}