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

    /**
     * Get request by name
     * 
     * @param name
     * @return
     */
    Request getRequest(String name);
    
    Collection<Request> getAll();
}
