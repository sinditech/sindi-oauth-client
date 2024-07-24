/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.net.URI;
import java.util.Objects;

/**
 * @author Buhake Sindi
 * @since 29 January 2024
 */
public abstract class TokenRequest {
	
	public abstract URI getURI();
	
	public static abstract class Builder<T extends TokenRequest> {
		
		protected URI uri;
		
		/**
		 * @param uri
		 */
		protected Builder(String uri) {
			super();
			this.uri = URI.create(Objects.requireNonNull(uri, "OAuth 2 request URI is required."));
		}

		public abstract T build();
	}
}
