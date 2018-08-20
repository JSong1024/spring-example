package cn.jsong.example.spring.provider.service;

import java.util.Date;
import java.util.List;

import cn.jsong.example.spring.provider.po.User;
import cn.jsong.example.spring.provider.rest.request.AddUserRequest;

public interface IUserService {

	void addUser(AddUserRequest req);
	List<User> listUser();
	void deleteUser(Date time);
	
}
