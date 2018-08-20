package cn.jsong.example.spring.provider.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.jsong.example.spring.provider.constant.Constants;
import cn.jsong.example.spring.provider.service.IUserService;

/**
 * 
 * 定时任务
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@Component
public class DeleteUserTask {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private IUserService userService;
	
	@Scheduled(cron = Constants.CRON_DELETE_USER_INFO)
	public void deleteUser() {
		try {
			this.userService.deleteUser(new Date());
		} catch (Exception e) {
			LOGGER.error("[定时任务-删除用户信息]-异常: errorMsg={}", e.getMessage());
		}
	}

}
