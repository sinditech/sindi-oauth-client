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
public class AuthorizationResponse implements Serializable {

	@JsonbProperty
	private String code;
	
	@JsonbProperty
	private String state;
	
	/**
	 * 
	 */
	public AuthorizationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 */
	public AuthorizationResponse(String code) {
		super();
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
}
