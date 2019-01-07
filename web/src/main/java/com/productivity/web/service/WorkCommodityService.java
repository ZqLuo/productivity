package com.productivity.web.service;

import com.productivity.web.entity.WorkCommodity;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.Pagination;

public interface WorkCommodityService {

    /**
     * 查询商品列表
     * @param workCommodity
     * @param pagination
     * @return
     */
    DataGridReturn<WorkCommodity> listWorkCommodity(WorkCommodity workCommodity, Pagination pagination);

    /**
     * 删除商品
     * @param id
     */
    void delWorkCommodity(Integer id);

    /**
     * 获取商品
     * @param id
     * @return
     */
    WorkCommodity getWorkCommodity(Integer id);

    /**
     * 添加商品
     * @param workCommodity
     */
    void addWorkCommodity(WorkCommodity workCommodity);

    /**
     * 更新商品
     * @param workCommodity
     */
    void updateWorkCommodity(WorkCommodity workCommodity);

    /**
     * 根据商品名称获取商品
     * @param commodityName
     * @return
     */
    WorkCommodity getWorkCommodityByCommodityName(String commodityName);
}
