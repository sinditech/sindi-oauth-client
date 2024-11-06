/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import za.co.sindi.oauth.client.AsyncClient;
import za.co.sindi.oauth.client.Client;
import za.co.sindi.oauth.client.JSONTransformer;
import za.co.sindi.oauth.client.http.HttpClient;

/**
 * @author Buhake Sindi
 * @since 03 February 2024
 */
public interface OAuth2RequestClient<REQ extends TokenRequest, RES> extends Client<REQ, RES>, AsyncClient<REQ, RES> {

	public void setClientAuthentication(final ClientAuthentication clientAuthentication);
	public void setHttpClient(final HttpClient httpClient);
	public void setJSONTransformer(final JSONTransformer transformer);
}
