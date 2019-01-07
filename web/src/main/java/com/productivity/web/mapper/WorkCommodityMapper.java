package com.productivity.web.mapper;

import com.productivity.web.entity.WorkCommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkCommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkCommodity record);

    int insertSelective(WorkCommodity record);

    WorkCommodity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkCommodity record);

    int updateByPrimaryKey(WorkCommodity record);

    /**
     * 查询商品集合
     * @param workCommodity
     * @return
     */
    List<WorkCommodity> listWorkCommodity(WorkCommodity workCommodity);

    /**
     * 根据商品名称获取商品
     * @param commodityName
     * @return
     */
    WorkCommodity getWorkCommodityByCommodityName(@Param("commodityName") String commodityName);
}