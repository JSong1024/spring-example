package cn.jsong.example.spring.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import cn.jsong.example.spring.comm.exception.ExampleException;

public class BaseController {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	protected void bindingResult(BindingResult bindingResult, String methodName) {
        if (bindingResult.hasErrors()) {
        		LOGGER.info(">>>>>> {}.{}() valid params is error msg = {}", this.getClass().getSimpleName(), methodName,
                    bindingResult.getFieldError().getDefaultMessage());
            throw new ExampleException("1030000", bindingResult.getFieldError().getDefaultMessage());
        }
    }
	
}
