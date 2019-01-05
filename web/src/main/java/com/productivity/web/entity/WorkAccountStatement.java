package com.productivity.web.entity;

import java.util.Date;

public class WorkAccountStatement {
    private Integer id;

    private String accountStatementName;

    private String companyName;

    private Date fromDate;

    private Date toDate;

    private Date inputTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountStatementName() {
        return accountStatementName;
    }

    public void setAccountStatementName(String accountStatementName) {
        this.accountStatementName = accountStatementName == null ? null : accountStatementName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getInputTime() {
        return inputTime;
    }

    public void setInputTime(Date inputTime) {
        this.inputTime = inputTime;
    }
}