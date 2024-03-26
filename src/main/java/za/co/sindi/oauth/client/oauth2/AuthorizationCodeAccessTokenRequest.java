/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.net.URI;
import java.util.Objects;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class AuthorizationCodeAccessTokenRequest extends AccessTokenRequest {
	
	private Builder builder;
	
	/**
	 * @param builder
	 */
	private AuthorizationCodeAccessTokenRequest(final Builder builder) {
		super();
		this.builder = builder;
	}

	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return builder.uri;
	}

	@Override
	public GrantType getGrantType() {
		// TODO Auto-generated method stub
		return builder.grantType;
	}

	public String getCode() {
		return builder.code;
	}
	
	public String getRedirectUri() {
		return builder.redirectUri;
	}
	
//	public String getClientId() {
//		return builder.clientId;
//	}
	
	public static class Builder extends AccessTokenRequest.Builder<AuthorizationCodeAccessTokenRequest> {
		
		private String code;
		private String redirectUri;
//		private String clientId;

		/**
		 * @param uri
		 * @param code
		 * @param redirectUri
		 * @param clientId
		 */
		public Builder(final String uri, final String code, final String redirectUri /*, final String clientId*/) {
			super(uri, GrantType.AUTHORIZATION_CODE);
			this.code = Objects.requireNonNull(code, "'code' is REQUIRED.");
			this.redirectUri = Objects.requireNonNull(redirectUri, "'redirect_uri' is REQUIRED.");
//			this.clientId = Objects.requireNonNull(clientId, "'client_id' is REQUIRED.");
		}

		@Override
		public AuthorizationCodeAccessTokenRequest build() {
			// TODO Auto-generated method stub
			return new AuthorizationCodeAccessTokenRequest(this);
		}
	}
}
