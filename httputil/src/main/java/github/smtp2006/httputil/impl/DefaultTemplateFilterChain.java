/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.impl;

import github.smtp2006.httputil.Template;
import github.smtp2006.httputil.TemplateContext;
import github.smtp2006.httputil.TemplateFilter;
import github.smtp2006.httputil.TemplateFilterChain;

import java.util.List;

/**
 * @author 王华
 * @version 2013年11月17日 下午8:25:26
 *
 */
public class DefaultTemplateFilterChain implements TemplateFilterChain {
    private List<TemplateFilter> filters;
    
    /**
     * @param filters
     */
    public DefaultTemplateFilterChain(List<TemplateFilter> filters) {
        super();
        this.filters = filters;
    }

    /* (non-Javadoc)
     * @see github.smtp2006.httputil.TemplateFilterChain#process(github.smtp2006.httputil.Template, github.smtp2006.httputil.TemplateContext)
     */
    public void process(Template template, TemplateContext context) {
        if (filters != null && !filters.isEmpty()) {
            for (TemplateFilter tf: filters) {
                tf.process(template, context);
            }
        }
        
    }

    public List<TemplateFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<TemplateFilter> filters) {
        this.filters = filters;
    }
    
}
