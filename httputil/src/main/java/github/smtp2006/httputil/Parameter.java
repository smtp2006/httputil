/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

/**
 * @author 王华
 * @version 2013年11月17日 下午7:53:10
 *
 */
public class Parameter {
    /**
     * <K,  V>键值对的Key
     */
    private String name;
    /**
     * <K,  V>键值对的Value
     */
    private String value;
    
    /**
     * 唯一的构造函数
     * @param name Key
     * @param value Value
     */
    public Parameter(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }
    /*
     * Getter & Setter方法域.
     */
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
    
    
}
