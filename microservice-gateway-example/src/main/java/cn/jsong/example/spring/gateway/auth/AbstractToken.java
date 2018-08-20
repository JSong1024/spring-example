package cn.jsong.example.spring.gateway.auth;

/**
 * 
 * token抽象类
 * 
 * @author S.J.
 * @date 2018/08/13
 */
public abstract class AbstractToken {

	/**
	 * @Title: isValid
	 * @Description: 验证token的有效性
	 * @param accessToken
	 * @return boolean
	 */
	public abstract boolean isValid(String accessToken);
	
	public abstract boolean isNeedFilter(String uri);

}
