/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 */
public interface NamespaceRequestHolder {

    /**
     * Get request by name with namespace
     * 
     * @param namespace
     * @param name
     * @return
     */
    Request getRequest(String namespace, String name);

    /**
     * 
     * @param namespace
     * @return
     */
    Collection<Request> getAll(String namespace);

    /**
     * 
     * @return
     */
    Collection<String> getAllNamespace();

    Map<String, RequestHolder> getAll();

    /**
     * 
     * @param namespace
     * @return
     */
    RequestHolder getRequestHolder(String namespace);

    void merge(NamespaceRequestHolder nrh);
}
