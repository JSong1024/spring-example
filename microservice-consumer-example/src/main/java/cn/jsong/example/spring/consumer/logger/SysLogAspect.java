package cn.jsong.example.spring.consumer.logger;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.jsong.example.spring.comm.exception.ExampleException;

/**
 * 统一日志切面拦截器
 * @author S.J.
 * @date 2018/08/28
 */
@Aspect
@Component
public class SysLogAspect {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(cn.jsong.example.spring.consumer.logger.SysLog)")//指向自定义注解路径
    public void logPointCut() {
		
	}
	
	// 环绕通知  
    @Around("logPointCut()")
    public Object logAroundAdvice(ProceedingJoinPoint point) throws Throwable {  
        
        long beginTime = System.currentTimeMillis();
        
        //执行方法
		Object result = null;
		try {
			result = point.proceed();
			
			//执行时长(毫秒)
	        long time = System.currentTimeMillis() - beginTime;
	        
	        //保存日志
	        saveSysLog(point, time, null, null);
	        return result;
		} catch (Exception e) {
			//执行时长(毫秒)
	        long time = System.currentTimeMillis() - beginTime;
	        
	        String errorCode = "";
	        String errorMsg = e.getMessage();
			if (e instanceof ExampleException) {
				ExampleException ex = (ExampleException) e;
				errorCode = ex.getErrorCodeStr();
				errorMsg = ex.getMessage();
			}
			
			//保存日志
	        saveSysLog(point, time, errorCode, errorMsg);
	        
	        throw e;
		}
        
    }

	private void saveSysLog(ProceedingJoinPoint point, long time, String errorCode, String errorMsg) {
		
		MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        SysLog syslog = method.getAnnotation(SysLog.class);
        //注解上的描述
        String syslogValue = null != syslog ? syslog.value() : "统一日志";

        //请求的方法名
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        
        if (StringUtils.isBlank(errorMsg)) {
	        	LOGGER.debug("[统一日志]-[{}({}.{})]: 耗时=[{}]ms", syslogValue, className, methodName, time);
        } else {
	        	LOGGER.debug("[统一日志]-[{}({}.{})]: 耗时=[{}]ms, errorCode=[{}], errorMsg=[{}]", syslogValue, className, methodName, time, errorCode, errorMsg);
        }
		
	}  
}
