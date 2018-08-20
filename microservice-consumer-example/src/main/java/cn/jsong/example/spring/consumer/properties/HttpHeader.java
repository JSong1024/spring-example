package cn.jsong.example.spring.consumer.properties;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
/**
 * 
 * Http 访问授权属性封装
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class HttpHeader {
	
	private String userName;
	private String password;
	
	/**
	 * Basic加密中间有空格
	 */
	private static final String BASIC = "Basic";

	private static final String AUTHORIZATION = "Authorization";

	public HttpHeader(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/**
	 * 获取短信提供者用户名密码，并进行HTTP Basic Base64 加密，放入入HttpHeaders里面 Add HTTP Authorization
	 * header, using Basic-Authentication to send user-credentials.
	 * 
	 * @return HttpHeaders
	 */
	public HttpHeaders getHeaders() {
		// 格式: 用户名+英文冒号+密码
		String plainCredentials = userName + ":" + password;
		String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		// 格式: Authorization, Basic+空格+base64加密
		headers.add(AUTHORIZATION, BASIC + " " + base64Credentials);
		headers.setContentType(MediaType.parseMediaType(MediaType.APPLICATION_JSON_UTF8_VALUE));
		return headers;
	}
}
