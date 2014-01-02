/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.httpclient;

import github.smtp2006.httputil.HTTPException;
import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestContext;
import github.smtp2006.httputil.RequestEngine;
import github.smtp2006.httputil.RequestIdGenerator;
import github.smtp2006.httputil.RequestInterceptor;
import github.smtp2006.httputil.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @email hua.wanghuawh@alibaba-inc.com;smtp2006@126.com
 * 
 */
public class HTTPClientRequestEngine implements RequestEngine {

    private static final Logger LOG = LoggerFactory.getLogger(HTTPClientRequestEngine.class);

    private CloseableHttpClient client;

    private HttpClientBuilder httpClientBuilder;

    private RequestConfig requestConfig;

    /**
     * 
     * @throws Exception
     */
    public HTTPClientRequestEngine() throws Exception {

        init();
    }

    /**
     * 
     * @throws Exception
     */
    public void init() throws Exception {

        requestConfig = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();

        // Build SSLContext
        SSLContext sslcontext = buildSSLContext();

        // Build Pool Connection Manager
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(
                getSocketFactoryRegistry(sslcontext));
        connManager.setDefaultMaxPerRoute(20);

        // Build httpClientBuilder
        httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(connManager);
        httpClientBuilder.setSslcontext(sslcontext);

        // Build httpClient
        client = httpClientBuilder.build();
    }

    /**
     * HTTPS
     * 
     * @param sslcontext
     * @return
     */
    private Registry<ConnectionSocketFactory> getSocketFactoryRegistry(SSLContext sslcontext) {

        return RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", buildSSLSocketFactory(sslcontext)).build();
    }

    @Override
    public byte[] process(Request request, RequestContext context) throws HTTPException {

        // Build HttpRequestBase
        HttpRequestBase httpRequest = buildRequest(request, context);

        // invoke
        return processHTTP(httpRequest, request, context);
    }

    /**
     * 创建自定义SSLContext
     * 
     * @return
     * @throws Exception
     */
    private SSLContext buildSSLContext() throws Exception {

        SSLContext sslcontext = SSLContext.getInstance("TLS");
        X509TrustManager trustManager = new TrustAllX509TrustManager();
        sslcontext.init(null, new TrustManager[] { trustManager }, null);
        return sslcontext;
    }

    /**
     * 
     * @param sslcontext
     * @return
     */
    private SSLConnectionSocketFactory buildSSLSocketFactory(SSLContext sslcontext) {

        SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslcontext,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return sf;
    }

    /**
     * 
     * @param httpRequest
     * @param request
     * @param context
     * @return
     * @throws HTTPException
     */
    private byte[] processHTTP(HttpUriRequest httpRequest, Request request, RequestContext context) throws HTTPException {

        HttpResponse httpResponse = null;
        try {
            httpResponse = client.execute(httpRequest);
        } catch (Exception e) {
            throw new HTTPException(e);
        }

        // Convert HttpResponse to Inner Response
        Response response = new HTTPClientResponse(httpResponse);

        // Handle response
        return request.getResponseHandler() == null ? null : request.getResponseHandler().process(response);
    }

    /**
     * Build HTTPRequest
     * 
     * @param request
     * @param context
     * @return
     * @throws HTTPException
     */
    private HttpRequestBase buildRequest(Request request, RequestContext context) throws HTTPException {

        HttpRequestBase httpRequest = null;

        // First, wrap request
        processInterceptors(request, context);

        String url = buildURL(request, context);
        switch (request.getMethod()) {
            case GET:
                httpRequest = buildGetRequest(url, request, context);
                break;
            case POST:
            default:
                break;
        }
        // Add Headers to httpRequest
        processHeaders(httpRequest, request, context);

        // Configure httpRequest
        httpRequest.setConfig(requestConfig);
        return httpRequest;
    }

    private HttpGet buildGetRequest(String url, Request request, RequestContext context) {

        HttpGet get = new HttpGet(url);
        return get;
    }

    private void processHeaders(HttpUriRequest httpRequest, Request request, RequestContext context) {

        if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                httpRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    private void processInterceptors(Request request, RequestContext context) throws HTTPException {

        if (request.getInterceptors() != null && !request.getInterceptors().isEmpty()) {
            for (RequestInterceptor ri : request.getInterceptors()) {
                ri.process(request, context);
            }
        }
    }

    private String buildURL(Request request, RequestContext context) {

        int step = 0;
        StringBuilder sb = new StringBuilder();
        // scheme://<host>:<port> => http://www.baidu.com
        sb.append(request.getScheme().name()).append("://");
        sb.append(request.getHost());

        // If port = 0 or port = 80, continue
        if (0 != request.getPort() && 80 != request.getPort()) {
            sb.append(":").append(request.getPort());
        }
        if (request.getUri() != null) {
            sb.append(request.getUri());
        }
        LOG.info("buildURL[{}]:{}", ++step, sb.toString());

        RequestIdGenerator reqIdGen = request.getRequestIdGenerator();
        if (reqIdGen != null) {
            request.getParameters().put(reqIdGen.getKey(), reqIdGen.genRequestId());
        }
        // If has parameters
        if (request.getParameters() != null && !request.getParameters().isEmpty()) {

            // Get charset for url encode.
            String charset = context.getWithDefaultAsNull(Request.HTTP_CHARSET, Request.HTTP_CHARSET_DEFAULT).toString();
            LOG.info("buildURL[{}]charset:{}", ++step, charset);

            sb.append("?");
            Iterator<String> pit = request.getParameters().keySet().iterator();

            while (pit.hasNext()) {
                try {
                    String key = pit.next();
                    String value = request.getParameters().get(key);
                    if (StringUtils.isEmpty(value)) continue;
                    sb.append(URLEncoder.encode(key, charset));
                    sb.append('=');
                    sb.append(URLEncoder.encode(request.getParameters().get(key), charset));
                    if (pit.hasNext()) {
                        sb.append('&');
                    }
                } catch (UnsupportedEncodingException e) {
                    LOG.error("UnsupportedEncodingException " + charset, e);
                }
            }
        }
        LOG.info("buildURL[{}]:{}", ++step, sb.toString());
        return sb.charAt(sb.length() - 1) == '&' ? sb.substring(0, sb.length() - 1) : sb.toString();
    }
}
