/**
 * 
 */
package za.co.sindi.oauth.client.http;

import java.util.List;
import java.util.Map;

/**
 * @author Buhake Sindi
 * @since 01 February 2024
 */
public interface HttpHeaders {

	public boolean containsHeader(final HttpHeaderName name);
	public boolean containsHeader(final String name);
//	public String getValue(final HttpHeaderName name);
//	public String getValue(final String name);
	public String[] getValues(final HttpHeaderName name);
	public String[] getValues(final String name);
	public List<String> getValuesList(final HttpHeaderName name);
	public List<String> getValuesList(final String name);
	public Map<String, List<String>> getHeaders();
}
