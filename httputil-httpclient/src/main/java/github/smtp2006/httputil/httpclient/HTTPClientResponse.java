/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.httpclient;

import github.smtp2006.httputil.HTTPException;
import github.smtp2006.httputil.Response;

import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;

/**
 * @author 王华
 * @version 2013年11月28日 下午1:59:48
 * 
 */
public class HTTPClientResponse implements Response {
    private HttpResponse proxy;

    /**
     * @param proxy
     */
    public HTTPClientResponse(HttpResponse proxy) {
        super();
        this.proxy = proxy;
    }

    @Override
    public Map<String, String> getAllHeaders() {
        if (proxy.getAllHeaders() != null) {
            Map<String, String> headers = new TreeMap<String, String>();
            for (Header header : proxy.getAllHeaders()) {
                headers.put(header.getName(), header.getValue());
            }

        }
        return null;
    }

    @Override
    public int getStatusCode() {
        return proxy.getStatusLine().getStatusCode();
    }

    @Override
    public InputStream getContent() throws HTTPException {
        try {
            return proxy.getEntity().getContent();
        } catch (Exception e) {
            throw new HTTPException(e);
        }
    }

}
