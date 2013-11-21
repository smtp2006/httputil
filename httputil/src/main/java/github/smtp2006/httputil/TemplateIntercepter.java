/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;


/**
 * @author 王华
 * @version 2013年11月18日 下午5:51:47
 *
 */
public interface TemplateIntercepter {
    void process(Template template, TemplateContext context);
}
