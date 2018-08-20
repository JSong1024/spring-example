package cn.jsong.example.spring.comm.exception;

import cn.jsong.example.spring.comm.enums.ResultEnum;

/**
 * 
 * 公共异常类
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class ExampleException extends RuntimeException {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public ExampleException() {
	}

	public ExampleException(Throwable ex) {
		super(ex);
	}

	public ExampleException(int errorCode) {
		this.errorCode = String.valueOf(errorCode);
	}

	public ExampleException(String errorCode) {
		this.errorCode = errorCode;
	}
	public ExampleException(ResultEnum result) {
		super(result.getMsg());
		this.errorCode = result.getCode();
	}

	public ExampleException(int errorCode, String msg) {
		super(msg);
		this.errorCode = String.valueOf(errorCode);
	}

	public ExampleException(String errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public ExampleException(int errorCode, Throwable ex) {
		super(ex);
		this.errorCode = String.valueOf(errorCode);
	}

	public ExampleException(String errorCode, Throwable ex) {
		super(ex);
		this.errorCode = errorCode;
	}

	public ExampleException(int errorCode, String msg, Throwable ex) {
		super(msg, ex);
		this.errorCode = String.valueOf(errorCode);
	}

	public int getErrorCode() {
		return Integer.parseInt(this.errorCode);
	}

	public String getErrorCodeStr() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = String.valueOf(errorCode);
	}

}
