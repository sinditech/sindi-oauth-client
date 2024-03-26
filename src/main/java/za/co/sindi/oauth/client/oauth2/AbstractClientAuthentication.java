/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public abstract class AbstractClientAuthentication implements ClientAuthentication {

	private String clientId;
	private String clientSecret;
	
	/**
	 * @param clientId
	 * @param clientSecret
	 */
	protected AbstractClientAuthentication(String clientId, String clientSecret) {
		super();
		this.clientId = Objects.requireNonNull(clientId, "'client_id' is REQUIRED.");
		this.clientSecret = Optional.ofNullable(clientSecret).orElse("");
	}

	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return clientId;
	}

	@Override
	public String getClientSecret() {
		// TODO Auto-generated method stub
		return clientSecret;
	}
}
