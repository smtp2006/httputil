/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * @author 王华
 * @version 2013年11月28日 上午11:12:39
 * 
 */
public interface RequestEngine {

    byte[] process(Request request, RequestContext context) throws HTTPException;
}
