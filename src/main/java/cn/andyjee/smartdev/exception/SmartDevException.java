package cn.andyjee.smartdev.exception;

/**
 * 异常
 * 继承于RuntimeException，可被异常拦截器拦截
 *
 * @author AndyJee
 */
public class SmartDevException extends RuntimeException {

    /**
     * 异常消息
     */
    private String msg;

    /**
     * 异常代码
     */
    private int code = -1;

    public SmartDevException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public SmartDevException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public SmartDevException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public SmartDevException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
