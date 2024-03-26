/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import za.co.sindi.oauth.client.http.AbstractBodyContent;

/**
 * @author Buhake Sindi
 * @since 02 February 2024
 */
public class StringBodyContent extends AbstractBodyContent {
	
	private final String content;
	private final Charset charset;
	private byte[] data;

	/**
	 * @param contentType
	 * @param content
	 * @param charset
	 */
	public StringBodyContent(String contentType, String content, Charset charset) {
		super(contentType);
		this.content = Objects.requireNonNull(content, "A String content is required.");
		this.charset = charset == null ? StandardCharsets.UTF_8 : charset;
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
		if (data == null) data = content.getBytes(charset);
		return data;
	}

	@Override
	public InputStream getContentInputStream() {
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(getContentBytes());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return content;
	}
}
