/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.net.URI;
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
	private URI uri;
	private HttpHeadersImpl requestHeaders;
	private BodyContent content;
	
	/**
	 * @param method
	 * @param uri
	 */
	public HttpRequestImpl(HttpMethod method, URI uri) {
		this(method, uri, new HttpHeadersImpl(), null);
	}
	
	/**
	 * @param method
	 * @param uri
	 * @param requestHeaders
	 */
	public HttpRequestImpl(HttpMethod method, URI uri, HttpHeadersImpl requestHeaders) {
		this(method, uri, requestHeaders, null);
	}

	/**
	 * @param method
	 * @param uri
	 * @param requestHeaders
	 * @param content
	 */
	public HttpRequestImpl(HttpMethod method, URI uri, HttpHeadersImpl requestHeaders, BodyContent content) {
		super();
		this.method = Objects.requireNonNull(method, "HTTP request method is required.");
		this.uri = Objects.requireNonNull(uri, "HTTP request URI is required.");
		this.requestHeaders = Objects.requireNonNull(requestHeaders, "HTTP request headers is required.");
		this.content = content;
	}

	@Override
	public HttpMethod getMethod() {
		// TODO Auto-generated method stub
		return method;
	}

	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return uri;
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
