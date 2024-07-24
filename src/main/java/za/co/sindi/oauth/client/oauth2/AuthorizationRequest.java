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
public class AuthorizationRequest extends TokenRequest {
	
	private Builder builder;
	
	/**
	 * @param builder
	 */
	private AuthorizationRequest(final Builder builder) {
		super();
		this.builder = builder;
	}
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.TokenRequest#getURI()
	 */
	@Override
	public URI getURI() {
		// TODO Auto-generated method stub
		return builder.uri;
	}

	public ResponseType getResponseType() {
		return builder.responseType;
	}
	
	public String getClientId() {
		return builder.clientId;
	}
	
	public String getRedirectUri() {
		return builder.redirectUri;
	}
	
	public String getScope() {
		return builder.scope;
	}
	
	public String getState() {
		return builder.state;
	}
	
	public static class Builder extends TokenRequest.Builder<AuthorizationRequest> {
		
//		protected URI uri;
		protected ResponseType responseType;
		protected String clientId;
		protected String redirectUri;
		protected String scope;
		protected String state;
		
		/**
		 * @param uri
		 * @param grantType
		 * @param clientId
		 */
		protected Builder(String uri, ResponseType responseType, String clientId) {
			super(uri);
//			this.uri = URI.create(Objects.requireNonNull(uri, "OAuth 2 request URI is required."));
			this.responseType = Objects.requireNonNull(responseType, "'response_type' is required.");
			this.clientId = Objects.requireNonNull(clientId, "'client_id' is required.");
		}

		/**
		 * @param redirectUri the redirectUri to set
		 */
		public Builder setRedirectUri(String redirectUri) {
			this.redirectUri = redirectUri;
			return this;
		}

		/**
		 * @param scope the scope to set
		 */
		public Builder setScope(String scope) {
			this.scope = scope;
			return this;
		}

		/**
		 * @param state the state to set
		 */
		public Builder setState(String state) {
			this.state = state;
			return this;
		}

		/* (non-Javadoc)
		 * @see za.co.sindi.oauth.client.oauth2.TokenRequest.Builder#build()
		 */
		
		@Override
		public AuthorizationRequest build() {
			// TODO Auto-generated method stub
			return new AuthorizationRequest(this);
		}
	}
}
