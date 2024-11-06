/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public enum PKCEParameters {
	CODE_CHALLENGE("code_challenge")
	,CODE_CHALLENGE_METHOD("code_challenge_method")
	,CODE_VERIFIER("code_verifier")
	;
	private final String name;

	/**
	 * @param name
	 */
	private PKCEParameters(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
