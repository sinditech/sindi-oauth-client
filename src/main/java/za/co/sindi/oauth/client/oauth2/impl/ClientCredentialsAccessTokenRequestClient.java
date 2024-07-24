/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.impl;

import java.util.Map;

import za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient;
import za.co.sindi.oauth.client.oauth2.ClientCredentialsAccessTokenRequest;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class ClientCredentialsAccessTokenRequestClient extends AccessTokenRequestClient<ClientCredentialsAccessTokenRequest> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient#handleHttpRequest(za.co.sindi.oauth.client.oauth2.AccessTokenRequest, java.util.Map)
	 */
	@Override
	protected void handleHttpRequest(ClientCredentialsAccessTokenRequest request, Map<String, Object> queryParameterMap) {
		// TODO Auto-generated method stub
		
	}
}
