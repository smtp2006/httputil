/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private String api;
    /**
     * 参数列表
     */
    private Map<String, String> parameters;
    private Map<String, String> headers;
    /**
     * 拦截器
     */
    private List<TemplateIntercepter> interceptors;
    /**
     * 拦截器
     */
    private ResponseHandler responseHandler;
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

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String name, String value) {
        if (this.parameters == null) {
            this.parameters = new TreeMap<String, String>();
        }
        this.parameters.put(name, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String name, String value) {
        if (this.headers == null) {
            this.headers = new TreeMap<String, String>();
        }
        this.headers.put(name, value);
    }

    public List<TemplateIntercepter> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<TemplateIntercepter> interceptors) {
        this.interceptors = interceptors;
    }

    public void addInterceptor(TemplateIntercepter interceptor) {
        if (this.interceptors == null) {
            this.interceptors = new ArrayList<TemplateIntercepter>();
        }
        this.interceptors.add(interceptor);
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

}
