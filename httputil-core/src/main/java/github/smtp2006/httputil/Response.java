/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil;

import java.io.InputStream;
import java.util.Map;

/**
 * @author 王华
 * @version 2013年11月28日 下午1:51:14
 * 
 */
public interface Response {

    Map<String, String> getAllHeaders();

    int getStatusCode();

    InputStream getContent()  throws HTTPException;

}
