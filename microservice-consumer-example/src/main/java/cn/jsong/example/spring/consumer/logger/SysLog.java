package cn.jsong.example.spring.consumer.logger;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 系统统一日志注解
 * @author S.J.
 * @date 2018/08/28
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented 
public @interface SysLog {
	public String value() default "";  
}
