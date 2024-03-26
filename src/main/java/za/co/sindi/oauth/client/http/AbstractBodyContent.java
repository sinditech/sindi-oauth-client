/**
 * 
 */
package za.co.sindi.oauth.client.http;

import java.util.Objects;

/**
 * @author Buhake Sindi
 * @since 02 February 2024
 */
public abstract class AbstractBodyContent implements BodyContent {
	
	private String contentType;

	/**
	 * @param contentType
	 */
	protected AbstractBodyContent(String contentType) {
		super();
		this.contentType = Objects.requireNonNull(contentType, "A Content-Type is required.");
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return contentType;
	}
}
