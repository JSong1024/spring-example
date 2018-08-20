package cn.jsong.example.spring.consumer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jsong.example.spring.comm.exception.ExampleException;
import cn.jsong.example.spring.comm.result.Result;
import cn.jsong.example.spring.comm.result.ResultUtils;

/**
 * 
 * 服务回退响应结果封装
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class HystrixFallBackUtils {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HystrixFallBackUtils.class);

	public static <T> Result<T> fallBackResult(String method, Throwable e) {
		LOGGER.info(">>>>>> {}: 触发服务降级", method);
		if (e instanceof ExampleException) {
			ExampleException ex = (ExampleException) e;
			return ResultUtils.error(ex.getErrorCodeStr(), ex.getMessage());
		}
		LOGGER.info(">>>>>> {}: 触发服务降级，断容器开启", method);

		return ResultUtils.error("0000001", "[" + method + "]服务不可用");
	}

}
