/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import za.co.sindi.commons.utils.Preconditions;
import za.co.sindi.oauth.client.JSONTransformer;
import za.co.sindi.oauth.client.JSONTransformerImpl;
import za.co.sindi.oauth.client.http.HttpClient;
import za.co.sindi.oauth.client.http.HttpMethod;
import za.co.sindi.oauth.client.http.HttpRequest;
import za.co.sindi.oauth.client.http.HttpResponse;
import za.co.sindi.oauth.client.http.impl.FormURLEncodedBodyContent;
import za.co.sindi.oauth.client.http.impl.HttpRequestImpl;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public abstract class AccessTokenRequestClient<REQ extends AccessTokenRequest> extends AbstractOAuth2RequestClient<REQ, AccessTokenResponse> {
	
	protected ClientAuthentication clientAuthentication;
	protected JSONTransformer jsonTransformer;
	
	/**
	 */
	protected AccessTokenRequestClient() {
		this(null, new JSONTransformerImpl());
	}
	
	/**
	 * @param clientAuthentication
	 * @param jsonTransformer
	 */
	protected AccessTokenRequestClient(ClientAuthentication clientAuthentication, JSONTransformer jsonTransformer) {
		super();
		this.clientAuthentication = clientAuthentication;
		this.jsonTransformer = jsonTransformer;
	}
	
	/**
	 * @param httpClient
	 * @param clientAuthentication
	 * @param jsonTransformer
	 */
	protected AccessTokenRequestClient(HttpClient httpClient, ClientAuthentication clientAuthentication, JSONTransformer jsonTransformer) {
		super(httpClient);
		this.clientAuthentication = clientAuthentication;
		this.jsonTransformer = jsonTransformer;
	}

	/**
	 * @param clientAuthentication the clientAuthentication to set
	 */
	public void setClientAuthentication(ClientAuthentication clientAuthentication) {
		this.clientAuthentication = clientAuthentication;
	}

	/**
	 * @param jsonTransformer the jsonTransformer to set
	 */
	public void setJsonTransformer(JSONTransformer jsonTransformer) {
		this.jsonTransformer = jsonTransformer;
	}

	@Override
	public HttpRequest createHttpRequest(REQ request) {
		// TODO Auto-generated method stub
		Preconditions.checkState(clientAuthentication != null, "Client Authentication is not set, and is required.");
		Preconditions.checkState(jsonTransformer != null, "JSON Transformer is not set, and is required.");
		
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
	
	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AbstractOAuth2RequestClient#handleHttpResponse(za.co.sindi.oauth.client.http.HttpResponse)
	 */
	@Override
	protected AccessTokenResponse handleHttpResponse(HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		int code = httpResponse.getStatusCode() / 100;
		if (code == 4 || code == 5) {
			OAuth2ErrorResponse errorResponse = jsonTransformer.transform(httpResponse.getBodyString(), OAuth2ErrorResponse.class);
			throw new OAuth2ClientException(httpResponse.getStatusCode(), errorResponse);
		}
		
		return jsonTransformer.transform(httpResponse.getBodyString(), AccessTokenResponse.class);
	}
	
	protected abstract void handleHttpRequest(final REQ request, final Map<String, Object> queryParameterMap);
}
