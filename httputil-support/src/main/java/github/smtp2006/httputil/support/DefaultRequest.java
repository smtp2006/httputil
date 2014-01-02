/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.HTTPMethod;
import github.smtp2006.httputil.HTTPScheme;
import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestContext;
import github.smtp2006.httputil.RequestIdGenerator;
import github.smtp2006.httputil.RequestInterceptor;
import github.smtp2006.httputil.ResponseHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DefaultRequest implements Request, Cloneable {

    protected RequestContext context = new DefaultRequestContext();

    protected Map<String, String> headers = new TreeMap<String, String>();

    protected String host;

    protected Collection<RequestInterceptor> interceptors = new ArrayList<RequestInterceptor>();

    protected HTTPMethod method = HTTPMethod.GET;

    protected String name;

    protected String description;

    protected Map<String, String> parameters = new TreeMap<String, String>();

    protected int port = 80;

//    protected RequestIdGenerator requestIdGenerator = new DefaultRequestIdGenerator();
    protected RequestIdGenerator requestIdGenerator;

    protected ResponseHandler responseHandler = new DefaultResponseHandler();

    protected HTTPScheme scheme = HTTPScheme.http;

    protected String uri = "/";

    public RequestContext getContext() {

        return context;
    }

    public void setContext(RequestContext context) {

        this.context = context;
    }

    public Map<String, String> getHeaders() {

        return headers;
    }

    public void setHeaders(Map<String, String> headers) {

        this.headers = headers;
    }

    public void addHeaders(String name, String value) {

        if (this.headers == null) {
            headers = new TreeMap<String, String>();
        }

        headers.put(name, value);
    }

    public String getHost() {

        return host;
    }

    public void setHost(String host) {

        this.host = host;
    }

    public Collection<RequestInterceptor> getInterceptors() {

        return interceptors;
    }

    public void setInterceptors(Collection<RequestInterceptor> interceptors) {

        this.interceptors = interceptors;
    }

    public HTTPMethod getMethod() {

        return method;
    }

    public void setMethod(HTTPMethod method) {

        this.method = method;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Map<String, String> getParameters() {

        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {

        this.parameters = parameters;
    }

    public void addParameter(String name, String value) {

        if (this.parameters == null) {
            parameters = new TreeMap<String, String>();
        }

        parameters.put(name, value);

    }

    public int getPort() {

        return port;
    }

    public void setPort(int port) {

        this.port = port;
    }

    public RequestIdGenerator getRequestIdGenerator() {

        return requestIdGenerator;
    }

    public void setRequestIdGenerator(RequestIdGenerator requestIdGenerator) {

        this.requestIdGenerator = requestIdGenerator;
    }

    public ResponseHandler getResponseHandler() {

        return responseHandler;
    }

    public void setResponseHandler(ResponseHandler responseHandler) {

        this.responseHandler = responseHandler;
    }

    public HTTPScheme getScheme() {

        return scheme;
    }

    public void setScheme(HTTPScheme scheme) {

        this.scheme = scheme;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        // TODO Auto-generated method stub
        return super.clone();
    }

    /*
     * (non-Javadoc)
     * 
     * @see github.smtp2006.httputil.Request#getDescription()
     */
    @Override
    public String getDescription() {

        return description;
    }

    public String getUri() {

        return uri;
    }

    public void setUri(String uri) {

        this.uri = uri;
    }

    public void setDescription(String description) {

        this.description = description;
    }

}
