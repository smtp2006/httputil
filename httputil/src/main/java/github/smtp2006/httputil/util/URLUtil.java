/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 王华
 * @version 2013年11月19日 上午9:51:48
 * 
 */
public class URLUtil {
    public static String format(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        Iterator<String> keyIterator = params.keySet().iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            sb.append(key).append('=').append(URLEncoder.encode(params.get(key), charset));
            if (keyIterator.hasNext()) {
                sb.append('&');
            }
        }
        return sb.toString();
    }
}
