package za.co.sindi.oauth.client.oauth2;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Implementation of the <a href="https://datatracker.ietf.org/doc/html/rfc7636">Proof Key for Code Exchange by OAuth Public Clients</a> specification.
 * 
 * @author Buhake Sindi
 * @since 24 October 2024
 */
public class PKCE implements Serializable {

	private final String verifier;
	private String challenge;
	private String challengeMethod;
	
	/**
	 * 
	 */
	public PKCE() {
		this(generateVerifier());
		//TODO Auto-generated constructor stub
	}

	/**
	 * @param verifier
	 */
	public PKCE(String verifier) {
		super();
		this.verifier = verifier;
		generateChallenge(verifier);
	}

	/**
	 * @return the verifier
	 */
	public String getVerifier() {
		return verifier;
	}

	/**
	 * @return the challenge
	 */
	public String getChallenge() {
		return challenge;
	}

	/**
	 * @return the challengeMethod
	 */
	public String getChallengeMethod() {
		return challengeMethod;
	}

	private static String generateVerifier() {
		SecureRandom sr = new SecureRandom();
		byte[] code = new byte[32];
		sr.nextBytes(code);
		return new String(Base64.getUrlEncoder().withoutPadding().encode(code));
	}

	/**
	 * Create the PKCE code verifier. It uses the S256 method but falls back to
	 * using the 'plain'
	 * method in the unlikely case that the SHA-256 MessageDigest algorithm
	 * implementation can't be
	 * loaded.
	 */
	private void generateChallenge(String verifier) {
		try {
			byte[] bytes = verifier.getBytes();
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes, 0, bytes.length);
			byte[] digest = md.digest();
			challenge = new String(Base64.getUrlEncoder().withoutPadding().encode(digest));
			challengeMethod = "S256";
		} catch (NoSuchAlgorithmException e) {
			challenge = verifier;
			challengeMethod = "plain";
		}
	}
}
