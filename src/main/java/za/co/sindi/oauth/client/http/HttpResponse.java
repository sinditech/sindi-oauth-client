/**
 * 
 */
package za.co.sindi.oauth.client.http;

import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author Buhake Sindi
 * @since 30 January 2024
 */
public interface HttpResponse {

	public int getStatusCode();
	
	public HttpRequest getRequest();
	
	public HttpHeaders getHeaders();
	public String getHeader(final HttpHeaderName name);
	public String getHeader(final String name);
	
	public ByteBuffer getBody();
	public InputStream getBodyInputStream();
	public String getBodyString();
}
