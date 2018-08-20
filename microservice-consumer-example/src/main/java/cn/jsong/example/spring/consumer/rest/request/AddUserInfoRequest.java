package cn.jsong.example.spring.consumer.rest.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * 添加用户请求参数
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class AddUserInfoRequest {

	@ApiModelProperty(value = "用户名称", required = true)
	@NotBlank(message = "用户名称不能为空")
	private String name;

	@ApiModelProperty(value = "用户年龄", required = true)
	@NotNull(message = "用户年龄不能为空")
    private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "AddUserRequest [name=" + name + ", age=" + age + "]";
	}
    
}
