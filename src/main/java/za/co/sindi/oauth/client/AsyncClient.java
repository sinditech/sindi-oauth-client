/**
 * 
 */
package za.co.sindi.oauth.client;

import java.util.concurrent.CompletableFuture;

/**
 * @author Buhake Sindi
 * @since 30 January 2024
 */
public interface AsyncClient<REQ, RES> {

	public CompletableFuture<RES> sendAsync(final REQ request);
}
