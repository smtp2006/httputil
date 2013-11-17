/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.impl;

import github.smtp2006.httputil.Template;
import github.smtp2006.httputil.TemplateLoader;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author 王华
 * @version 2013年11月17日 下午8:57:39
 * 
 */
public class DigesterTemplateLoader implements TemplateLoader {
    private Map<String, Template> templates = new TreeMap<String, Template>();

    /**
     * @param templates
     */
    public DigesterTemplateLoader(String basePacage) {

    }

    protected void scan(String basePacage) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * github.smtp2006.httputil.TemplateLoader#getTemplate(java.lang.String)
     */
    public Template getTemplate(String name) {
        return templates.get(name);
    }

}
