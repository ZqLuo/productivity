package com.productivity.web.controller;

import com.alibaba.fastjson.JSON;
import com.productivity.web.entity.WorkCommodity;
import com.productivity.web.service.WorkCommodityService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.ReturnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品controller
 */
@Controller
@RequestMapping(value = "workCommodity")
public class WorkCommodityController extends BaseController{

    Logger logger = LoggerFactory.getLogger(WorkCommodityController.class);

    @Autowired
    private WorkCommodityService workCommodityService;

    /**
     * 进入商品列表页面
     * @return
     */
    @RequestMapping("toWorkCommodityIndex")
    public String toWorkCommodityIndex(){
        return "workCommodity/workCommodityIndex";
    }

    /**
     * 获取商品列表数据
     * @return
     */
    @RequestMapping("workCommodityList")
    @ResponseBody
    public DataGridReturn workCommodityList(WorkCommodity workCommodity){
        return workCommodityService.listWorkCommodity(workCommodity,getPagination());
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("delWorkCommodity")
    @ResponseBody
    public ReturnVo delWorkCommodity(Integer id){
        ReturnVo returnVo = new ReturnVo();
        try {
            workCommodityService.delWorkCommodity(id);
        }catch (Exception e){
            logger.error("delWorkCommodity,id=" + id,e);
            returnVo.setSuccess(false);
            returnVo.setMsg("删除失败：" + e.getMessage());
        }
        return returnVo;
    }

    /**
     * 获取商品
     * @param id
     * @return
     */
    @RequestMapping("getWorkCommodity")
    @ResponseBody
    public ReturnVo getWorkCommodity(Integer id){
        ReturnVo returnVo = new ReturnVo();
        returnVo.setObj(workCommodityService.getWorkCommodity(id));
        return returnVo;
    }

    /**
     * 添加或更新商品
     * @param workCommodity
     * @return
     */
    @RequestMapping("addOrUpdWorkCommodity")
    @ResponseBody
    public ReturnVo addOrUpdWorkCommodity(WorkCommodity workCommodity){
        ReturnVo returnVo = new ReturnVo();
        try {
            if(workCommodity.getId() != null){
                workCommodityService.updateWorkCommodity(workCommodity);
            }else{
                workCommodityService.addWorkCommodity(workCommodity);
            }
        }catch (Exception e){
            logger.error("addOrUpdWorkCommodity:[" + JSON.toJSONString(workCommodity) + "]",e);
            returnVo.setSuccess(false);
            returnVo.setMsg("失败：" + e.getMessage());
        }
        return returnVo;
    }
}
