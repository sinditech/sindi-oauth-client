/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.io.Serializable;

import jakarta.json.bind.annotation.JsonbProperty;

/**
 * @author Buhake Sindi
 * @since 29 January 2024
 */
public class AccessTokenResponse implements Serializable {

	@JsonbProperty("access_token")
	private String accessToken;
	
	@JsonbProperty("token_type")
	private String tokenType;
	
	@JsonbProperty("expires_in")
	private int expiresIn;
	
	@JsonbProperty("refresh_token")
	private String refreshToken;
	
	@JsonbProperty("scope")
	private String scope;
	
	/**
	 * 
	 */
	public AccessTokenResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param accessToken
	 * @param tokenType
	 */
	public AccessTokenResponse(String accessToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @return the expiresIn
	 */
	public int getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
}
