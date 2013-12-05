/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.xml;

import github.smtp2006.httputil.RequestHolder;

/**
 * @author 王华
 * @version 2013年12月4日 下午7:55:00
 * 
 */
public interface RequestLoader {
    RequestHolder process(String file);
}
