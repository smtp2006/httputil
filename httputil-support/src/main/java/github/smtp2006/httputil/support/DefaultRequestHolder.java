/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.HTTPScheme;
import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestHolder;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DefaultRequestHolder implements RequestHolder {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRequestHolder.class);

    private Map<String, Request> requestMap = new TreeMap<String, Request>();
    /**
     * 
     */
    protected String namespace;
    protected String description;

    protected HTTPScheme scheme = HTTPScheme.http;

    protected String host;

    protected int port = 80;

    @Override
    public Request getRequest(String name) {

        return requestMap.get(name);
    }

    @Override
    public Collection<Request> getAll() {

        return requestMap.values();
    }

    public RequestHolder addRequest(Request request) {

        if (request.getScheme() == null) {
            try {
                BeanUtils.setProperty(request, "scheme", scheme);
            } catch (Exception e) {
                logger.error("setProperty[scheme] error");
                throw new RuntimeException(e);
            }
        }
        if (StringUtils.isBlank(request.getHost())) {
            try {
                BeanUtils.setProperty(request, "host", host);
            } catch (Exception e) {
                logger.error("setProperty[host] error");
                throw new RuntimeException(e);
            }
        }

        if (request.getPort() <= 0) {
            try {
                BeanUtils.setProperty(request, "port", port);
            } catch (Exception e) {
                logger.error("setProperty[port] error");
                throw new RuntimeException(e);
            }
        }
        requestMap.put(request.getName(), request);
        return this;
    }

    // =========================================================
    // Getter & Setter
    // =========================================================
    public HTTPScheme getScheme() {

        return scheme;
    }

    public void setScheme(HTTPScheme scheme) {

        this.scheme = scheme;
    }

    public String getHost() {

        return host;
    }

    public void setHost(String host) {

        this.host = host;
    }

    public int getPort() {

        return port;
    }

    public void setPort(int port) {

        this.port = port;
    }

    
    public String getNamespace() {
    
        return namespace;
    }

    
    public void setNamespace(String namespace) {
    
        this.namespace = namespace;
    }

    @Override
    public void merge(RequestHolder rh) {
        for (Request request : rh.getAll()) {
            addRequest(request);
        }
    }

    @Override
    public String getDescription() {

        return description;
    }

    
    public void setDescription(String description) {
    
        this.description = description;
    }
    
    
}
