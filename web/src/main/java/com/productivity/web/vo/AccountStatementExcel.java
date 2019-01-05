package com.productivity.web.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * 销售对账单
 */
public class AccountStatementExcel extends BaseRowModel {

    @ExcelProperty(value = "销售日期", index = 0)
    private String saleDate;
    @ExcelProperty(value = "商品名称", index = 1)
    private String tradeName;
    @ExcelProperty(value = "单位", index = 2)
    private String unit;
    @ExcelProperty(value = "数量", index = 3)
    private String count;
    @ExcelProperty(value = "单价", index = 4)
    private String unitPrice;
    @ExcelProperty(value = "总价", index = 5)
    private String totalPrice;
    @ExcelProperty(value = "备注", index = 6)
    private String remark;

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
