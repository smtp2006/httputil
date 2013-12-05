/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author hua.wanghuawh
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public interface Request extends Cloneable {

    /**
     * RequestContext key for http url encode.
     */
    String HTTP_CHARSET = "http.charset";

    /**
     * Default value for HTTP_CHARSET.
     */
    String HTTP_CHARSET_DEFAULT = "UTF-8";

    /**
     * HTTP Scheme
     * 
     * @return
     */
    String getName();

    /**
     * HTTP Scheme
     * 
     * @return
     */
    HTTPScheme getScheme();

    /**
     * HTTP Service`s host
     * 
     * @return
     */
    String getHost();

    /**
     * HTTP Service`s port
     * 
     * @return
     */
    int getPort();

    /**
     * HTTP URI
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
     * HTTP parameters
     * 
     * @return
     */
    Map<String, String> getParameters();

    /**
     * HTTP headers
     * 
     * @return
     */
    Map<String, String> getHeaders();

    /**
     * RequestInterceptor, which can wrap Request with RequestContext.
     * 
     * <pre>
     * Add Parameters
     * </pre>
     * 
     * <pre>
     * Add Headers
     * </pre>
     * 
     * @return
     */
    Collection<RequestInterceptor> getInterceptors();

    /**
     * ResponseHandler, which can handle HTTP Response.
     * 
     * @return
     */
    ResponseHandler getResponseHandler();

    /**
     * RequestContext, The context of request.
     * 
     * <pre>
     * Put key into RequestContext, then get value if need.
     * </pre>
     * 
     * @return
     */
    RequestContext getContext();

    /**
     * Generate unique id to trace request.
     * 
     * @return
     */
    RequestIdGenerator getRequestIdGenerator();
}
