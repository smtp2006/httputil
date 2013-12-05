/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.support;

import github.smtp2006.httputil.ErrorCode;
import github.smtp2006.httputil.HTTPException;
import github.smtp2006.httputil.Response;
import github.smtp2006.httputil.ResponseHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author 王华
 * @version 2013年11月29日 上午10:20:22
 * 
 */
public class DefaultResponseHandler implements ResponseHandler, Cloneable {

    @Override
    public byte[] process(Response response) throws HTTPException {

        processStatusCode(response);
        byte[] content = processContent(response);
        peek(content);
        return content;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return this;
    }

    protected int processStatusCode(Response response) throws HTTPException {

        int statusCode = response.getStatusCode();
        if (HTTP_OK != statusCode) {
            throw new HTTPException(statusCode);
        }
        return statusCode;
    }

    protected Map<String, String> processHeaders(Response response) {

        return response.getAllHeaders();
    }

    protected byte[] processContent(Response response) throws HTTPException {

        InputStream input = response.getContent();
        if (input != null) {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = -1;
            try {
                while ((len = input.read(buffer)) != -1) {
                    bao.write(buffer, 0, len);
                }
            } catch (IOException e) {
                throw new HTTPException(e, ErrorCode.IOError);
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    throw new HTTPException(e, ErrorCode.IOColseError);
                }
            }
            return bao.toByteArray();
        }
        return null;
    }

    protected void peek(byte[] content) throws HTTPException {

    }
}
