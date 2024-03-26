/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public enum OAuth2Parameters {
	CLIENT_ID("client_id")
	,CLIENT_SECRET("client_secret")
	,RESPONSE_TYPE("response_type")
	,SCOPE("scope")
	,STATE("state")
	,REDIRECT_URI("redirect_uri")
	,GRANT_TYPE("grant_type")
	,CODE("code")
	,ACCESS_TOKEN("access_token")
	,TOKEN_TYPE("token_type")
	,EXPIRES_IN("expires_in")
	,USERNAME("username")
	,PASSWORD("password")
	,REFRESH_TOKEN("refresh_token")
	;
	private final String name;

	/**
	 * @param name
	 */
	private OAuth2Parameters(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
