/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.NamespaceRequestHolder;
import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestHolder;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DefaultNamespaceRequestHolder implements NamespaceRequestHolder {

    // =========================================================
    // Properties
    // =========================================================
    private Map<String, RequestHolder> requestHolderMap = new TreeMap<String, RequestHolder>();

    @Override
    public Request getRequest(String namespace, String name) {

        RequestHolder rh = requestHolderMap.get(namespace);
        return rh == null ? null : rh.getRequest(name);
    }

    @Override
    public Collection<Request> getAll(String namespace) {

        RequestHolder rh = requestHolderMap.get(namespace);
        return rh == null ? null : rh.getAll();
    }

    @Override
    public RequestHolder getRequestHolder(String namespace) {

        return requestHolderMap.get(namespace);
    }

    @Override
    public Collection<String> getAllNamespace() {

        return requestHolderMap.keySet();
    }

    public void addRequestHolder(RequestHolder rh) {

        requestHolderMap.put(rh.getNamespace(), rh);
    }

    public void merge(NamespaceRequestHolder nrh) {

        for (String namespace : nrh.getAllNamespace()) {
            RequestHolder rh = requestHolderMap.get(namespace);
            if (rh == null) {
                requestHolderMap.put(namespace, nrh.getRequestHolder(namespace));
            } else {
                rh.merge(nrh.getRequestHolder(namespace));
            }

        }

    }

    @Override
    public Map<String, RequestHolder> getAll() {

        return requestHolderMap;
    }
}
