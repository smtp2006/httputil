/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.List;

/**
 * @author 王华
 * @version 2013年11月17日 下午7:51:39
 *
 */
public class Template {
    /**
     * 模板的名字,必须全局唯一
     */
    private String name;
    /**
     * HTTP Method
     */
    private Method method;
    /**
     * 参数列表
     */
    private List<Parameter> parameters;
    /**
     * FilterChain，通过FileChain修改/增加HTTP参数
     */
    private TemplateFilterChain filterChain;
    /*
     * Getter & Setter 
     */
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public List<Parameter> getParameters() {
        return parameters;
    }
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
    public TemplateFilterChain getFilterChain() {
        return filterChain;
    }
    public void setFilterChain(TemplateFilterChain filterChain) {
        this.filterChain = filterChain;
    }
    
}
