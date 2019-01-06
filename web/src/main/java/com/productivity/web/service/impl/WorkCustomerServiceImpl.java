package com.productivity.web.service.impl;

import com.productivity.web.entity.WorkCustomer;
import com.productivity.web.mapper.WorkCustomerMapper;
import com.productivity.web.service.WorkCustomerService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WorkCustomerServiceImpl implements WorkCustomerService {

	@Autowired
	private WorkCustomerMapper workCustomerMapper;

	@Override
	public DataGridReturn<WorkCustomer> listWorkCustomer(WorkCustomer workCustomer, Pagination pagination){
		DataGridReturn<WorkCustomer> dataGridReturn = new DataGridReturn<>(pagination);
		dataGridReturn.setPageHelperList(workCustomerMapper.listWorkCustomer(workCustomer));
		return dataGridReturn;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delWorkCustomer(Integer id){
		workCustomerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public WorkCustomer getWorkCustomer(Integer id){
		return workCustomerMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addWorkCustomer(WorkCustomer workCustomer){
		workCustomerMapper.insert(workCustomer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateWorkCustomer(WorkCustomer workCustomer){
		workCustomerMapper.updateByPrimaryKeySelective(workCustomer);
	}

}