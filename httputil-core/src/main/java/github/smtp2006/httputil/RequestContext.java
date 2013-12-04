/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.Map;

/**
 * @author 王华
 * @version 2013年11月28日 上午11:13:01
 * 
 */
public interface RequestContext extends Map<String, Object> {

    /**
     * 当value = null时,返回默认false
     * 
     * @param k
     * @return
     */
    boolean getBooleanWithDefaultAsNull(String k);

    /**
     * 当value = null时,返回指定值
     * 
     * @param k
     * @param v
     * @return
     */
    Object getWithDefaultAsNull(String k, Object v);
}
