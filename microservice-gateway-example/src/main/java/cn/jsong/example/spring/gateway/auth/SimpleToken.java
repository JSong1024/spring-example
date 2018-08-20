package cn.jsong.example.spring.gateway.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import cn.jsong.example.spring.gateway.vo.PropertiesVO;

/**
 * 
 * 简单token实现
 * 
 * @author S.J.
 * @date 2018/08/13
 */
@Component
public class SimpleToken extends AbstractToken {

	private static final String DEFAULT_SIMPLE_TOKEN = "ABCDEFGHIJKLMNOPQRSTUVWXZY";
	
	@Autowired
	private PropertiesVO properties;
	
	private PathMatcher pathMatcher = new AntPathMatcher();
	
	@Override
	public boolean isValid(String accessToken) {
		return DEFAULT_SIMPLE_TOKEN.equalsIgnoreCase(accessToken);
	}

	@Override
	public boolean isNeedFilter(String uri) {
		if (properties.getTokenIgnoreUriList().isEmpty()) {
			return true;
		}
		
		for (String tokenIgnoreUri : properties.getTokenIgnoreUriList()) {
			if (StringUtils.isBlank(tokenIgnoreUri)) {
				continue;
			}
			if (this.pathMatcher.match(tokenIgnoreUri, uri)) {
				return false;
			}
		}
		return true;
	}

}
