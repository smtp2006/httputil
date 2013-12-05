/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * 
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public interface RequestEngine {

    byte[] process(Request request, RequestContext context) throws HTTPException;
}
