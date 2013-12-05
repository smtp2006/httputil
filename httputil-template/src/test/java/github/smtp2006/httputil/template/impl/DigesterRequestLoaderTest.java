/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.template.impl;

import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestHolder;

import org.junit.Test;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DigesterRequestLoaderTest {

    @Test
    public void testLoad() {

        DigesterRequestLoader loader = new DigesterRequestLoader();
        RequestHolder holder = loader.load("sample.xml");
        for (Request r : holder.getAll()) {
            System.out.println(r.getMethod());
            System.out.println(r.getScheme());
            System.out.println(r.getHost());
            System.out.println(r.getPort());
        }
            
    }
}
