package com.productivity.web;

import com.productivity.web.entity.WorkAccountStatement;
import com.productivity.web.service.WorkAccountStatementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Autowired
    private WorkAccountStatementService workAccountStatementService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addWorkAccountStatementService(){
        WorkAccountStatement workAccountStatement = new WorkAccountStatement();
        workAccountStatement.setCompanyName("名称");
        workAccountStatementService.addWorkAccountStatement(workAccountStatement);
    }
}
