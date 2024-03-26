/**
 * 
 */
package za.co.sindi.oauth.client.http;

import java.util.List;

/**
 * @author Buhake Sindi
 * @since 01 February 2024
 *
 */
public interface Header {

	public String getName();
	public String[] getValues();
	public List<String> getValueList();
}
