/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.RequestContext;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author 王华
 * @version 2013年11月28日 下午2:16:05
 * 
 */
public class DefaultRequestContext extends TreeMap<String, Object> implements RequestContext, Cloneable {

    /**
     * 
     */
    private static final long serialVersionUID = -3134693721489447705L;

    /**
     * @param m
     */
    public DefaultRequestContext(Map<? extends String, ? extends Object> m) {

        super(m);
    }

    /**
     */
    public DefaultRequestContext() {

        super();
    }

    @Override
    public boolean getBooleanWithDefaultAsNull(String k) {

        Object value = super.get(k);
        return value == null ? false : Boolean.valueOf(value.toString());
    }

    @Override
    public Object getWithDefaultAsNull(String k, Object v) {

        Object value = super.get(k);
        return value == null ? v : value;
    }
}
