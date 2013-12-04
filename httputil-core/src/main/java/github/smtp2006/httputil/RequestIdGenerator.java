/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * Generate unique id to trace request.
 * 
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public interface RequestIdGenerator {

    /**
     * Generate unique id to trace request.
     * 
     * @return
     */
    String genRequestId();

    /**
     * The requestId key in the url
     * 
     * @return
     */
    String getKey();
}
