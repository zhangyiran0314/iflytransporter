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
import com.iflytransporter.common.bean.OrderApply;
import com.iflytransporter.common.utils.ResponseUtil;
import com.iflytransporter.web.service.CommonService;
import com.iflytransporter.web.service.OrderApplyService;

@Controller
@RequestMapping("transporter/orderApply")
public class TransporterOrderApplyController {
	private static Logger logger = LoggerFactory.getLogger(TransporterOrderApplyController.class);
	@Autowired
	private OrderApplyService orderApplyService;
	@Autowired
	private CommonService commonService;

	@RequestMapping("/manage")
	public String index(){
		logger.info("orderApply/list");
		return "transporter/orderApply/manage";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public Map<String,Object> queryPage(Integer page,Integer limit,String orderNo,String mobile,String companyName,HttpServletRequest request){
		PageInfo<Map<String,Object>> result = orderApplyService.queryPage(page, limit, orderNo,mobile,companyName);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> op: result.getList()){
			op.put("departureProvince",commonService.queryProvince((String)op.get("departureProvinceId")));
			
			//op.setDepartureCity(commonService.queryCity(order.getDepartureCityId()));
			op.put("departureCity",commonService.queryCity((String)op.get("departureCityId")));
//			op.setDepartureArea(commonService.queryArea(order.getDepartureAreaId()));
			op.put("departureArea",commonService.queryArea((String)op.get("departureAreaId")));
			
//			op.setDestinationProvince(commonService.queryProvince(order.getDestinationProvinceId()));
			op.put("destinationProvince",commonService.queryProvince((String)op.get("destinationProvinceId")));
//			op.setDestinationCity(commonService.queryCity(order.getDestinationCityId()));
			op.put("destinationCity",commonService.queryCity((String)op.get("destinationCityId")));
//			op.setDestinationArea(commonService.queryArea(order.getDestinationAreaId()));
			op.put("destinationArea",commonService.queryArea((String)op.get("destinationAreaId")));
		
//			op.setCarType(commonService.queryCarType(order.getCarTypeId()));
			op.put("carType",commonService.queryCarType((String)op.get("carTypeId")));
//			op.setHandlingType(commonService.queryHandlingType(order.getHandlingTypeId()));
			op.put("handlingType",commonService.queryHandlingType((String)op.get("handlingTypeId")));
//			op.setPaymentType(commonService.queryPaymentType(order.getPaymentTypeId()));
			op.put("paymentType",commonService.queryPaymentType((String)op.get("paymentTypeId")));
//			op.setUseType(commonService.queryUseType(order.getUseTypeId()));
			op.put("useType",commonService.queryUseType((String)op.get("useTypeId")));
			list.add(op);
		}
		return ResponseUtil.successPage(result.getTotal(),list);
	}
	@RequestMapping("toDetail")
	public String toDetail(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "transporter/orderApply/detail";
	}
	@RequestMapping("toEdit")
	public String toEdit(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "transporter/orderApply/edit";
	}
	@RequestMapping("detail")
	@ResponseBody
	public Map<String,Object> detail(String id,HttpServletRequest request){
		OrderApply obj = orderApplyService.queryDetail(id);
		return ResponseUtil.successResult(obj);
	}
	@RequestMapping("edit")
	@ResponseBody
	public  Map<String,Object> edit(OrderApply obj,HttpServletRequest request){
		return ResponseUtil.successResult();
	}
}
