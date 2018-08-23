package cn.jsong.example.spring.provider.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jsong.example.spring.comm.enums.ResultEnum;
import cn.jsong.example.spring.comm.exception.ExampleException;
import cn.jsong.example.spring.provider.dao.UserMapper;
import cn.jsong.example.spring.provider.po.User;
import cn.jsong.example.spring.provider.po.UserExample;
import cn.jsong.example.spring.provider.po.UserExample.Criteria;
import cn.jsong.example.spring.provider.rest.request.AddUserRequest;
import cn.jsong.example.spring.provider.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public void addUser(AddUserRequest req) {
		
		User entity = this.selectByName(req.getName());
		
		if (null == entity) {
			// insert
			this.insertUser(req);
		} else {
			// update
			this.updateUser(req, entity);
		}
		
	}

	private void updateUser(AddUserRequest req, User entity) {
		
		entity.setAge(req.getAge());
		entity.setUpdateTime(new Date());
		int result;
		try {
			result = this.userMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ExampleException(ResultEnum.PROVIDER_USER_UPDATE_EXCEPTION);
		}
		
		if (result <= 0) {
			throw new ExampleException(ResultEnum.PROVIDER_USER_UPDATE_FAILED);
		}
	}

	private void insertUser(AddUserRequest req) {
		User user = new User();
		user.setName(req.getName());
		user.setAge(req.getAge());
		user.setCreateTime(new Date());
		int result;
		try {
			result = this.userMapper.insertSelective(user);
		} catch (Exception e) {
			throw new ExampleException(ResultEnum.PROVIDER_USER_INSERT_EXCEPTION);
		}
		
		if (result <= 0) {
			throw new ExampleException(ResultEnum.PROVIDER_USER_INSERT_FAILED);
		}
	}

	private User selectByName(String name) {
		List<User> list;
		try {
			UserExample example = new UserExample();
			Criteria cr = example.createCriteria();
			cr.andNameEqualTo(name);
			list = this.userMapper.selectByExample(example);
		} catch (Exception e) {
			throw new ExampleException(ResultEnum.PROVIDER_USER_QUERY_BY_NAME_EXCEPTION);
		}
		
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<User> listUser() {
		List<User> list;
		try {
			list = this.userMapper.selectByExample(new UserExample());
		} catch (Exception e) {
			throw new ExampleException(ResultEnum.PROVIDER_USER_QUERY_LIST_EXCEPTION);
		}
		return list;
	}
	
	@Override
	public void deleteUser(Date time) {
		List<User> list;
		try {
			UserExample example = new UserExample();
			Criteria cr = example.createCriteria();
			cr.andCreateTimeLessThanOrEqualTo(time);
			list = this.userMapper.selectByExample(example);
		} catch (Exception e) {
			throw new ExampleException(ResultEnum.PROVIDER_USER_QUERY_LIST_EXCEPTION);
		}
		
		for (User entity : list) {
			try {
				this.userMapper.deleteByPrimaryKey(entity.getId());
			} catch (Exception e) {
				LOGGER.error("[删除用户][异常] >>> id={}, errorMsg={}", entity.getId(), e.getMessage());
			}
		}
		
	}

}
