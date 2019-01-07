package com.productivity.web.service.impl;

import com.productivity.web.entity.WorkIncome;
import com.productivity.web.entity.WorkIncomeDetail;
import com.productivity.web.mapper.WorkIncomeDetailMapper;
import com.productivity.web.mapper.WorkIncomeMapper;
import com.productivity.web.service.WorkIncomeService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WorkIncomeServiceImpl implements WorkIncomeService {

	@Autowired
	private WorkIncomeMapper workIncomeMapper;
	@Autowired
	private WorkIncomeDetailMapper workIncomeDetailMapper;

	@Override
	public DataGridReturn<WorkIncome> listWorkIncome(WorkIncome workIncome, Pagination pagination){
		DataGridReturn<WorkIncome> dataGridReturn = new DataGridReturn<>(pagination);
		dataGridReturn.setPageHelperList(workIncomeMapper.listWorkIncome(workIncome));
		return dataGridReturn;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delWorkIncome(Integer id){
		workIncomeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public WorkIncome getWorkIncome(Integer id){
		return workIncomeMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addWorkIncome(WorkIncome workIncome){
		workIncomeMapper.insert(workIncome);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateWorkIncome(WorkIncome workIncome){
		workIncomeMapper.updateByPrimaryKeySelective(workIncome);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveWorkIncomeDetail(Integer incomeId, List<WorkIncomeDetail> workIncomeDetailList) {
		workIncomeDetailMapper.deleteByIncomeId(incomeId);
		double totalPrice = 0;
		for(WorkIncomeDetail workIncomeDetail : workIncomeDetailList){
			workIncomeDetail.setIncomeId(incomeId);
			workIncomeDetail.setId(null);
			workIncomeDetailMapper.insert(workIncomeDetail);
			totalPrice += workIncomeDetail.getTotalPrice();
		}
		WorkIncome workIncome = workIncomeMapper.selectByPrimaryKey(incomeId);
		workIncome.setIncomePrice(totalPrice);
		workIncomeMapper.updateByPrimaryKey(workIncome);
	}

	@Override
	public List<WorkIncomeDetail> listWorkIncomeDetail(Integer incomeId) {
		return workIncomeDetailMapper.listWorkIncomeDetail(incomeId);
	}

}