/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public class ClientParametersAuthentication extends AbstractClientAuthentication {

	/**
	 * @param clientId
	 * @param clientSecret
	 */
	public ClientParametersAuthentication(String clientId, String clientSecret) {
		super(clientId, clientSecret);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean authenticateViaRequestBodyParameters() {
		// TODO Auto-generated method stub
		return true;
	}
}
