/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.client;

import java.util.Map;

import za.co.sindi.commons.utils.Strings;
import za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;
import za.co.sindi.oauth.client.oauth2.ResourceOwnerPasswordCredentialsAccessTokenRequest;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class ResourceOwnerPasswordCredentialsAccessTokenRequestClient extends AccessTokenRequestClient<ResourceOwnerPasswordCredentialsAccessTokenRequest> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AccessTokenRequestClient#handleHttpRequest(za.co.sindi.oauth.client.oauth2.AccessTokenRequest, java.util.Map)
	 */
	@Override
	protected void handleHttpRequest(ResourceOwnerPasswordCredentialsAccessTokenRequest request, Map<String, Object> queryParameterMap) {
		// TODO Auto-generated method stub
		queryParameterMap.put(OAuth2Parameters.USERNAME.toString(), request.getUserName().toString());
		queryParameterMap.put(OAuth2Parameters.PASSWORD.toString(), request.getPassword().toString());
		if (!Strings.isNullOrEmpty(request.getScope())) {
			queryParameterMap.put(OAuth2Parameters.SCOPE.toString(), request.getScope().toString());
		}
	}
}
