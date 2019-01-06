package com.productivity.web.controller;

import com.alibaba.fastjson.JSON;
import com.productivity.web.entity.SysUser;
import com.productivity.web.service.SysUserService;
import com.productivity.web.vo.DataGridReturn;
import com.productivity.web.vo.ReturnVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户Controller
 * @author luozeqiang
 * @date 2019-01-06
 */
@Controller
@RequestMapping(value = "sysUser")
public class SysUserController extends BaseController{

	Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 进入用户列表页面
	 * @return
	 */
	@RequestMapping("toSysUserIndex")
	public String toSysUserIndex(){
		return "sysUser/sysUserIndex";
	}

	/**
	 * 获取用户列表数据
	 * @return
	 */
	@RequestMapping("sysUserList")
	@ResponseBody
	public DataGridReturn sysUserList(SysUser sysUser){
		return sysUserService.listSysUser(sysUser,getPagination());
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("delSysUser")
	@ResponseBody
	public ReturnVo delSysUser(Integer id){
		ReturnVo returnVo = new ReturnVo();
		try {
			sysUserService.delSysUser(id);
		}catch (Exception e){
			logger.error("delSysUser,id=" + id,e);
			returnVo.setSuccess(false);
			returnVo.setMsg("删除失败：" + e.getMessage());
		}
		return returnVo;
	}

	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	@RequestMapping("getSysUser")
	@ResponseBody
	public ReturnVo getSysUser(Integer id){
		ReturnVo returnVo = new ReturnVo();
		returnVo.setObj(sysUserService.getSysUser(id));
		return returnVo;
	}

	/**
	 * 添加或更新用户
	 * @param sysUser
	 * @return
	 */
	@RequestMapping("addOrUpdSysUser")
	@ResponseBody
	public ReturnVo addOrUpdSysUser(SysUser sysUser){
		ReturnVo returnVo = new ReturnVo();
		try {
			if(sysUser.getId() != null){
				sysUserService.updateSysUser(sysUser);
			}else{
				sysUserService.addSysUser(sysUser);
			}
		}catch (Exception e){
			logger.error("addOrUpdSysUser:[" + JSON.toJSONString(sysUser) + "]",e);
			returnVo.setSuccess(false);
			returnVo.setMsg("失败：" + e.getMessage());
		}
		return returnVo;
	}

}