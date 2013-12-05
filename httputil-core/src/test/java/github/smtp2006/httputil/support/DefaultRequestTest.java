/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.HTTPMethod;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;


/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 *
 */
public class DefaultRequestTest {
    @Test
    public void testClone() throws CloneNotSupportedException {
        DefaultRequest request = new DefaultRequest();
        DefaultRequest target = (DefaultRequest) request.clone();
        Assert.assertEquals(request.getHost(), target.getHost());
        Assert.assertEquals(request.getName(), target.getName());
        Assert.assertEquals(request.getPort(), target.getPort());
        Assert.assertEquals(request.getURI(), target.getURI());
        Assert.assertEquals(request.getContext(), target.getContext());
        Assert.assertEquals(request.getHeaders(), target.getHeaders());
        Assert.assertEquals(request.getInterceptors(), target.getInterceptors());
        Assert.assertEquals(request.getMethod(), target.getMethod());
        Assert.assertEquals(request.getParameters(), target.getParameters());
        Assert.assertEquals(request.getRequestIdGenerator(), target.getRequestIdGenerator());
        Assert.assertEquals(request.getResponseHandler(), target.getResponseHandler());
        Assert.assertEquals(request.getScheme(), target.getScheme());
        
        Map<String, String> headers = new TreeMap<String, String>();
        headers.put("A", "A");
        request.setHeaders(headers);
        
        Map<String, String> parameters = new TreeMap<String, String>();
        parameters.put("A", "A");
        request.setHeaders(headers);
        request.setParameters(parameters);
        request.setHost("www.baidu.com");
        request.setMethod(HTTPMethod.GET);
        request.setName("www.baidu.com");
        request.setPort(80);
        request.setURI("/");
        target = (DefaultRequest) request.clone();
        
        Assert.assertEquals(request.getHost(), target.getHost());
        Assert.assertEquals(request.getName(), target.getName());
        Assert.assertEquals(request.getPort(), target.getPort());
        Assert.assertEquals(request.getURI(), target.getURI());
        Assert.assertEquals(request.getContext(), target.getContext());
        Assert.assertEquals(request.getHeaders(), target.getHeaders());
        Assert.assertEquals(request.getInterceptors(), target.getInterceptors());
        Assert.assertEquals(request.getMethod(), target.getMethod());
        Assert.assertEquals(request.getParameters(), target.getParameters());
        Assert.assertEquals(request.getRequestIdGenerator(), target.getRequestIdGenerator());
        Assert.assertEquals(request.getResponseHandler(), target.getResponseHandler());
        Assert.assertEquals(request.getScheme(), target.getScheme());
    }
}
