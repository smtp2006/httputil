/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.impl;

import github.smtp2006.httputil.Template;
import github.smtp2006.httputil.TemplateContext;
import github.smtp2006.httputil.TemplateIntercepter;
import github.smtp2006.httputil.TemplateRuntimeInstance;
import github.smtp2006.httputil.util.URLUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.web.client.RestTemplate;

/**
 * @author 王华
 * @version 2013年11月21日 上午9:28:44
 * 
 */
public class DefaultTemplateRuntimeInstance implements TemplateRuntimeInstance {
    private List<TemplateIntercepter> interceptors;
    private RestTemplate restTemplate;
    
    public List<TemplateIntercepter> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<TemplateIntercepter> interceptors) {
        this.interceptors = interceptors;
    }

    protected void prepareTemplate(Template template, TemplateContext context, String attrPrefix, String logTag) {
        List<TemplateIntercepter> tempInterceptors = template.getInterceptors() == null ? this.interceptors : template
                .getInterceptors();
        if (tempInterceptors != null && tempInterceptors.size() > 0) {
            for (TemplateIntercepter ti : tempInterceptors) {
                ti.process(template, context);
            }
        }
    }

    protected String constructURL(Template template, TemplateContext context) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(template.getApi());
        if (template.getParameters() != null && !template.getParameters().isEmpty()) {
            sb.append('?').append(URLUtil.format(template.getParameters(), "UTF-8"));
        }
        return sb.toString();
    }

    /* (non-Javadoc)
     * @see github.smtp2006.httputil.TemplateRuntimeInstance#evaluate(github.smtp2006.httputil.Template, github.smtp2006.httputil.TemplateContext, java.lang.String, java.lang.String)
     */
    public void evaluate(Template template, TemplateContext context, String attrPrefix, String logTag) throws Exception {
        prepareTemplate(template, context, attrPrefix, logTag);        
    }
    
    
}
