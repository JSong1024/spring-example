package cn.jsong.example.spring.gateway.zuul;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import cn.jsong.example.spring.gateway.util.ResponseBodyUtils;

/**
 * Zuul的容错回退
 * @author S.J.
 * @date 2018/08/13
 */
@Component
public class ZuulFallBack implements FallbackProvider {

	protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String getRoute() {
		// 服务id，可以用* 或者 null 代表所有服务都过滤
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				// 返回前端的内容
				return new ByteArrayInputStream(ResponseBodyUtils.fallBack().getBytes("UTF-8"));
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders httpHeaders = new HttpHeaders();
				// 设置头
				httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return httpHeaders;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				// 请求网关成功了，所以是ok
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return HttpStatus.OK.value();
			}

			@Override
			public String getStatusText() throws IOException {
				return HttpStatus.OK.getReasonPhrase();
			}

			@Override
			public void close() {
				
			}
		};
	}
	
	@Override
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		
		LOGGER.info("[ZuulFallBack] >>> errorMsg={} ", cause == null ? null : cause.getMessage());

		return this.fallbackResponse();
	}

}
