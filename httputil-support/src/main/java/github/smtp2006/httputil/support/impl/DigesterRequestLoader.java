/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support.impl;

import static org.apache.commons.beanutils.BeanUtils.setProperty;
import github.smtp2006.httputil.HTTPMethod;
import github.smtp2006.httputil.HTTPScheme;
import github.smtp2006.httputil.RequestHolder;
import github.smtp2006.httputil.support.DefaultRequest;
import github.smtp2006.httputil.support.DefaultRequestHolder;
import github.smtp2006.httputil.support.RequestLoader;

import java.io.InputStream;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.Rule;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DigesterRequestLoader implements RequestLoader {

    /*
     * (non-Javadoc)
     * 
     * @see
     * github.smtp2006.httputil.template.RequestLoader#load(java.lang.String)
     */
    @Override
    public RequestHolder load(String file) {

        Digester digester = new Digester();
        RequestHolder holder = new DefaultRequestHolder();
        digester.push(holder);
        InputStream fileInputStream = DigesterRequestLoader.class.getClassLoader().getResourceAsStream(file);
        digester.addObjectCreate("requestHolder/request", "class", DefaultRequest.class);

        digester.addSetProperties("requestHolder/request", "id", "name");
        digester.addBeanPropertySetter("requestHolder/request/host");
        digester.addBeanPropertySetter("requestHolder/request/port");
        digester.addBeanPropertySetter("requestHolder/request/uri");

        digester.addRule("requestHolder/request/method", new HTTPMethodRule());
        digester.addRule("requestHolder/request/scheme", new HTTPSchemeRule());
        digester.addSetNext("requestHolder/request", "addRequest");
        try {
            digester.parse(fileInputStream);
        } catch (Exception e) {}
        return holder;
    }

    private static class HTTPSchemeRule extends Rule {

        private HTTPScheme scheme;

        /**
         */
        public HTTPSchemeRule() {

        }

        public void body(String namespace, String name, String text) throws Exception {

            scheme = HTTPScheme.valueOf(text.trim().toLowerCase());
        }

        @Override
        public void end(String namespace, String name) throws Exception {

            // Get a reference to the top object
            Object top = getDigester().peek();
            setProperty(top, "scheme", scheme);
        }

    }

    private static class HTTPMethodRule extends Rule {

        private HTTPMethod method;

        /**
         */
        public HTTPMethodRule() {

        }

        public void body(String namespace, String name, String text) throws Exception {

            method = HTTPMethod.valueOf(text.trim().toUpperCase());
        }

        @Override
        public void end(String namespace, String name) throws Exception {

            // Get a reference to the top object
            Object top = getDigester().peek();
            setProperty(top, "method", method);
        }

    }
}
