package cn.jsong.example.spring.comm.result;
/**
 * 
 * 响应数据封装
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public class Result<T> {

	private String retCode;
	private String retMsg;
	private T result;
	/**
	 * @return the retCode
	 */
	public String getRetCode() {
		return retCode;
	}
	/**
	 * @param retCode the retCode to set
	 */
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	/**
	 * @return the retMsg
	 */
	public String getRetMsg() {
		return retMsg;
	}
	/**
	 * @param retMsg the retMsg to set
	 */
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	/**
	 * @return the result
	 */
	public T getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(T result) {
		this.result = result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Result [retCode=" + retCode + ", retMsg=" + retMsg + ", result=" + result + "]";
	}
	
}
