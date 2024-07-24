/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.impl;

import java.net.URISyntaxException;
import java.util.Map;

import za.co.sindi.commons.io.UncheckedException;
import za.co.sindi.commons.net.URIBuilder;
import za.co.sindi.oauth.client.http.HttpResponse;
import za.co.sindi.oauth.client.oauth2.AuthorizationRequestClient;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class AuthorizationTokenAuthorizationRequestClient extends AuthorizationRequestClient<AccessTokenResponse> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AbstractOAuth2RequestClient#createResponse(za.co.sindi.oauth.client.http.HttpResponse)
	 */
	@Override
	protected AccessTokenResponse createResponse(HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		try {
			URIBuilder uriBuilder = new URIBuilder(httpResponse.getRequest().getURL().toURI());
			Map<String, String> fragments = splitFragment(uriBuilder.getFragment());
			AccessTokenResponse response = new AccessTokenResponse(fragments.get(OAuth2Parameters.ACCESS_TOKEN.toString()), fragments.get(OAuth2Parameters.TOKEN_TYPE.toString()));
			if (fragments.containsKey(OAuth2Parameters.EXPIRES_IN.toString())) {
				response.setExpiresIn(Integer.valueOf(fragments.get(OAuth2Parameters.EXPIRES_IN.toString())));
			}
			
			if (fragments.containsKey(OAuth2Parameters.SCOPE.toString())) {
				response.setScope(fragments.get(OAuth2Parameters.SCOPE.toString()));
			}
			
			if (fragments.containsKey(OAuth2Parameters.STATE.toString())) {
				response.setState(fragments.get(OAuth2Parameters.STATE.toString()));
			}
			
			return response;
		} catch (NumberFormatException | URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new UncheckedException(e);
		}
	}
}
