package com.iflytransporter.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iflytransporter.common.bean.OrderApply;
import com.iflytransporter.web.mapper.OrderApplyMapper;
import com.iflytransporter.web.service.OrderApplyService;

@Service("orderApplyService")
public class OrderApplyServiceImpl implements OrderApplyService{

	@Autowired
	private OrderApplyMapper orderApplyMapper;

	@Override
	public OrderApply queryDetail(String id) {
		return orderApplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<OrderApply> queryPage(Integer pageNo, Integer pageSize,String tId,String oId) {
		if(pageNo!= null && pageSize!= null){  
            PageHelper.startPage(pageNo, pageSize);  
        }  
		List<OrderApply> list= orderApplyMapper.queryAll(tId,oId);
		return new PageInfo<OrderApply>(list);
	}

}