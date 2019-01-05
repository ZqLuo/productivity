package com.productivity.web.service.impl;

import com.productivity.web.entity.WorkAccountStatement;
import com.productivity.web.mapper.WorkAccountStatementDetailMapper;
import com.productivity.web.mapper.WorkAccountStatementMapper;
import com.productivity.web.service.WorkAccountStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class WorkAccountStatementServiceImpl implements WorkAccountStatementService {

    @Autowired
    private WorkAccountStatementMapper workAccountStatementMapper;
    @Autowired
    private WorkAccountStatementDetailMapper workAccountStatementDetailMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addWorkAccountStatement(WorkAccountStatement workAccountStatement) {
        workAccountStatement.setInputTime(new Date());
        workAccountStatementMapper.insert(workAccountStatement);
    }
}
