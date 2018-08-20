package cn.jsong.example.spring.gateway.cfg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * 聚合微服务系统API配置
 * 
 * @author S.J.
 * @date 2018/08/13
 */
@Configuration
@EnableSwagger2
public class SwaggerAggregateConfig {

	@Autowired
	ZuulProperties properties;

	@Primary
	@Bean
	public SwaggerResourcesProvider swaggerResourcesProvider() {
		return () -> {
			List<SwaggerResource> resources = new ArrayList<>();
			properties.getRoutes().values().stream().forEach(route -> {
				if (route.getPath().startsWith("/swagger")) {
					resources.add(createResource(route.getId(), route.getPath(), "2.0"));
				}
			});
			return resources;
		};
	}

	private SwaggerResource createResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location.replace("/*", "").replace("*", "") + "/v2/api-docs");
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}
