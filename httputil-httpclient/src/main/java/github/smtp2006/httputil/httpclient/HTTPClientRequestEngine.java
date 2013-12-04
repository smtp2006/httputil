/**
 * Copyright (c) 2013.
 */
package github.smtp2006.httputil.httpclient;

import github.smtp2006.httputil.HTTPException;
import github.smtp2006.httputil.Request;
import github.smtp2006.httputil.RequestContext;
import github.smtp2006.httputil.RequestEngine;
import github.smtp2006.httputil.RequestInterceptor;
import github.smtp2006.httputil.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
 * @author 王华
 * @version 2013年11月28日 上午11:46:42
 * 
 */
public class HTTPClientRequestEngine implements RequestEngine {
    /**
     * 随机数参数
     */
    private static final String RANDOM_PARAM = "_r";
    private static final Logger LOG = LoggerFactory.getLogger(HTTPClientRequestEngine.class);
    /**
     * HTTPClient
     */
    private CloseableHttpClient client;
    /**
     * HTTPClient builder, 用来创建HTTPClient
     */
    private HttpClientBuilder httpClientBuilder;
    /**
     * HTTP Request配置
     */
    private RequestConfig requestConfig;

    /**
     * 构造函数
     * 
     * @throws Exception
     */
    public HTTPClientRequestEngine() throws Exception {
        init();
    }

    /**
     * 初始化
     * 
     * @throws Exception
     */
    private void init() throws Exception {
        requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();

        // 构造SSLContext
        SSLContext sslcontext = buildSSLContext();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(getSocketFactoryRegistry(sslcontext));
        connManager.setDefaultMaxPerRoute(20);
        
        httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(connManager);
        httpClientBuilder.setSslcontext(sslcontext);
        
        client = httpClientBuilder.build();
    }
    /**
     * HTTPS特殊处理
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
        HttpRequestBase httpRequest = buildRequest(request, context);
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
     * 创建自定义SSLConnectionSocketFactory
     * 
     * @param sslcontext
     * @return
     */
    private SSLConnectionSocketFactory buildSSLSocketFactory(SSLContext sslcontext) {
        SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return sf;
    }
    /**
     * 提交HTTP请求
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
        Response response = new HTTPClientResponse(httpResponse);
        return request.getResponseHandler() == null ? null : request.getResponseHandler().process(response);
    }
    /**
     * 构造HTTPRequest
     * 
     * @param request
     * @param context
     * @return
     * @throws HTTPException
     */
    private HttpRequestBase buildRequest(Request request, RequestContext context) throws HTTPException {
        HttpRequestBase httpRequest = null;
        // Request拦截器
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
        // 处理Headers
        processHeaders(httpRequest, request, context);
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
        // scheme://<host>:<port> => http://my.yunos.com
        sb.append(request.getScheme().name()).append("://");
        sb.append(request.getHost()).append(":").append(request.getPort());
        sb.append(request.getURI());
        LOG.info("buildURL[{}]:{}", ++step, sb.toString());
        boolean hasQueryParam = false;
        if (request.getParameters() != null && !request.getParameters().isEmpty()) {
            sb.append("?");
            hasQueryParam = true;
            String charset = context.getWithDefaultAsNull(Request.HTTP_CHARSET, Request.HTTP_CHARSET_DEFAULT).toString();
            LOG.info("buildURL[{}]charset:{}", ++step, charset);
            Iterator<String> pit = request.getParameters().keySet().iterator();
            try {
                while (pit.hasNext()) {
                    String key = pit.next();
                    sb.append(URLEncoder.encode(key, charset));
                    sb.append('=');
                    sb.append(URLEncoder.encode(request.getParameters().get(key), charset));
                    if (pit.hasNext()) {
                        sb.append('&');
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        boolean random = context.getBooleanWithDefaultAsNull(Request.HTTP_RANDOM);
        if (random) {
            sb.append(hasQueryParam ? '&' : '?').append(RANDOM_PARAM).append('=').append(System.currentTimeMillis());
        }
        LOG.info("buildURL[{}]:{}", ++step, sb.toString());
        return sb.toString();
    }
}
