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
import com.iflytransporter.common.bean.Waybill;
import com.iflytransporter.common.utils.ResponseUtil;
import com.iflytransporter.web.service.WaybillService;

@Controller
@RequestMapping("/waybill")
public class WaybillController {
	private static Logger logger = LoggerFactory.getLogger(WaybillController.class);
	@Autowired
	private WaybillService waybillService;

	@RequestMapping("/manage")
	public String index(){
		logger.info("waybill/list");
		return "waybill/manage";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public Map<String,Object> queryPage(Integer pageNo,String sId,String tId,String wId,HttpServletRequest request){
		PageInfo<Waybill> page = waybillService.queryPage(pageNo, 10, sId, tId, wId);
		return ResponseUtil.successResult(page);
	}
	@RequestMapping("toDetail")
	public String toDetail(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "waybill/detail";
	}
	@RequestMapping("toEdit")
	public String toEdit(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "waybill/edit";
	}
	@RequestMapping("detail")
	@ResponseBody
	public Map<String,Object> detail(String id,HttpServletRequest request){
		Waybill obj = waybillService.queryDetail(id);
		return ResponseUtil.successResult(obj);
	}
	@RequestMapping("edit")
	@ResponseBody
	public  Map<String,Object> edit(Waybill obj,HttpServletRequest request){
		return ResponseUtil.successResult();
	}
}
