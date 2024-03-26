/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public interface ClientAuthentication {

	public String getClientId();
	
	public String getClientSecret();
	
	public boolean authenticateViaRequestBodyParameters();
}
