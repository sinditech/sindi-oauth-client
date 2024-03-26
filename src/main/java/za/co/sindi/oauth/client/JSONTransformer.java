/**
 * 
 */
package za.co.sindi.oauth.client;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public interface JSONTransformer {

	public String transform(final Object object);
	public <T> T transform(final String data, final Class<T> objectType);
}
