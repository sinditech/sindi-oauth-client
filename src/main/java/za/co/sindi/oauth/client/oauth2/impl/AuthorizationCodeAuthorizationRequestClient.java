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
import za.co.sindi.oauth.client.oauth2.AuthorizationResponse;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class AuthorizationCodeAuthorizationRequestClient extends AuthorizationRequestClient<AuthorizationResponse> {

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AbstractOAuth2RequestClient#createResponse(za.co.sindi.oauth.client.http.HttpResponse)
	 */
	@Override
	protected AuthorizationResponse createResponse(HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		try {
			URIBuilder uriBuilder = new URIBuilder(httpResponse.getRequest().getURL().toURI());
			Map<String, String> fragments = splitFragment(uriBuilder.getFragment());
			AuthorizationResponse response = new AuthorizationResponse(fragments.get(OAuth2Parameters.CODE.toString()));
			if (fragments.containsKey(OAuth2Parameters.STATE.toString())) {
				response.setState(fragments.get(OAuth2Parameters.STATE.toString()));
			}
			return response;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new UncheckedException(e);
		}
	}
}
