/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import github.smtp2006.httputil.util.URLUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.collections.ExtendedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 王华
 * @version 2013年11月17日 下午8:37:59
 * 
 */
public class TemplateEngine implements RuntimeConstants {
    /**
     * The Runtime logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(TemplateEngine.class);
    /**
     * 支持多个相同Key
     */
    private ExtendedProperties overridingProperties = new ExtendedProperties();
    /**
     * Template加载器
     */
    private TemplateLoader templateLoader;
    /**
     * 拦截器,可以修改Template属性
     */
    private List<TemplateIntercepter> interceptors;
    /**
     * Template运行时对象
     */
    private TemplateRuntimeInstance rt;

    /**
     * 默认构造函数
     */
    public TemplateEngine() {
        // do nothing
    }

    /**
     * 带配置文件路径的构造函数
     */
    public TemplateEngine(String propsFilename) {
        init(propsFilename);
    }

    /**
     * 带配置Properties的构造函数.
     */
    public TemplateEngine(Properties props) {
        init(props);

    }

    /**
     * 初始化
     * 
     * @param propsFilename 配置文件
     */
    public void init(String propsFilename) {
        try {
            this.overridingProperties = new ExtendedProperties(propsFilename);
            if (LOG.isInfoEnabled()) {
                LOG.info("config:{}", overridingProperties);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading properties from '" + propsFilename + "'", e);
        }
    }
    /**
     * 初始化
     * @param p
     */
    public void init(Properties p) {
        Enumeration en = p.keys();
        while (en.hasMoreElements()) {
            String key = en.nextElement().toString();
            setProperty(key, p.get(key));
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("config:{}", overridingProperties);
        }
    }

    /**
     * Set Runtime property.
     * 
     * @param key property key
     * @param value property value
     */
    public void setProperty(String key, Object value) {
        this.overridingProperties.setProperty(key, value);
    }

    /**
     * Add a Velocity Runtime property.
     * 
     * @param key
     * @param value
     */
    public void addProperty(String key, Object value) {
        this.overridingProperties.addProperty(key, value);
    }

    /**
     * Clear a Velocity Runtime property.
     * 
     * @param key of property to clear
     */
    public void clearProperty(String key) {
        this.overridingProperties.clearProperty(key);
    }

    /**
     * Get a Velocity Runtime property.
     * 
     * @param key property to retrieve
     * @return property value or null if the property not currently set
     */
    public Object getProperty(String key) {
        return this.overridingProperties.getProperty(key);
    }

    

    public void evaluate(Template template, TemplateContext context, String attrPrefix, String logTag) throws Exception {
        rt.evaluate(template, context, attrPrefix, logTag);
    }

    public Template getTemplate(String name) throws Exception {
        return templateLoader.getTemplate(name);
    }
}
