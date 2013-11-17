/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * @author 王华
 * @version 2013年11月17日 下午8:20:53
 *
 */
public interface TemplateFilterChain {
    void process(Template template, TemplateContext context);
}
