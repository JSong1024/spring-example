package cn.jsong.example.spring.comm.enums;
/**
 * 
 * 公共错误码
 * 
 * @author S.J.
 * @date 2018/08/10
 */
public enum ResultEnum {
    /**
     * 系统未知错误
     */
    UNKNOWN_ERROR("-1", "unknown exception"),
    SUCCESS("0000000", "success"),

    //*****************comsumer error code***************************//

    //*****************provider error code***************************//
    PROVIDER_USER_INSERT_EXCEPTION("1020001", "用户信息存储异常"),
    PROVIDER_USER_INSERT_FAILED("1020002", "用户信息存储失败"),
    PROVIDER_USER_UPDATE_EXCEPTION("1020003", "用户信息存储异常"),
    PROVIDER_USER_UPDATE_FAILED("1020004", "用户信息存储失败"),
    PROVIDER_USER_QUERY_LIST_EXCEPTION("1020005", "用户信息列表查询异常"),
    PROVIDER_USER_QUERY_BY_NAME_EXCEPTION("1020006", "通过用户名称查询异常"),
    ;
    
    private String code;
    private String msg;
    private ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    public ResultEnum  replaceMsg(String msg) {
       if (msg != null && !"".equals(msg)) {
           this.msg = msg; 
       }
       return this;
    }

}
