/**
 * 
 */
package za.co.sindi.oauth.client.http;

import java.util.concurrent.CompletableFuture;

import za.co.sindi.oauth.client.AsyncClient;
import za.co.sindi.oauth.client.Client;

/**
 * @author Buhake Sindi
 * @since 30 January 2024
 */
public interface HttpClient extends Client<HttpRequest, HttpResponse>, AsyncClient<HttpRequest, HttpResponse> {

	public void authenticate(final String userName, final String password);
	public HttpResponse send(final HttpRequest request);
	public CompletableFuture<HttpResponse> sendAsync(final HttpRequest request);
}
