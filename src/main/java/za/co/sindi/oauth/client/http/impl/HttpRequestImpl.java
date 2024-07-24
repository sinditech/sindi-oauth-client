/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Objects;

import za.co.sindi.oauth.client.http.BodyContent;
import za.co.sindi.oauth.client.http.HttpHeaderName;
import za.co.sindi.oauth.client.http.HttpHeaders;
import za.co.sindi.oauth.client.http.HttpMethod;
import za.co.sindi.oauth.client.http.HttpRequest;

/**
 * @author Buhake Sindi
 * @since 02 February 2024
 */
public class HttpRequestImpl implements HttpRequest {
	
	private HttpMethod method;
	private URL url;
	private HttpHeadersImpl requestHeaders;
	private BodyContent content;
	
	/**
	 * @param method
	 * @param url
	 * @param requestHeaders
	 * @param content
	 */
	public HttpRequestImpl(HttpMethod method, URL url) {
		this(method, url, new HttpHeadersImpl(), null);
	}
	
	/**
	 * @param method
	 * @param url
	 * @param requestHeaders
	 */
	public HttpRequestImpl(HttpMethod method, URL url, HttpHeadersImpl requestHeaders) {
		this(method, url, requestHeaders, null);
	}

	/**
	 * @param method
	 * @param url
	 * @param requestHeaders
	 * @param content
	 */
	public HttpRequestImpl(HttpMethod method, URL url, HttpHeadersImpl requestHeaders, BodyContent content) {
		super();
		this.method = Objects.requireNonNull(method, "HTTP request method is required.");
		this.url = Objects.requireNonNull(url, "HTTP request URL is required.");
		this.requestHeaders = Objects.requireNonNull(requestHeaders, "HTTP request headers is required.");
		this.content = content;
	}

	@Override
	public HttpMethod getMethod() {
		// TODO Auto-generated method stub
		return method;
	}

	@Override
	public URL getURL() {
		// TODO Auto-generated method stub
		return url;
	}

	@Override
	public HttpHeaders getHeaders() {
		// TODO Auto-generated method stub
		return requestHeaders;
	}

	@Override
	public ByteBuffer getBody() {
		// TODO Auto-generated method stub
		return content == null ? null : content.getContent();
	}

	@Override
	public void addHeader(HttpHeaderName name, String value) {
		// TODO Auto-generated method stub
		requestHeaders.addHeader(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub
		requestHeaders.addHeader(name, value);
	}

	@Override
	public void setHeader(HttpHeaderName name, String value) {
		// TODO Auto-generated method stub
		requestHeaders.setHeader(name, value);
	}

	@Override
	public void setHeader(String name, String value) {
		// TODO Auto-generated method stub
		requestHeaders.setHeader(name, value);
	}

	@Override
	public void setBody(BodyContent content) {
		// TODO Auto-generated method stub
		this.content = content;
		if (content != null) {
			setHeader(HttpHeaderName.CONTENT_TYPE, content.getContentType());
			setHeader(HttpHeaderName.CONTENT_LENGTH, String.valueOf(content.getContentLength()));
		}
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return content == null ? 0 : content.getContentLength();
	}
}
