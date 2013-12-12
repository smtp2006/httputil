/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.NamespaceRequestHolder;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public interface RequestLoader {

    NamespaceRequestHolder load(String file);
}
