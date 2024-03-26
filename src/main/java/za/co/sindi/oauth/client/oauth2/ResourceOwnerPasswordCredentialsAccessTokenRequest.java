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
public class ResourceOwnerPasswordCredentialsAccessTokenRequest extends AccessTokenRequest {
	
	private Builder builder;
	
	/**
	 * @param builder
	 */
	private ResourceOwnerPasswordCredentialsAccessTokenRequest(final Builder builder) {
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
	 * @return the userName
	 */
	public String getUserName() {
		return builder.userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return builder.password;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return builder.scope;
	}
	
	public static class Builder extends AccessTokenRequest.Builder<ResourceOwnerPasswordCredentialsAccessTokenRequest> {
		
		private String userName;
		private String password;
		private String scope;

		/**
		 * @param uri
		 * @param userName
		 * @param password
		 */
		public Builder(final String uri, final String userName, final String password) {
			super(uri, GrantType.PASSWORD);
			this.userName = Objects.requireNonNull(userName, "'username' is REQUIRED.");;
			this.password = Objects.requireNonNull(password, "'password' is REQUIRED.");;
		}

		/**
		 * @param scope the scope to set
		 */
		public Builder setScope(String scope) {
			this.scope = scope;
			return this;
		}

		@Override
		public ResourceOwnerPasswordCredentialsAccessTokenRequest build() {
			// TODO Auto-generated method stub
			return new ResourceOwnerPasswordCredentialsAccessTokenRequest(this);
		}
	}
}
