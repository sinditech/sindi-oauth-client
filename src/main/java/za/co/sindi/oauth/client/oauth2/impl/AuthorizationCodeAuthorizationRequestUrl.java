/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.impl;

import java.util.Map;

import za.co.sindi.oauth.client.oauth2.AuthorizationRequestUrl;
import za.co.sindi.oauth.client.oauth2.AuthorizationResponse;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class AuthorizationCodeAuthorizationRequestUrl extends AuthorizationRequestUrl<AuthorizationResponse> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AuthorizationRequestClient#createResponse(java.util.Map)
	 */
	@Override
	protected AuthorizationResponse createResponse(Map<String, String> parameters) {
		// TODO Auto-generated method stub
		AuthorizationResponse response = new AuthorizationResponse(parameters.get(OAuth2Parameters.CODE.toString()));
		if (parameters.containsKey(OAuth2Parameters.STATE.toString())) {
			response.setState(parameters.get(OAuth2Parameters.STATE.toString()));
		}
		
		return response;
	}
}
