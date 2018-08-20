package cn.jsong.example.spring.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import cn.jsong.example.spring.gateway.auth.SimpleToken;
import cn.jsong.example.spring.gateway.util.ResponseBodyUtils;

/**
 * 
 * Token过滤器
 * 
 * @author S.J.
 * @date 2018/08/13
 */
@Component
public class AccessTokenFilter extends ZuulFilter {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SimpleToken simpleToken;

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		LOGGER.info("[AccessTokenFilter] >>> send:{} reqeust to : {} ", request.getMethod(),
				request.getRequestURL().toString());

		String accessToken = request.getHeader("token");
		LOGGER.info("[AccessTokenFilter] >>> token=[{}]", accessToken);

		// 校验 token 是否存在
		if (StringUtils.isBlank(accessToken)) {
			// 不进行路由，流程不会往下走
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
			ctx.set("isToken", false);
			ctx.setResponseBody(ResponseBodyUtils.noPermission("token is null"));
			return null;
		}

		// 校验 token 有效性
		if (!simpleToken.isValid(accessToken)) {
			// 不进行路由，流程不会往下走
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
			ctx.set("isToken", false);
			ctx.setResponseBody(ResponseBodyUtils.noPermission("invalid token"));
			return null;
		}

		return null;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		LOGGER.info("[AccessTokenFilter] >>> reqeust_uri=[{}]", request.getRequestURI().toString());
		return simpleToken.isNeedFilter(request.getRequestURI().toString());
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public String filterType() {
		return "pre";
	}

}
