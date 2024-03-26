/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.net.URI;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class ClientCredentialsAccessTokenRequest extends AccessTokenRequest {
	
	private Builder builder;
	
	/**
	 * @param builder
	 */
	private ClientCredentialsAccessTokenRequest(final Builder builder) {
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
	 * @return the scope
	 */
	public String getScope() {
		return builder.scope;
	}
	
	public static class Builder extends AccessTokenRequest.Builder<ClientCredentialsAccessTokenRequest> {
		
		private String scope;

		/**
		 * @param uri
		 */
		public Builder(final String uri) {
			super(uri, GrantType.CLIENT_CREDENTIALS);
		}

		/**
		 * @param scope the scope to set
		 */
		public Builder setScope(String scope) {
			this.scope = scope;
			return this;
		}

		@Override
		public ClientCredentialsAccessTokenRequest build() {
			// TODO Auto-generated method stub
			return new ClientCredentialsAccessTokenRequest(this);
		}
	}
}
