/**
 * 
 */
package za.co.sindi.oauth.client.http;

import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * @author Buhake Sindi
 * @since 02 February 2024
 */
public interface BodyContent {

	public String getContentType();
	public long getContentLength();
	public ByteBuffer getContent();
	public byte[] getContentBytes();
	public InputStream getContentInputStream();
}
