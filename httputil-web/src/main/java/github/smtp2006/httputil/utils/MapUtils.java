/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.utils;

import java.util.Map;

/**
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public abstract class MapUtils {

    public static void putAsNotNull(Map<String, Object> map, String key, Object value) {

        if (value != null) {
            map.put(key, value);
        }
    }
}
