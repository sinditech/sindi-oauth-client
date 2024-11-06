package za.co.sindi.oauth.client.oauth2;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import za.co.sindi.commons.net.URIBuilder;
import za.co.sindi.commons.utils.Strings;

/**
 * @author Buhake Sindi
 * @since 24 October 2024
 */
public abstract class AuthorizationRequestUrl<RES> {

	public String generateUrl(final AuthorizationRequest request) {
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
		
		if (request.getPkce() != null) {
			uriBuilder.addQueryParameters(PKCEParameters.CODE_CHALLENGE.toString(), request.getPkce().getChallenge());
			uriBuilder.addQueryParameters(PKCEParameters.CODE_CHALLENGE_METHOD.toString(), request.getPkce().getChallengeMethod());
		}
		
		return uriBuilder.toString();
	}
	
	public RES createResponse(final String url) {
		URIBuilder uriBuilder = new URIBuilder(URI.create(url));
		String parameterString = Objects.requireNonNullElse(uriBuilder.getFragment(), uriBuilder.getQuery());
		Map<String, String> parameters = splitParameter(parameterString);
		if (parameters.containsKey(OAuth2Parameters.ERROR.toString())) {
			OAuth2ErrorResponse errorResponse = getOAuth2ErrorResponse(parameters);
			throw new OAuth2ClientException(400, errorResponse);
		}
		
		return createResponse(parameters);
	}
	
	private OAuth2ErrorResponse getOAuth2ErrorResponse(final Map<String, String> parameters) {
		OAuth2ErrorResponse errorResponse = new OAuth2ErrorResponse(Error.of(parameters.get(OAuth2Parameters.ERROR.toString())));
		
		if (parameters.containsKey(OAuth2Parameters.ERROR_DESCRIPTION.toString())) {
			errorResponse.setErrorDescription(parameters.get(OAuth2Parameters.ERROR_DESCRIPTION.toString()));
		}
		
		if (parameters.containsKey(OAuth2Parameters.ERROR_URI.toString())) {
			errorResponse.setErrorUri(parameters.get(OAuth2Parameters.ERROR_URI.toString()));
		}
		
		if (parameters.containsKey(OAuth2Parameters.STATE.toString())) {
			errorResponse.setState(parameters.get(OAuth2Parameters.STATE.toString()));
		}
		
		return errorResponse;
	}
	
	private static Map<String, String> splitParameter(String parameterString) {
	    Map<String, String> parameter_pairs = new LinkedHashMap<String, String>();
	    String[] pairs = parameterString.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        parameter_pairs.put(pair.substring(0, idx), pair.substring(idx + 1));
	    }
	    return parameter_pairs;
	}
	
	protected abstract RES createResponse(Map<String, String> parameters);
}
