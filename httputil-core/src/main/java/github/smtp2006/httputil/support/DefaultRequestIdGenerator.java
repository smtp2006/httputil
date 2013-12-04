/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import java.util.UUID;

import github.smtp2006.httputil.RequestIdGenerator;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class DefaultRequestIdGenerator implements RequestIdGenerator {

    /*
     * (non-Javadoc)
     * 
     * @see github.smtp2006.httputil.RequestIdGenerator#genRequestId()
     */
    @Override
    public String genRequestId() {

        return UUID.randomUUID().toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see github.smtp2006.httputil.RequestIdGenerator#getKey()
     */
    @Override
    public String getKey() {

        return "__rid";
    }

}
