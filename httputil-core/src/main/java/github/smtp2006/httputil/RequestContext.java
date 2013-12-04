/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.Map;

/**
 * The context of Request.
 * 
 * 
 * <pre>
 * Put key into RequestContext, then get value if need.
 * </pre>
 * 
 * 
 * @author hua.wanghuawh
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public interface RequestContext extends Map<String, Object> {

    /**
     * return false as value is null
     * 
     * @param k
     * @return
     */
    boolean getBooleanWithDefaultAsNull(String k);

    /**
     * return the target value as value is null
     * 
     * @param k
     * @param v
     * @return
     */
    Object getWithDefaultAsNull(String k, Object v);
}
