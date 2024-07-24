/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.io.Serializable;

import jakarta.json.bind.annotation.JsonbProperty;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 * @see <a href="https://datatracker.ietf.org/doc/html/rfc6749#section-5.2">Section 5.2 of RFC 6749</a>.
 */
public class OAuth2ErrorResponse implements Serializable {

	@JsonbProperty
	private Error error;
	
	@JsonbProperty("error_description")
	private String errorDescription;
	
	@JsonbProperty("error_uri")
	private String errorUri;
	
	@JsonbProperty
	private String state;

	/**
	 * 
	 */
	public OAuth2ErrorResponse() {
		super();
		//TODO Auto-generated constructor stub
	}

	/**
	 * @param error
	 */
	public OAuth2ErrorResponse(Error error) {
		super();
		this.error = error;
	}

	/**
	 * @return the error
	 */
	public Error getError() {
		return error;
	}
	
	/**
	 * @param error the error to set
	 */
	public void setError(Error error) {
		this.error = error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = Error.of(error);
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @param errorDescription the errorDescription to set
	 */
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	/**
	 * @return the errorUri
	 */
	public String getErrorUri() {
		return errorUri;
	}

	/**
	 * @param errorUri the errorUri to set
	 */
	public void setErrorUri(String errorUri) {
		this.errorUri = errorUri;
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
