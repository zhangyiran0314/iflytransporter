package com.iflytransporter.api.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String UUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String[] args){
		System.out.println(UUIDUtil.UUID());
		System.out.println(UUIDUtil.UUID().length());
	}
}
