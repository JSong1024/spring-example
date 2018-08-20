package cn.jsong.example.spring.comm.result;

import cn.jsong.example.spring.comm.enums.ResultEnum;

/**
 * 
 * 响应数据封装工具类
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class ResultUtils {
	/**
	 * @Title: success
	 * @Description: 成功响应封装（无数据）
	 * @return Result<T>
	 */
	public static <T> Result<T> success() {
		Result<T> result = new Result<>();
		result.setRetCode(ResultEnum.SUCCESS.getCode());
		result.setRetMsg(ResultEnum.SUCCESS.getMsg());
		return result;
	}

	/**
	 * @Title: success
	 * @Description: 成功响应封装（带业务数据）
	 * @param data		响应的业务数据
	 * @return Result<T>
	 */
	public static <T> Result<T> success(T data) {
		Result<T> result = new Result<>();
		result.setRetCode(ResultEnum.SUCCESS.getCode());
		result.setRetMsg(ResultEnum.SUCCESS.getMsg());
		result.setResult(data);
		return result;
	}

	/**
	 * @Title: error
	 * @Description: 错误响应封装（错误枚举）
	 * @param error		错误枚举对象
	 * @return Result<T>
	 */
	public static <T> Result<T> error(ResultEnum error) {
		Result<T> result = new Result<>();
		result.setRetCode(error.getCode());
		result.setRetMsg(error.getMsg());
		return result;
	}

	/**
	 * @Title: error
	 * @Description: 错误响应封装（信息码）
	 * @param code		错误编号
	 * @param msg		错误信息
	 * @return Result<T>
	 */
	public static <T> Result<T> error(String code, String msg) {
		Result<T> result = new Result<>();
		result.setRetCode(code);
		result.setRetMsg(msg);
		result.setResult(null);
		return result;
	}

}
