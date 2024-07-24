/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.impl;

import java.util.Map;

import za.co.sindi.commons.utils.Strings;
import za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;
import za.co.sindi.oauth.client.oauth2.RefreshAccessTokenRequest;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class RefreshAccessTokenRequestClient extends AccessTokenRequestClient<RefreshAccessTokenRequest> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient#handleHttpRequest(za.co.sindi.oauth.client.oauth2.AccessTokenRequest, java.util.Map)
	 */
	@Override
	protected void handleHttpRequest(RefreshAccessTokenRequest request, Map<String, Object> queryParameterMap) {
		// TODO Auto-generated method stub
		queryParameterMap.put(OAuth2Parameters.REFRESH_TOKEN.toString(), request.getRefreshToken());
		if (!Strings.isNullOrEmpty(request.getScope())) {
			queryParameterMap.put(OAuth2Parameters.SCOPE.toString(), request.getScope().toString());
		}
	}
}
