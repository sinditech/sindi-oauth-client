/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import za.co.sindi.commons.utils.URLEncoderUtils;

/**
 * @author Buhake Sindi
 * @since 05 February 2024
 */
public class FormURLEncodedBodyContent extends StringBodyContent {
	
	/**
	 * @param content
	 */
	public FormURLEncodedBodyContent(Map<String, Object> content) {
		this(content, StandardCharsets.ISO_8859_1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param content
	 * @param charset
	 */
	public FormURLEncodedBodyContent(Map<String, Object> content, Charset charset) {
		super(URLEncoderUtils.CONTENT_TYPE, URLEncoderUtils.formatQueryParameters(content, '&', charset), charset != null ? charset : StandardCharsets.ISO_8859_1);
		// TODO Auto-generated constructor stub
	}
}
