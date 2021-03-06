package com.iflytransporter.web.controller;

import java.util.ArrayList;
import java.util.List;
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
import com.iflytransporter.web.service.CommonService;
import com.iflytransporter.web.service.InsuranceService;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {
	private static Logger logger = LoggerFactory.getLogger(InsuranceController.class);
	@Autowired
	private InsuranceService insuranceService;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/manage")
	public String index(){
		logger.info("insurance/list");
		return "insurance/manage";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public Map<String,Object> queryPage(Integer page,Integer limit,String policyholderCompany,String policyholderMobile,String policyholderName,
			HttpServletRequest request){
		PageInfo<Map<String,Object>> result = insuranceService.queryPage(page, limit, policyholderCompany, policyholderMobile, policyholderName);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> op: result.getList()){
			op.put("departureProvince",commonService.queryProvince((String)op.get("departureProvinceId")));
			op.put("departureCity",commonService.queryCity((String)op.get("departureCityId")));
			op.put("departureArea",commonService.queryArea((String)op.get("departureAreaId")));
			op.put("destinationProvince",commonService.queryProvince((String)op.get("destinationProvinceId")));
			op.put("destinationCity",commonService.queryCity((String)op.get("destinationCityId")));
			op.put("destinationArea",commonService.queryArea((String)op.get("destinationAreaId")));
		
			list.add(op);
		}
		return ResponseUtil.successPage(result.getTotal(),result.getList());
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
