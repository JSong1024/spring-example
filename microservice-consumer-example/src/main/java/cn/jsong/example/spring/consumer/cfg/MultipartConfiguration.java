package cn.jsong.example.spring.consumer.cfg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationHome;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springboot 文件上传配置
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@Configuration
public class MultipartConfiguration {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 文件上传临时路径
	 * springboot启动时默认在系统“/tmp”目录创建临时文件上传目录，
	 * 而一般系统对tmp目录会有定时清理机制，
	 * 将导致spring上传文件找不到临时文件目录
	 */
	@Bean
	MultipartConfigElement multipartConfigElement() {
		ApplicationHome home = new ApplicationHome(getClass());
		String tmpPathStr = home.getDir().getParent() + File.separator + "tmp";
		Path tmpPath = Paths.get(tmpPathStr);
		if (!Files.exists(tmpPath)) {
			try {
				Files.createDirectory(tmpPath);
			} catch (IOException e) {
				tmpPathStr = "/tmp";
				LOGGER.error(">>>>>>>>>>[文件上传临时目录配置]-异常：tmpPath=[{}], e=[]", tmpPathStr, e.getMessage());
			}
		}
		LOGGER.info(">>>>>>>>>>[文件上传临时目录配置]：tmpPath=[{}]", tmpPathStr);
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(tmpPathStr);
		return factory.createMultipartConfig();
	}
}
