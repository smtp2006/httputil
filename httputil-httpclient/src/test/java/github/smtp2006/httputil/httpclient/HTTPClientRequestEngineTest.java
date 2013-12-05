/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.httpclient;

import github.smtp2006.httputil.HTTPException;
import github.smtp2006.httputil.support.DefaultRequest;
import github.smtp2006.httputil.support.DefaultRequestContext;
import github.smtp2006.httputil.support.DefaultResponseHandler;

import org.junit.Test;

/**
 * @author 王华
 * @version 2013年11月28日 下午2:13:44
 * 
 */
public class HTTPClientRequestEngineTest {

    @Test
    public void test_get() throws Exception {

        HTTPClientRequestEngine engine = new HTTPClientRequestEngine();
        DefaultRequest baiduRequest = new DefaultRequest();
        baiduRequest.setHost("www.baidu.com");
        baiduRequest.setResponseHandler(new DefaultResponseHandler() {

            protected void peek(byte[] content) throws HTTPException {

                System.err.println(new String(content));
            }
        });
        engine.process(baiduRequest, new DefaultRequestContext());
        
        StringBuilder sb = new StringBuilder("www.baidu.com?a=b&");
        System.err.println(sb.charAt(sb.length() - 1) == '&' ? sb.substring(0, sb.length() - 1) : sb.toString());
        
    }
}
