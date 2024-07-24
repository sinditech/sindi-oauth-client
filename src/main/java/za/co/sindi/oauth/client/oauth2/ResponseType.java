/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

/**
 * @author Buhake Sindi
 * @since 29 January 2024
 */
public enum ResponseType {
	CODE("code")
	,TOKEN("token")
	;
	private final String responseType;

	/**
	 * @param responseType
	 */
	private ResponseType(final String responseType) {
		this.responseType = responseType;
	}
	
	public static ResponseType of(final String responseType) {
		for (ResponseType type : values()) {
			if (type.responseType.equals(responseType)) return type;
		}
		
		throw new IllegalArgumentException("Invalid response_type '" + responseType + "'.");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return responseType;
	}
}
