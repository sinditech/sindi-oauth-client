/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.impl;

import java.util.Map;

import za.co.sindi.oauth.client.oauth2.AccessTokenResponse;
import za.co.sindi.oauth.client.oauth2.AuthorizationRequestClient;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class AuthorizationTokenAuthorizationRequestClient extends AuthorizationRequestClient<AccessTokenResponse> {

	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AuthorizationRequestClient#createResponse(java.util.Map)
	 */
	@Override
	protected AccessTokenResponse createResponse(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		AccessTokenResponse response = new AccessTokenResponse(parameters.get(OAuth2Parameters.ACCESS_TOKEN.toString()), parameters.get(OAuth2Parameters.TOKEN_TYPE.toString()));
		if (parameters.containsKey(OAuth2Parameters.EXPIRES_IN.toString())) {
			response.setExpiresIn(Integer.valueOf(parameters.get(OAuth2Parameters.EXPIRES_IN.toString())));
		}
		
		if (parameters.containsKey(OAuth2Parameters.SCOPE.toString())) {
			response.setScope(parameters.get(OAuth2Parameters.SCOPE.toString()));
		}
		
		if (parameters.containsKey(OAuth2Parameters.STATE.toString())) {
			response.setState(parameters.get(OAuth2Parameters.STATE.toString()));
		}
		
		return response;
	}
}
