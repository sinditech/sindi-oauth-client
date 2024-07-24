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
public abstract class AccessTokenRequest extends TokenRequest {

	public abstract URI getURI();
	public abstract GrantType getGrantType();
	
	public static abstract class Builder<T extends AccessTokenRequest> extends TokenRequest.Builder<T> {
		
//		protected URI uri;
		protected GrantType grantType;
		
		/**
		 * @param uri
		 * @param grantType
		 */
		protected Builder(String uri, GrantType grantType) {
			super(uri);
//			this.uri = URI.create(Objects.requireNonNull(uri, "OAuth 2 request URI is required."));
			this.grantType = Objects.requireNonNull(grantType, "'grant_type' is required.");
		}

//		public abstract T build();
	}
}
