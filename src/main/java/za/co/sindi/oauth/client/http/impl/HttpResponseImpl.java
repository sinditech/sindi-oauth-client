/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

import za.co.sindi.oauth.client.http.BodyContent;
import za.co.sindi.oauth.client.http.HttpHeaderName;
import za.co.sindi.oauth.client.http.HttpHeaders;
import za.co.sindi.oauth.client.http.HttpRequest;
import za.co.sindi.oauth.client.http.HttpResponse;

/**
 * @author Buhake Sindi
 * @since 02 February 2024
 */
public class HttpResponseImpl implements HttpResponse {
	
	private int statusCode;
	private HttpRequest request;
	private HttpHeaders responseHeaders;
	private BodyContent body;

	/**
	 * @param statusCode
	 * @param request
	 * @param responseHeaders
	 * @param body
	 */
	public HttpResponseImpl(int statusCode, HttpRequest request, HttpHeaders responseHeaders, BodyContent body) {
		super();
		this.statusCode = statusCode;
		this.request = Objects.requireNonNull(request, "A HTTP request is required.");
		this.responseHeaders = Objects.requireNonNull(responseHeaders, "A HTTP response header is required.");
		this.body = Objects.requireNonNull(body, "A HTTP response body is required.");
	}

	@Override
	public int getStatusCode() {
		// TODO Auto-generated method stub
		return statusCode;
	}

	@Override
	public HttpRequest getRequest() {
		// TODO Auto-generated method stub
		return request;
	}

	@Override
	public HttpHeaders getHeaders() {
		// TODO Auto-generated method stub
		return responseHeaders;
	}

	@Override
	public String getHeader(HttpHeaderName name) {
		// TODO Auto-generated method stub
		return responseHeaders.containsHeader(name) ? responseHeaders.getValues(name)[0] : null;
	}

	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return responseHeaders.containsHeader(name) ? responseHeaders.getValues(name)[0] : null;
	}

	@Override
	public ByteBuffer getBody() {
		// TODO Auto-generated method stub
		return body.getContent();
	}

	@Override
	public String getBodyString() {
		// TODO Auto-generated method stub
		return body.toString();
	}

	@Override
	public InputStream getBodyInputStream() {
		// TODO Auto-generated method stub
		return body.getContentInputStream();
	}
}
