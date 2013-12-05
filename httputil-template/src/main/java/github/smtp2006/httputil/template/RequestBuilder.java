/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.template;

import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestContext;

import java.util.Map;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public interface RequestBuilder {

    Request build(Request request, RequestContext requestContext, Map<String, String> parameters, Map<String, String> headers);
}
