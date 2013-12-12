/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.Collection;

/**
 * 
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 */
public interface RequestHolder {

    String getNamespace();

    String getDescription();

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
     * Get request by name
     * 
     * @param name
     * @return
     */
    Request getRequest(String name);

    /**
     * 
     * @return
     */
    Collection<Request> getAll();

    void merge(RequestHolder rh);
}
