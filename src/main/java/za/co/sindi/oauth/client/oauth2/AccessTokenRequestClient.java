/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import za.co.sindi.oauth.client.http.HttpMethod;
import za.co.sindi.oauth.client.http.HttpRequest;
import za.co.sindi.oauth.client.http.HttpResponse;
import za.co.sindi.oauth.client.http.impl.FormURLEncodedBodyContent;
import za.co.sindi.oauth.client.http.impl.HttpRequestImpl;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public abstract class AccessTokenRequestClient<REQ extends AccessTokenRequest> extends AbstractOAuth2TokenRequestClient<REQ, AccessTokenResponse> {
	
	@Override
	public HttpRequest createHttpRequest(REQ request) {
		// TODO Auto-generated method stub
		Map<String, Object> queryParameterMap = new LinkedHashMap<>();
		queryParameterMap.put(OAuth2Parameters.GRANT_TYPE.toString(), request.getGrantType().toString());
		handleHttpRequest(request, queryParameterMap);
		
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
	
	protected abstract void handleHttpRequest(final REQ request, final Map<String, Object> queryParameterMap);
}
