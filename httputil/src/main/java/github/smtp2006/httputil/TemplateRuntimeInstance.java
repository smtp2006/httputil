/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * @author 王华
 * @version 2013年11月20日 上午9:58:28
 * 
 */
public interface TemplateRuntimeInstance {

    void evaluate(Template template, TemplateContext context, String attrPrefix, String logTag) throws Exception;
}
