/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.Map;

import za.co.sindi.commons.io.UncheckedException;
import za.co.sindi.commons.net.URIBuilder;
import za.co.sindi.commons.utils.Strings;
import za.co.sindi.oauth.client.http.HttpMethod;
import za.co.sindi.oauth.client.http.HttpRequest;
import za.co.sindi.oauth.client.http.HttpResponse;
import za.co.sindi.oauth.client.http.impl.HttpRequestImpl;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public abstract class AuthorizationRequestClient<RES> extends AbstractOAuth2RequestClient<AuthorizationRequest, RES> {
	
	@Override
	public HttpRequest createHttpRequest(AuthorizationRequest request) {
		// TODO Auto-generated method stub
		URIBuilder uriBuilder = new URIBuilder(request.getURI())
					.addQueryParameters(OAuth2Parameters.RESPONSE_TYPE.toString(), request.getResponseType().toString())
					.addQueryParameters(OAuth2Parameters.CLIENT_ID.toString(), request.getClientId());
		if (!Strings.isNullOrEmpty(request.getRedirectUri())) {
			uriBuilder.addQueryParameters(OAuth2Parameters.REDIRECT_URI.toString(), request.getRedirectUri());
		}
		
		if (!Strings.isNullOrEmpty(request.getScope())) {
			uriBuilder.addQueryParameters(OAuth2Parameters.SCOPE.toString(), request.getScope());
		}
		
		if (!Strings.isNullOrEmpty(request.getState())) {
			uriBuilder.addQueryParameters(OAuth2Parameters.STATE.toString(), request.getState());
		}
		
		try {
			return new HttpRequestImpl(HttpMethod.GET, uriBuilder.build().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new UncheckedIOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.oauth.client.oauth2.AbstractOAuth2RequestClient#handleHttpResponse(za.co.sindi.oauth.client.http.HttpResponse)
	 */
	@Override
	protected RES handleHttpResponse(HttpResponse httpResponse) {
		// TODO Auto-generated method stub
		int code = httpResponse.getStatusCode() / 100;
		if (code == 4 || code == 5) {
			OAuth2ErrorResponse errorResponse = getOAuth2ErrorResponse(httpResponse);
			throw new OAuth2ClientException(httpResponse.getStatusCode(), errorResponse);
		}
		
		return createResponse(httpResponse);
	}
	
	private OAuth2ErrorResponse getOAuth2ErrorResponse(HttpResponse httpResponse) {
		try {
			URIBuilder uriBuilder = new URIBuilder(httpResponse.getRequest().getURL().toURI());
			Map<String, String> fragments = splitFragment(uriBuilder.getFragment());
			OAuth2ErrorResponse errorResponse = new OAuth2ErrorResponse(Error.of(fragments.get(OAuth2Parameters.ERROR.toString())));
			
			if (fragments.containsKey(OAuth2Parameters.ERROR_DESCRIPTION.toString())) {
				errorResponse.setErrorDescription(fragments.get(OAuth2Parameters.ERROR_DESCRIPTION.toString()));
			}
			
			if (fragments.containsKey(OAuth2Parameters.ERROR_URI.toString())) {
				errorResponse.setErrorUri(fragments.get(OAuth2Parameters.ERROR_URI.toString()));
			}
			
			if (fragments.containsKey(OAuth2Parameters.STATE.toString())) {
				errorResponse.setState(fragments.get(OAuth2Parameters.STATE.toString()));
			}
			
			return errorResponse;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new UncheckedException(e);
		}
	}
	
	protected static Map<String, String> splitFragment(String fragment) {
	    Map<String, String> fragment_pairs = new LinkedHashMap<String, String>();
	    String[] pairs = fragment.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        fragment_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
	    }
	    return fragment_pairs;
	}
	
	protected abstract RES createResponse(HttpResponse httpResponse);
}
