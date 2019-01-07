package com.productivity.web.controller;

import com.alibaba.fastjson.JSON;
import com.productivity.web.entity.WorkCustomer;
import com.productivity.web.entity.WorkIncome;
import com.productivity.web.entity.WorkIncomeDetail;
import com.productivity.web.service.WorkCustomerService;
import com.productivity.web.service.WorkIncomeService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.ReturnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 销售Controller
 * @author luozeqiang
 * @date 2019-01-06
 */
@Controller
@RequestMapping(value = "workIncome")
public class WorkIncomeController extends BaseController{

	Logger logger = LoggerFactory.getLogger(WorkIncomeController.class);

	@Autowired
	private WorkIncomeService workIncomeService;
	@Autowired
	private WorkCustomerService workCustomerService;

	/**
	 * 进入销售列表页面
	 * @return
	 */
	@RequestMapping("toWorkIncomeIndex")
	public String toWorkIncomeIndex(Model model){
		DataGridReturn dataGridReturn = workCustomerService.listWorkCustomer(new WorkCustomer(),null);
		model.addAttribute("workCustomers", dataGridReturn.getRows());
		return "workIncome/workIncomeIndex";
	}

	/**
	 * 获取销售列表数据
	 * @return
	 */
	@RequestMapping("workIncomeList")
	@ResponseBody
	public DataGridReturn workIncomeList(WorkIncome workIncome){
		return workIncomeService.listWorkIncome(workIncome,getPagination());
	}

	/**
	 * 获取销售详细列表
	 * @param incomeId
	 * @return
	 */
	@RequestMapping("workIncomeDetailList")
	@ResponseBody
	public DataGridReturn workIncomeDetailList(Integer incomeId){
		List<WorkIncomeDetail> workIncomeDetails = new ArrayList<>();
		if(incomeId != null){
			workIncomeDetails = workIncomeService.listWorkIncomeDetail(incomeId);
		}
		int total = workIncomeDetails!=null?workIncomeDetails.size():0;
		return new DataGridReturn(total,workIncomeDetails);
	}

	/**
	 * 删除销售
	 * @param id
	 * @return
	 */
	@RequestMapping("delWorkIncome")
	@ResponseBody
	public ReturnVo delWorkIncome(Integer id){
		ReturnVo returnVo = new ReturnVo();
		try {
			workIncomeService.delWorkIncome(id);
		}catch (Exception e){
			logger.error("delWorkIncome,id=" + id,e);
			returnVo.setSuccess(false);
			returnVo.setMsg("删除失败：" + e.getMessage());
		}
		return returnVo;
	}

	/**
	 * 获取销售
	 * @param id
	 * @return
	 */
	@RequestMapping("getWorkIncome")
	@ResponseBody
	public ReturnVo getWorkIncome(Integer id){
		ReturnVo returnVo = new ReturnVo();
		returnVo.setObj(workIncomeService.getWorkIncome(id));
		return returnVo;
	}

	/**
	 * 添加或更新销售
	 * @param workIncome
	 * @return
	 */
	@RequestMapping("addOrUpdWorkIncome")
	@ResponseBody
	public ReturnVo addOrUpdWorkIncome(WorkIncome workIncome){
		ReturnVo returnVo = new ReturnVo();
		try {
			if(workIncome.getId() != null){
				workIncomeService.updateWorkIncome(workIncome);
			}else{
				workIncomeService.addWorkIncome(workIncome);
			}
		}catch (Exception e){
			logger.error("addOrUpdWorkIncome:[" + JSON.toJSONString(workIncome) + "]",e);
			returnVo.setSuccess(false);
			returnVo.setMsg("失败：" + e.getMessage());
		}
		return returnVo;
	}

	/**
	 * 保存销售详细
	 * @param detailJson
	 * @return
	 */
	@RequestMapping("saveWorkIncomeDetail")
	@ResponseBody
	public ReturnVo saveWorkIncomeDetail(Integer incomeId, String detailJson){
		ReturnVo returnVo = new ReturnVo();
		try {
			List<WorkIncomeDetail> workIncomeDetails = JSON.parseArray(detailJson,WorkIncomeDetail.class);
			workIncomeService.saveWorkIncomeDetail(incomeId, workIncomeDetails);
		} catch (Exception e){
			logger.error("保存销售详细失败，incomeId="+incomeId, e);
			returnVo.setSuccess(false);
			returnVo.setMsg("保存失败");
		}
		return returnVo;
	}
}