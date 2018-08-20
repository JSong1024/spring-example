package cn.jsong.example.spring.consumer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import cn.jsong.example.spring.comm.result.Result;
import cn.jsong.example.spring.comm.result.ResultUtils;
import cn.jsong.example.spring.consumer.rest.request.AddUserInfoRequest;
import cn.jsong.example.spring.consumer.rest.response.ListUserInfoResponse;
import cn.jsong.example.spring.consumer.service.IUserInfoService;
import cn.jsong.example.spring.consumer.util.HystrixFallBackUtils;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 用户信息管理接口
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@RestController
@RequestMapping(value = "/v1/example/user")
public class UserInfoController extends BaseController {

	@Autowired
	IUserInfoService userInfoSerice;

	@HystrixCommand(commandKey = "add", groupKey = "addGroup", threadPoolKey = "addThreadPool", fallbackMethod = "addUserInfoFallBack", 
			commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000"),
									@HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "500") }, 
			threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "500") })
	@ApiOperation(value = "添加用户信息接口")
	@PostMapping(value = "/info/add")
	public Result<?> addUserInfo(@Valid @RequestBody AddUserInfoRequest request, BindingResult bindingResult) {
		
		bindingResult(bindingResult, "addUserInfo");
		
		LOGGER.info("[添加用户信息接口]-入参: {}", JSON.toJSONString(request));

		this.userInfoSerice.addUser(request);

		return ResultUtils.success();
	}
	
	Result<?> addUserInfoFallBack(@Valid @RequestBody AddUserInfoRequest request,
			BindingResult bindingResult, Throwable e) {
		bindingResult(bindingResult, "addUserInfo");
		
		return HystrixFallBackUtils.fallBackResult("addUserInfo", e);
	}
	
	@ApiOperation(value = "查询所有用户信息列表接口")
	@PostMapping(value = "/info/list")
	public Result<List<ListUserInfoResponse>> addUserInfo() {
		
		List<ListUserInfoResponse> list = this.userInfoSerice.listUser();

		return ResultUtils.success(list);
	}
	
}
