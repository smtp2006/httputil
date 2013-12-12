/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support.impl;

import github.smtp2006.httputil.NamespaceRequestHolder;
import github.smtp2006.httputil.Request;

import org.junit.Test;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DigesterRequestLoaderTest {

    @Test
    public void testLoad() {

        DigesterRequestLoader loader = new DigesterRequestLoader();
        NamespaceRequestHolder nrh = loader.load("sample.xml");
        for (String namespace : nrh.getAllNamespace())
            for (Request r : nrh.getAll(namespace)) {
                System.out.println(r.getMethod());
                System.out.println(r.getScheme());
                System.out.println(r.getHost());
                System.out.println(r.getPort());
                System.out.println(r.getHeaders());
                System.out.println(r.getParameters());
            }

    }
}
