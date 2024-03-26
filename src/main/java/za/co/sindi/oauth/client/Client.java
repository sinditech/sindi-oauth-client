/**
 * 
 */
package za.co.sindi.oauth.client;

/**
 * @author Buhake Sindi
 * @since 30 January 2024
 */
public interface Client<REQ, RES> {

	public RES send(final REQ request);
}
