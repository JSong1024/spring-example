package cn.jsong.example.spring.gateway.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件参数对象类
 * @author S.J.
 * @date 2018/08/13
 */
@Component
public class PropertiesVO {

	@Value("${token.ignore.filter.uris}")
	private String tokenIgnoreUris;

	private List<String> tokenIgnoreUriList = new ArrayList<>();;
	
	public String getTokenIgnoreUris() {
		return tokenIgnoreUris;
	}

	public void setTokenIgnoreUris(String tokenIgnoreUris) {
		this.tokenIgnoreUris = tokenIgnoreUris;
	}

	public List<String> getTokenIgnoreUriList() {
		if (StringUtils.isNotBlank(tokenIgnoreUris)) {
			tokenIgnoreUriList = Arrays.asList(tokenIgnoreUris.split(","));
		}
		return tokenIgnoreUriList;
	}

	@Override
	public String toString() {
		return "PropertiesVO [tokenIgnoreUris=" + tokenIgnoreUris + "]";
	}
	
}
