package com.iflytransporter.common.enums;

public class Status {
	/**用户类型定义*/
	public static int Type_User_Shipper =0;//货主
	public static int Type_User_Transporter = 1; //车主
	/**状态定义*/
	//用户部分
	public static int User_Register = 0;//用户注册
	public static int User_Identify = 1;//用户认证
	public static int User_Enable  = 2;//用户激活
	
	/**数据有效*/
	public static int Status_Valid = 0;//有效
	public static int Status_Invalid = 1;//失效
	
	/**订单部分*/
	public static int Order_Publish =0;//发布中
	public static int Order_Transfer =1;//已转运单
}