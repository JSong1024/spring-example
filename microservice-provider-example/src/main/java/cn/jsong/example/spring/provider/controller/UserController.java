package cn.jsong.example.spring.provider.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jsong.example.spring.comm.result.Result;
import cn.jsong.example.spring.comm.result.ResultUtils;
import cn.jsong.example.spring.provider.po.User;
import cn.jsong.example.spring.provider.rest.request.AddUserRequest;
import cn.jsong.example.spring.provider.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 用户操作服务接口
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@Api(description = "用户操作服务接口集")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {
	
    @Autowired
    private IUserService userService;

    @ApiOperation(value = "添加用户信息")
    @PostMapping("/add")
    public Result<?> addUser(@Valid @RequestBody AddUserRequest request, BindingResult bindingResult) {
        
        bindingResult(bindingResult, "addUser");
        
        LOGGER.info("[addUser] >>> interface request params = {}", request.toString());
        
        this.userService.addUser(request);
        
        return ResultUtils.success();
    }
    
    @ApiOperation(value = "查询用户信息")
    @PostMapping("/list")
    public Result<List<User>> listUser() {
        
    		List<User> list = this.userService.listUser();
        
        return ResultUtils.success(list);
    }
}