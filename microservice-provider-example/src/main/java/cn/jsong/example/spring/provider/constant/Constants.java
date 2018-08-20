package cn.jsong.example.spring.provider.constant;

import org.springframework.stereotype.Component;

@Component
public class Constants {

	/**
	 * 定时任务-删除用户信息
	 */
	public static final String CRON_DELETE_USER_INFO = "0 59 23 * * ? ";

}
