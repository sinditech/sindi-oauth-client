/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-5.2">Section 5.2 of RFC 6749</a>.
 */
public enum Error {
	ACCESS_DENIED("access_denied")
	,INVALID_REQUEST("invalid_request")
	,INVALID_CLIENT("invalid_client")
	,INVALID_GRANT("invalid_grant")
	,UNAUTHORIZED_CLIENT("unauthorized_client")
	,UNSUPPORTED_GRANT_TYPE("unsupported_grant_type")
	,UNSUPPORTED_RESPONSE_TYPE("unsupported_response_type")
	,INVALID_SCOPE("invalid_scope")
	,SERVER_ERROR("server_error")
	,TEMPORARILY_UNAVAILABLE("temporarily_unavailable")
	;
	private final String code;

	/**
	 * @param code
	 */
	private Error(String code) {
		this.code = code;
	}
	
	public static Error of(final String code) {
		for (Error error : values()) {
			if (error.code.equals(code)) return error;
		}
		
		throw new IllegalStateException("Unknown error code: " + code);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code;
	}
}
