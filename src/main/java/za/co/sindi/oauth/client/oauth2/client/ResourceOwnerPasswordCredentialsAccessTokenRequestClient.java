/**
 * 
 */
package za.co.sindi.oauth.client.oauth2.client;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import za.co.sindi.commons.utils.Strings;
import za.co.sindi.oauth.client.http.HttpMethod;
import za.co.sindi.oauth.client.http.HttpRequest;
import za.co.sindi.oauth.client.http.HttpResponse;
import za.co.sindi.oauth.client.http.impl.FormURLEncodedBodyContent;
import za.co.sindi.oauth.client.http.impl.HttpRequestImpl;
import za.co.sindi.oauth.client.oauth2.AbstractOAuth2TokenRequestClient;
import za.co.sindi.oauth.client.oauth2.AccessTokenResponse;
import za.co.sindi.oauth.client.oauth2.OAuth2Parameters;
import za.co.sindi.oauth.client.oauth2.ResourceOwnerPasswordCredentialsAccessTokenRequest;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public class ResourceOwnerPasswordCredentialsAccessTokenRequestClient extends AbstractOAuth2TokenRequestClient<ResourceOwnerPasswordCredentialsAccessTokenRequest, AccessTokenResponse> {

	@Override
	public HttpRequest createHttpRequest(ResourceOwnerPasswordCredentialsAccessTokenRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> queryParameterMap = new LinkedHashMap<>();
		queryParameterMap.put(OAuth2Parameters.GRANT_TYPE.toString(), request.getGrantType().toString());
		queryParameterMap.put(OAuth2Parameters.USERNAME.toString(), request.getUserName().toString());
		queryParameterMap.put(OAuth2Parameters.PASSWORD.toString(), request.getPassword().toString());
		if (!Strings.isNullOrEmpty(request.getScope())) {
			queryParameterMap.put(OAuth2Parameters.SCOPE.toString(), request.getScope().toString());
		}
		
		if (clientAuthentication.authenticateViaRequestBodyParameters()) {
			queryParameterMap.put(OAuth2Parameters.CLIENT_ID.toString(), clientAuthentication.getClientId());
			queryParameterMap.put(OAuth2Parameters.CLIENT_SECRET.toString(), clientAuthentication.getClientSecret());
		} else {
			httpClient.authenticate(clientAuthentication.getClientId(), clientAuthentication.getClientSecret());
		}
		
		try {
			HttpRequest httpRequest = new HttpRequestImpl(HttpMethod.POST, request.getURI().toURL());
			httpRequest.setBody(new FormURLEncodedBodyContent(queryParameterMap, StandardCharsets.UTF_8));
			return httpRequest;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new UncheckedIOException(e);
		}
	}

	@Override
	protected AccessTokenResponse handleHttpResponseInternally(HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		return jsonTransformer.transform(httpResponse.getBodyString(), AccessTokenResponse.class);
	}
}
