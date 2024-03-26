/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import za.co.sindi.oauth.client.http.AbstractBodyContent;

/**
 * @author Buhake Sindi
 * @since 02 February 2024
 */
public class ByteArrayBodyContent extends AbstractBodyContent {
	
	private final byte[] content;

	/**
	 * @param contentType
	 * @param content
	 */
	public ByteArrayBodyContent(String contentType, byte[] content) {
		super(contentType);
		this.content = Objects.requireNonNull(content, "A byte array content is required.");
	}

	@Override
	public long getContentLength() {
		// TODO Auto-generated method stub
		return getContentBytes().length;
	}

	@Override
	public ByteBuffer getContent() {
		// TODO Auto-generated method stub
		return ByteBuffer.wrap(getContentBytes()); //.asReadOnlyBuffer();
	}

	@Override
	public byte[] getContentBytes() {
		// TODO Auto-generated method stub
		return content;
	}

	@Override
	public InputStream getContentInputStream() {
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(getContentBytes());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new String(content, StandardCharsets.UTF_8);
	}
}
