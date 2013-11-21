/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.impl;

import github.smtp2006.httputil.Template;
import github.smtp2006.httputil.TemplateLoader;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 王华
 * @version 2013年11月17日 下午8:57:39
 * 
 */
public class DigesterTemplateLoader implements TemplateLoader {
    /**
     * The Runtime logger.
     */
    private Logger log = LoggerFactory.getLogger(DigesterTemplateLoader.class);
    private Map<String, Template> templates = new TreeMap<String, Template>();

    /**
     * @param templates
     * @throws IOException
     */
    public DigesterTemplateLoader(String config) throws IOException {
        load(config);
    }

    protected void load(String config) throws IOException {
        log.info("templates:{}", templates);
    }

    protected Map<String, Template> loadTemplatesFromURL(URL url) {
        Map<String, Template> temps = new TreeMap<String, Template>();
        return temps;
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
