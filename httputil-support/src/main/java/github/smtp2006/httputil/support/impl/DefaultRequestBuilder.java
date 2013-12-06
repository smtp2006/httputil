/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support.impl;

import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestContext;
import github.smtp2006.httputil.support.RequestBuilder;

import java.util.Map;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DefaultRequestBuilder implements RequestBuilder {

    @Override
    public Request build(Request request, RequestContext requestContext, Map<String, String> parameters,
            Map<String, String> headers) {

        Request result = null;
        try {
            result = (Request) request.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        result.getParameters().putAll(parameters);
        result.getHeaders().putAll(headers);
        return result;
    }

}
