package cn.jsong.example.spring.consumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.jsong.example.spring.comm.exception.ExampleException;
import cn.jsong.example.spring.consumer.constant.Constants;
import cn.jsong.example.spring.consumer.properties.HttpBasicAuthProperties;
import cn.jsong.example.spring.consumer.properties.HttpHeader;

/**
 * 
 * restTemple 模版方法基础服务类
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public abstract class BaseRestTemplateService {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	HttpBasicAuthProperties httpBasicAuth;
	
	public String userName;
	public String password;

	@Autowired
	@Qualifier("loadBalanced")
	protected RestTemplate restTemplate;

	protected HttpHeaders getBaseHeader() {
		HttpHeaders headers = new HttpHeader(this.getUserName(), this.getPassword()).getHeaders();
		return headers;
	}

	protected <T> T postForObject(String url, Object request, Class<T> responstType, HttpHeaders headers) {
		HttpEntity<Object> httpEntity = new HttpEntity<>(request, headers);
		return this.restTemplate.postForObject(url, httpEntity, responstType);
	}

	protected String postForObject(String url, Object object, String discript) {
		JSONObject result = null;
		try {
			result = this.postForObject(url, object, JSONObject.class, getBaseHeader());
		} catch (Exception e) {
			LOGGER.error("[{}远程请求服务异常] >>> e:{}", discript, e);
			throw new ExampleException("1010001", "远程请求[" + discript + "]异常");
		}
		LOGGER.info("[{}远程请求返回数据] >>> {}",discript, result.toJSONString());
		String jsonStr = vaildResult(result, discript);
		return jsonStr;
	}

	protected String postForObject(String url, Object object, String discript, HttpHeaders headers) {
		JSONObject result = null;
		try {
			result = this.postForObject(url, object, JSONObject.class, headers);
		} catch (Exception e) {
			LOGGER.error("[{}远程请求服务异常] >>> e:{}", discript, e);
			throw new ExampleException("1010001", "远程请求[" + discript + "]异常");
		}
		String jsonStr = vaildResult(result, discript);
		return jsonStr;
	}

	protected String vaildResult(JSONObject result, String discript) {
		if (null == result) {
			throw new ExampleException("1010001", "远程请求[" + discript + "]响应结果为空");
		}
		String code = result.getString("retCode");
		if (!Constants.PROVIDER_SUCCESS.equals(code)) {
			throw new ExampleException(code, result.getString("retMsg"));
		}
		result.remove("retCode");
		result.remove("retMsg");
		return JSON.toJSONString(result.get("result"));
	}

	public abstract String getUserName();

	public abstract String getPassword();
}
