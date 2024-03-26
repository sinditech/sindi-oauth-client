/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import za.co.sindi.commons.utils.Strings;

/**
 * @author Buhake Sindi
 * @since 05 February 2024
 */
public class OAuth2ClientException extends RuntimeException {

	private final int statusCode;
	private final OAuth2ErrorResponse errorResponse;

	/**
	 * @param errorResponse
	 */
	public OAuth2ClientException(final int statusCode, final OAuth2ErrorResponse errorResponse) {
		super(errorResponse.getError().toString() + ": " + (!Strings.isNullOrEmpty(errorResponse.getErrorDescription()) ? errorResponse.getErrorDescription() : errorResponse.getError().toString()));
		this.statusCode = statusCode;
		this.errorResponse = errorResponse;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @return the errorResponse
	 */
	public OAuth2ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}
