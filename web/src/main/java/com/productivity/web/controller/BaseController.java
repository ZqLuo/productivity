package com.productivity.web.controller;

import com.productivity.util.StringUtils;
import com.productivity.web.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zqLuo
 */
public class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取分页信息
     * @return
     */
    public Pagination getPagination(){
        String page = request.getParameter("page");
        String size = request.getParameter("rows");
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        if(StringUtils.isEmpty(size)){
            size = "20";
        }
        return new Pagination(Integer.parseInt(page), Integer.parseInt(size));
    }


    /**
     * 获取IP
     * @return
     */
    public String getIpAddress(){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
