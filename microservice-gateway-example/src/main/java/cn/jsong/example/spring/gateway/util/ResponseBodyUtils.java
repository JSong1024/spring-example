package cn.jsong.example.spring.gateway.util;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 生成 respopnse body 模版
 * 
 * @author S.J.
 * @date 2018/08/13
 */
public class ResponseBodyUtils {

	public static String noPermission(String msg) {
		JSONObject result = new JSONObject();
		result.put("retCode", "0000001");
		result.put("retMsg", "no permission: " + msg);
		result.put("result", null);
		return result.toJSONString();
	}
	
	public static String fallBack() {
		JSONObject result = new JSONObject();
		result.put("retCode", "0000002");
		result.put("retMsg", "service unavailable");
		result.put("result", null);
		return result.toJSONString();
	}
	


}
