package com.productivity.web.entity;

import java.util.Date;

public class WorkIncome {
    private Integer id;

    private Date incomeDate;

    private String customerName;

    private Double incomePrice;

    private Date startDate;
    private Date endDate;

    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Double getIncomePrice() {
        return incomePrice;
    }

    public void setIncomePrice(Double incomePrice) {
        this.incomePrice = incomePrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}