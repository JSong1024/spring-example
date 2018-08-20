package cn.jsong.example.spring.consumer.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * 服务的连接认证信息
 * 
 * @author S.J.
 * @date 2018/08/11
 */
@Component
public class HttpBasicAuthProperties {
	@Value("${http.basic.provider.userName}")
	private String providerName;
	@Value("${http.basic.provider.password}")
	private String providerPassword;
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getProviderPassword() {
		return providerPassword;
	}
	public void setProviderPassword(String providerPassword) {
		this.providerPassword = providerPassword;
	}

}
