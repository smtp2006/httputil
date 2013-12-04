/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * @author 王华
 * @version 2013年11月28日 上午11:14:41
 * 
 */
public class HTTPException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5381274800799434771L;
    private int code;

    /**
     * @param cause
     */
    public HTTPException(Throwable cause) {
        super(cause);
    }

    public HTTPException(Throwable cause, ErrorCode error) {
        super(error.getMsg(), cause);
        this.code = error.getCode();
    }

    public HTTPException(int code) {
        this.code = code;
    }

    public HTTPException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
