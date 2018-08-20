package cn.jsong.example.spring.consumer.service;

import java.util.List;

import cn.jsong.example.spring.consumer.rest.request.AddUserInfoRequest;
import cn.jsong.example.spring.consumer.rest.response.ListUserInfoResponse;

public interface IUserInfoService {

	void addUser(AddUserInfoRequest req);
	List<ListUserInfoResponse> listUser();
}
