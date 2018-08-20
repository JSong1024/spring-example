package cn.jsong.example.spring.consumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jsong.example.spring.comm.exception.ExampleException;
import cn.jsong.example.spring.comm.result.Result;
import cn.jsong.example.spring.comm.result.ResultUtils;

/**
 * 
 * 全局异常处理
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public Result<?> handle(Exception e) {

		if (e instanceof ExampleException) {
			ExampleException ex = (ExampleException) e;
			LOGGER.error("[全局异常处理]: retCode=[{}], retMsg=[{}]", ex.getErrorCodeStr(), ex.getMessage());
			return ResultUtils.error(ex.getErrorCodeStr(), ex.getMessage());
		}
		LOGGER.info("[全局异常处理]: unknown exception ={}", e);
		String message = e.getMessage();
        String exceptionName = e.getClass() == null ? null : e.getClass().getSimpleName();
        String resultMessage = exceptionName == null ? "[" + message+ "]" : exceptionName + "[" + message + "]"; 
        return ResultUtils.error("-1", resultMessage);
	}

}
