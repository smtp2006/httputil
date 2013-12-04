/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * 请求拦截器,可以修改Request
 * @author 王华
 * @version 2013年11月28日 上午11:24:12
 *
 */
public interface RequestInterceptor {
    Request process(Request request, RequestContext context) throws HTTPException;
}
