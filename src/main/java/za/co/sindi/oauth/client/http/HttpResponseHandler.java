/**
 * 
 */
package za.co.sindi.oauth.client.http;

/**
 * @author Buhake Sindi
 * @since 30 January 2024
 */
@FunctionalInterface
public interface HttpResponseHandler<T> {

	public T handleResponse(final HttpResponse response);
}
