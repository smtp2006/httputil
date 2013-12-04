/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestHolder;

import java.util.Map;
import java.util.TreeMap;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DefaultRequestHolder implements RequestHolder {

    private Map<String, Request> requestMap = new TreeMap<String, Request>();

    /*
     * (non-Javadoc)
     * 
     * @see github.smtp2006.httputil.RequestHolder#getRequest(java.lang.String)
     */
    @Override
    public Request getRequest(String name) {

        return requestMap.get(name);
    }

    /**
     * Add request
     * 
     * @param request
     * @return
     */
    public Request addRequest(Request request) {

        return requestMap.put(request.getName(), request);
    }
}
