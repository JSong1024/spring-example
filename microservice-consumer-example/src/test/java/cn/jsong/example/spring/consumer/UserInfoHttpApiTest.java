package cn.jsong.example.spring.consumer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.alibaba.fastjson.JSON;

import cn.jsong.example.spring.consumer.rest.request.AddUserInfoRequest;

public class UserInfoHttpApiTest extends BaseTest {

    @Test
    public void testAddUserInfo() {

    		// 添加用户信息接口
        String uri = "/v1/example/user/info/add";
        
        try {
			AddUserInfoRequest req = new AddUserInfoRequest();
			req.setAge(18);
			req.setName("S.J.");

			long startTime = System.currentTimeMillis();
			
			String resBody = this.postString(uri, JSON.toJSONString(req));
			
			long endTime = System.currentTimeMillis();
			
			System.out.printf("耗时[%s]ms, responseBody=[%s]\n", (endTime - startTime), resBody);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
    @Test
    public void testListUserInfo() {
    	
	    	// 查询所有用户信息列表接口
	    	String uri = "/v1/example/user/info/list";
	    	
	    	try {
	    		long startTime = System.currentTimeMillis();
	    		
	    		String resBody = this.postString(uri, null);
	    		
	    		long endTime = System.currentTimeMillis();
	    		
	    		System.out.printf("耗时[%s]ms, responseBody=[%s]\n", (endTime - startTime), resBody);
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
    	
    }
	
	private String postString(String uri, String jsonBody) throws UnsupportedEncodingException, Exception {

		MockHttpServletRequestBuilder request = post(uri)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8);
		
		if (StringUtils.isNotBlank(jsonBody)) {
			request.content(jsonBody);
		}
		
        String resBody = mvc.perform(request)
        		.andDo(print())
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("retCode", Is.is("0000000")))
        		.andReturn()
        		.getResponse()
        		.getContentAsString();
        return resBody;
    }
}
