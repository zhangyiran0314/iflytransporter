package com.iflytransporter.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.iflytransporter.common.bean.Insurance;
import com.iflytransporter.common.utils.ResponseUtil;
import com.iflytransporter.web.service.InsuranceService;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {
	private static Logger logger = LoggerFactory.getLogger(InsuranceController.class);
	@Autowired
	private InsuranceService insuranceService;

	@RequestMapping("/manage")
	public String index(){
		logger.info("insurance/list");
		return "insurance/manage";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public Map<String,Object> queryPage(Integer pageNo,HttpServletRequest request){
		PageInfo<Insurance> page = insuranceService.queryPage( pageNo, 10);
		return ResponseUtil.successResult(page);
	}
	@RequestMapping("toDetail")
	public String toDetail(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "insurance/detail";
	}
	@RequestMapping("toEdit")
	public String toEdit(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "insurance/edit";
	}
	@RequestMapping("detail")
	@ResponseBody
	public Map<String,Object> detail(String id,HttpServletRequest request){
		Insurance obj = insuranceService.queryDetail(id);
		return ResponseUtil.successResult(obj);
	}
	@RequestMapping("edit")
	@ResponseBody
	public  Map<String,Object> edit(Insurance obj,HttpServletRequest request){
		return ResponseUtil.successResult();
	}
}
