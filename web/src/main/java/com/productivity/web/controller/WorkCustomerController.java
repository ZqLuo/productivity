package com.productivity.web.controller;

import com.alibaba.fastjson.JSON;
import com.productivity.web.entity.WorkCustomer;
import com.productivity.web.service.WorkCustomerService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.ReturnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 客户Controller
 * @author luozeqiang
 * @date 2019-01-06
 */
@Controller
@RequestMapping(value = "workCustomer")
public class WorkCustomerController extends BaseController{

	Logger logger = LoggerFactory.getLogger(WorkCustomerController.class);

	@Autowired
	private WorkCustomerService workCustomerService;

	/**
	 * 进入客户列表页面
	 * @return
	 */
	@RequestMapping("toWorkCustomerIndex")
	public String toWorkCustomerIndex(){
		return "workCustomer/workCustomerIndex";
	}

	/**
	 * 获取客户列表数据
	 * @return
	 */
	@RequestMapping("workCustomerList")
	@ResponseBody
	public DataGridReturn workCustomerList(WorkCustomer workCustomer){
		return workCustomerService.listWorkCustomer(workCustomer,getPagination());
	}

	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	@RequestMapping("delWorkCustomer")
	@ResponseBody
	public ReturnVo delWorkCustomer(Integer id){
		ReturnVo returnVo = new ReturnVo();
		try {
			workCustomerService.delWorkCustomer(id);
		}catch (Exception e){
			logger.error("delWorkCustomer,id=" + id,e);
			returnVo.setSuccess(false);
			returnVo.setMsg("删除失败：" + e.getMessage());
		}
		return returnVo;
	}

	/**
	 * 获取客户
	 * @param id
	 * @return
	 */
	@RequestMapping("getWorkCustomer")
	@ResponseBody
	public ReturnVo getWorkCustomer(Integer id){
		ReturnVo returnVo = new ReturnVo();
		returnVo.setObj(workCustomerService.getWorkCustomer(id));
		return returnVo;
	}

	/**
	 * 添加或更新客户
	 * @param workCustomer
	 * @return
	 */
	@RequestMapping("addOrUpdWorkCustomer")
	@ResponseBody
	public ReturnVo addOrUpdWorkCustomer(WorkCustomer workCustomer){
		ReturnVo returnVo = new ReturnVo();
		try {
			WorkCustomer oldWorkCustomer = workCustomerService.getWorkCustomerByNameWithoutId(workCustomer.getCustomerName(),workCustomer.getId());
			if(oldWorkCustomer != null){
				returnVo.setSuccess(false);
				returnVo.setMsg("客户名称已存在");
			}else{
				if(workCustomer.getId() != null){
					workCustomerService.updateWorkCustomer(workCustomer);
				}else{
					workCustomerService.addWorkCustomer(workCustomer);
				}
			}
		}catch (Exception e){
			logger.error("addOrUpdWorkCustomer:[" + JSON.toJSONString(workCustomer) + "]",e);
			returnVo.setSuccess(false);
			returnVo.setMsg("失败：" + e.getMessage());
		}
		return returnVo;
	}

}