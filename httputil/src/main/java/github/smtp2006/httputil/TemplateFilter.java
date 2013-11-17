/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * @author 王华
 * @version 2013年11月17日 下午8:26:42
 *
 */
public interface TemplateFilter {
    void process(Template template, TemplateContext context);
}
