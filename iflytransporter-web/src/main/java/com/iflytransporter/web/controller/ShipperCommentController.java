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
import com.iflytransporter.common.bean.Comment;
import com.iflytransporter.common.utils.ResponseUtil;
import com.iflytransporter.web.service.CommentService;

@Controller
@RequestMapping("shipper/comment")
public class ShipperCommentController {
	private static Logger logger = LoggerFactory.getLogger(ShipperCommentController.class);
	@Autowired
	private CommentService commentService;

	@RequestMapping("/manage")
	public String index(){
		logger.info("comment/list");
		return "shipper/comment/manage";
	}
	@RequestMapping("queryPage")
	@ResponseBody
	public Map<String,Object> queryPage(Integer page,Integer limit,String orderNo,String sMobile,String tCompanyName,String tMobile,String dMobile,HttpServletRequest request){
		PageInfo<Map<String,Object>> result = commentService.queryPage(page, limit, orderNo, sMobile, tCompanyName,tMobile,dMobile);
		return ResponseUtil.successPage(result.getTotal(),result.getList());
	}
	@RequestMapping("toDetail")
	public String toDetail(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "shipper/comment/detail";
	}
	@RequestMapping("toEdit")
	public String toEdit(String id,HttpServletRequest request){
		request.setAttribute("objectId", id);
		return "shipper/comment/edit";
	}
	@RequestMapping("detail")
	@ResponseBody
	public Map<String,Object> detail(String id,HttpServletRequest request){
		Map<String,Object> obj = commentService.queryDetail(id);
		return ResponseUtil.successResult(obj);
	}
	@RequestMapping("edit")
	@ResponseBody
	public  Map<String,Object> edit(Comment obj,HttpServletRequest request){
		return ResponseUtil.successResult();
	}
}
