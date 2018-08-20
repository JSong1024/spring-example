package cn.jsong.example.spring.consumer.constant;

/**
 * 
 * 远程服务访问地址
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class UrlConstants {

	private final static String POST_HTTP_PREFIX = "http://";

	@SuppressWarnings("unused")
	private final static String POST_HTTPS_PREFIX = "https://";

	/************ 服务请求prefix *****************/

	private final static String PROVIDER_URL_PREFIX = POST_HTTP_PREFIX + "microservice-provider-example";
	
	/****************************************/

	/*** ################user################ *****/
	/**
	 * 添加用户
	 */
	public final static String USER_ADD_URL = PROVIDER_URL_PREFIX + "/v1/user/add";
	
	/**
	 * 查询所有用户
	 */
	public final static String USER_LIST_URL = PROVIDER_URL_PREFIX + "/v1/user/list";
}
