/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.Collection;
import java.util.Map;

/**
 * @author 王华
 * @version 2013年11月28日 上午11:12:27
 * 
 */
public interface Request {
    /**
     * 
     */
    String HTTP_CHARSET = "http.charset";
    String HTTP_CHARSET_DEFAULT = "UTF-8";
    String HTTP_RANDOM = "http.random";

    /**
     * 
     * @return
     */
    HTTPScheme getScheme();

    /**
     * 服务地址
     * 
     * @return
     */
    String getHost();

    /**
     * 服务端口
     * 
     * @return
     */
    int getPort();

    /**
     * 服务URI
     * 
     * @return
     */
    String getURI();

    /**
     * HTTPMethod
     * 
     * @return
     */
    HTTPMethod getMethod();

    /**
     * URL参数
     * 
     * @return
     */
    Map<String, String> getParameters();

    /**
     * Header参数
     * 
     * @return
     */
    Map<String, String> getHeaders();

    /**
     * RequestInterceptor,拦截器,可以修改Request
     * 
     * @return
     */
    Collection<RequestInterceptor> getInterceptors();

    /**
     * 处理HTTP Response
     * 
     * @return
     */
    ResponseHandler getResponseHandler();

    /**
     * 返回Request上下文,上下文可以传递参数
     * 
     * @return
     */
    RequestContext getContext();
}
