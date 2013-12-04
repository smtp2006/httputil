/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * @author 王华
 * @version 2013年11月29日 上午10:23:12
 * 
 */
public enum ErrorCode {
    Error(-1, "Error"), 
    OK(200, "OK"), 
    IOError(60001, "IOError"),
    IOColseError(60002, "IOColseError");
    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
