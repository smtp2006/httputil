/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;


/**
 * @author 王华
 * @version 2013年11月28日 下午1:51:22
 * 
 */
public interface ResponseHandler {
    int HTTP_OK = 200;

    byte[] process(Response response) throws HTTPException;
}
