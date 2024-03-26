/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

/**
 * @author Buhake Sindi
 * @since 29 January 2024
 */
public enum GrantType {
	AUTHORIZATION_CODE("authorization_code")
	,PASSWORD("password")
	,CLIENT_CREDENTIALS("client_credentials")
	,REFRESH_TOKEN("refresh_token")
	;
	private final String grantType;

	/**
	 * @param grantType
	 */
	private GrantType(final String grantType) {
		this.grantType = grantType;
	}
	
	public static GrantType of(final String grantType) {
		for (GrantType type : values()) {
			if (type.grantType.equals(grantType)) return type;
		}
		
		throw new IllegalArgumentException("Invalid grant_type '" + grantType + "'.");
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return grantType;
	}
}
