/**
 * 
 */
package za.co.sindi.oauth.client.http;

import java.net.URI;
import java.nio.ByteBuffer;

/**
 * @author Buhake Sindi
 * @since 30 January 2024
 */
public interface HttpRequest {

	public HttpMethod getMethod();
	
	public URI getURI();
	
	public void addHeader(final HttpHeaderName name, final String value);
	
	public void addHeader(final String name, final String value);
	
	public void setHeader(final HttpHeaderName name, final String value);
	
	public void setHeader(final String name, final String value);
	
	public HttpHeaders getHeaders();
	
	public ByteBuffer getBody();
	
	public long getContentLength();
	
	public void setBody(BodyContent content);
}
