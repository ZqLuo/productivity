package com.productivity.web.service.impl;

import com.productivity.web.entity.WorkCommodity;
import com.productivity.web.mapper.WorkCommodityMapper;
import com.productivity.web.service.WorkCommodityService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WorkCommodityServiceImpl implements WorkCommodityService {

    @Autowired
    private WorkCommodityMapper workCommodityMapper;

    @Override
    public DataGridReturn<WorkCommodity> listWorkCommodity(WorkCommodity workCommodity, Pagination pagination) {
        DataGridReturn<WorkCommodity> dataGridReturn = new DataGridReturn<>(pagination);
        dataGridReturn.setPageHelperList(workCommodityMapper.listWorkCommodity(workCommodity));
        return dataGridReturn;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delWorkCommodity(Integer id) {
        workCommodityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WorkCommodity getWorkCommodity(Integer id) {
        return workCommodityMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWorkCommodity(WorkCommodity workCommodity) {
        workCommodityMapper.insert(workCommodity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWorkCommodity(WorkCommodity workCommodity) {
        workCommodityMapper.updateByPrimaryKey(workCommodity);
    }
}
