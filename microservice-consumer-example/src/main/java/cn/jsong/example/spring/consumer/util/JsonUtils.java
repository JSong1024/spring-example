package cn.jsong.example.spring.consumer.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.jsong.example.spring.comm.exception.ExampleException;

/**
 * 
 * JSON对象处理工具
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class JsonUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

	public static <T> T parseObject(String jsonStr, Class<T> typeClazz) {
		if (StringUtils.isBlank(jsonStr)) {
			return null;
		}

		T parseObject = null;
		try {
			parseObject = JSON.parseObject(jsonStr, typeClazz);
		} catch (Exception e) {
			LOGGER.error("return parse result field exception = {}", e);
			throw new ExampleException("1010001", "parse json to Object exception");
		}
		return parseObject;
	}

	/**
	 * json 转 List<T>
	 */
	public static <T> List<T> praseList(String jsonStr, Class<T> typeClazz) {
		List<T> list;
		try {
			list = JSON.parseArray(jsonStr, typeClazz);
		} catch (Exception e) {
			LOGGER.error("return [list] parse result failed exception = {}", e);
			throw new ExampleException("1010001", "parse json to list exception");
		}
		return list;
	}
}
