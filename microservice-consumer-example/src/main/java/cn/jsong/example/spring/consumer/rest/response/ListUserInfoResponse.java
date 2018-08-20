package cn.jsong.example.spring.consumer.rest.response;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ListUserInfoResponse implements Serializable{
    
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户ID")
	private Integer id;
	
	@ApiModelProperty(value = "用户姓名")
    private String name;
	
	@ApiModelProperty(value = "用户年龄")
    private Integer age;
	
	@ApiModelProperty(value = "创建时间")
    private Date createTime;
	
	@ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "ListUserInfoResponse [id=" + id + ", name=" + name + ", age=" + age + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
}