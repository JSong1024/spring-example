package cn.jsong.example.spring.consumer.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.jsong.example.spring.consumer.constant.UrlConstants;
import cn.jsong.example.spring.consumer.rest.request.AddUserInfoRequest;
import cn.jsong.example.spring.consumer.rest.response.ListUserInfoResponse;
import cn.jsong.example.spring.consumer.service.IUserInfoService;
import cn.jsong.example.spring.consumer.util.JsonUtils;

@Service
public class UserInfoServiceImpl extends BaseRestTemplateService implements IUserInfoService {

	@Override
	public void addUser(AddUserInfoRequest req) {
		this.postForObject(UrlConstants.USER_ADD_URL, req, "添加用户信息");
	}

	@Override
	public List<ListUserInfoResponse> listUser() {
		String res = this.postForObject(UrlConstants.USER_LIST_URL, null, "查询所有用户信息");
		return JsonUtils.praseList(res, ListUserInfoResponse.class);
	}

	@Override
	public String getUserName() {
		return this.httpBasicAuth.getProviderName();
	}

	@Override
	public String getPassword() {
		return this.httpBasicAuth.getProviderPassword();
	}
}
