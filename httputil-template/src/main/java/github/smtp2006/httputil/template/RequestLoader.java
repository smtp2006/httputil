/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.template;

import github.smtp2006.httputil.RequestHolder;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public interface RequestLoader {

    RequestHolder load(String file);
}
