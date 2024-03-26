/**
 * 
 */
package za.co.sindi.oauth.client.test;

import java.net.MalformedURLException;

import za.co.sindi.oauth.client.oauth2.AccessTokenResponse;
import za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest;
import za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest.Builder;
import za.co.sindi.oauth.client.oauth2.ClientParametersAuthentication;
import za.co.sindi.oauth.client.oauth2.client.AuthorizationCodeAccessTokenRequestClient;

/**
 * @author Buhake Sindi
 * @since 05 February 2024
 */
public class OAuthTest {
	
	static {
		System.setProperty("jdk.internal.httpclient.debug", "true");
		System.setProperty("jdk.internal.httpclient.websocket.debug", "true");
		System.setProperty("jdk.internal.httpclient.hpack.debug", "true");
		System.setProperty("jdk.httpclient.HttpClient.log", "errors,requests,headers,frames[:control:data:window:all],content,ssl,trace,channel,all");
	}
	
	public static void main(String[] args) throws MalformedURLException {
//		URI uri = URI.create("https://client.example.com/cb?code=%20SplxlOBeZQQYbYS6WxSbIA&state=xyz");
//		System.out.println(uri);
//		
//		HttpClient client = new HttpClientImpl();
//		HttpRequest request = new HttpRequestImpl(HttpMethod.GET, URI.create("https://www.facebook.com/v19.0/dialog/oauth?response_type=code&client_id=fdfgdfsgdfgdfg&state=xyz&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb").toURL());
//		request.setHeader(HttpHeaderName.ACCEPT, "application/json");
//		HttpResponse response = client.send(request);
//		System.out.println(response.getStatusCode());
		
		Builder builder = new Builder("https://oauth2.googleapis.com/token", "dfasdfasdfsadfsdf", "https://developers.google.com/oauthplayground/");
		AuthorizationCodeAccessTokenRequest accessTokenRequest = builder.build();
		AuthorizationCodeAccessTokenRequestClient oauthClient = new AuthorizationCodeAccessTokenRequestClient();
		oauthClient.setClientAuthentication(new ClientParametersAuthentication("sadfasfasdfadsfdsf.apps.googleusercontent.com", null));
		AccessTokenResponse accessTokenResponse = oauthClient.send(accessTokenRequest);
	}
}
