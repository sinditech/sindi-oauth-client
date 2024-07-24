/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.impl;

import java.util.Map;

import za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient;
import za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class AuthorizationCodeAccessTokenRequestClient extends AccessTokenRequestClient<AuthorizationCodeAccessTokenRequest> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient#handleHttpRequest(za.co.sindi.oauth.client.oauth2.AccessTokenRequest, java.util.Map)
	 */
	@Override
	protected void handleHttpRequest(AuthorizationCodeAccessTokenRequest request, Map<String, Object> queryParameterMap) {
		// TODO Auto-generated method stub
		queryParameterMap.put(OAuth2Parameters.CODE.toString(), request.getCode());
		queryParameterMap.put(OAuth2Parameters.REDIRECT_URI.toString(), request.getRedirectUri());
	}
}
