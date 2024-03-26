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
public class RefreshAccessTokenRequest extends AccessTokenRequest {
	
	private Builder builder;
	
	/**
	 * @param builder
	 */
	private RefreshAccessTokenRequest(final Builder builder) {
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
	
	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return builder.refreshToken;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return builder.scope;
	}
	
	public static class Builder extends AccessTokenRequest.Builder<RefreshAccessTokenRequest> {
		
		private String refreshToken;
		private String scope;

		/**
		 * @param uri
		 * @param refreshToken
		 */
		public Builder(final String uri, final String refreshToken) {
			super(uri, GrantType.REFRESH_TOKEN);
			this.refreshToken = Objects.requireNonNull(refreshToken, "'refresh_token' is REQUIRED.");
		}

		/**
		 * @param scope the scope to set
		 */
		public Builder setScope(String scope) {
			this.scope = scope;
			return this;
		}

		@Override
		public RefreshAccessTokenRequest build() {
			// TODO Auto-generated method stub
			return new RefreshAccessTokenRequest(this);
		}
	}
}
