/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.net.URI;
import java.util.Objects;

import za.co.sindi.oauth.client.oauth2.AuthorizationRequest.Builder;

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
	
	public String getState() {
		return builder.state;
	}
	
	public PKCE getPkce() {
		return builder.pkce;
	}
	
	public static class Builder extends AccessTokenRequest.Builder<AuthorizationCodeAccessTokenRequest> {
		
		private String code;
		private String redirectUri;
		private String state;
		protected PKCE pkce;

		/**
		 * @param uri
		 * @param code
		 * @param redirectUri
		 */
		public Builder(final String uri, final String code, final String redirectUri) {
			super(uri, GrantType.AUTHORIZATION_CODE);
			this.code = Objects.requireNonNull(code, "'code' is REQUIRED.");
			this.redirectUri = Objects.requireNonNull(redirectUri, "'redirect_uri' is REQUIRED.");
		}
		
		/**
		 * @param state the state to set
		 */
		public Builder setState(String state) {
			this.state = state;
			return this;
		}
		
		/**
		 * @param pkce the pkce to set
		 */
		public Builder setPkce(PKCE pkce) {
			this.pkce = pkce;
			return this;
		}

		@Override
		public AuthorizationCodeAccessTokenRequest build() {
			// TODO Auto-generated method stub
			return new AuthorizationCodeAccessTokenRequest(this);
		}
	}
}
