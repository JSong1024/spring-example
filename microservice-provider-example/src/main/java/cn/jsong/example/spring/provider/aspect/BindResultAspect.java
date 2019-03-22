package cn.jsong.example.spring.provider.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import cn.jsong.example.spring.comm.exception.ExampleException;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求参数验证结果处理器
 * 
 * @author S.J.
 * @date 2019/03/22
 */
@Slf4j
@Aspect
@Component
@Order(2)
public class BindResultAspect {

	@Pointcut("execution(public * cn.jsong.example.spring.provider.controller.*.*(..))")
	public void bindResultCut() {
	}

	@Before("bindResultCut()")
	public void bindResultBeforeAdvice(JoinPoint point) throws Throwable {
		// 获取类名
		String clazzName = point.getTarget().getClass().getName();
		// 获取方法名
		String methodName = point.getSignature().getName();
		// 获取参数
		Object[] args = point.getArgs();
		for (Object arg : args) {
			if (arg instanceof BindingResult) {
				BindingResult result = (BindingResult) arg;
				if (result.hasErrors()) {
					log.error(">>>>>> {}.{}() valid params is error msg = {}", clazzName, methodName, 
							result.getFieldError().getDefaultMessage());
					throw new ExampleException("1030000", result.getFieldError().getDefaultMessage());
				}
			}
		}
	}
}
