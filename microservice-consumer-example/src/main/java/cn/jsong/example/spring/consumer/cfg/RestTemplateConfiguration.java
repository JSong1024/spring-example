package cn.jsong.example.spring.consumer.cfg;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * restTemplate 配置类
 * 
 * @author S.J.
 * @date 2018/08/10
 */
@Configuration
public class RestTemplateConfiguration {

	@Bean(name = "loadBalanced")
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate template = new RestTemplate();
		SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
		factory.setConnectTimeout(600000);
		factory.setReadTimeout(600000);
		return template;
	}
	
	@Bean(name = "httpRestTemplate")
	public RestTemplate httpRestTemplate() {
		RestTemplate template = new RestTemplate();
		SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
		factory.setConnectTimeout(600000);
		factory.setReadTimeout(600000);
		return template;
	}

}
