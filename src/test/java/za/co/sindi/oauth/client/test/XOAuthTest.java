package za.co.sindi.oauth.client.test;

import za.co.sindi.oauth.client.oauth2.AccessTokenResponse;
import za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest;
import za.co.sindi.oauth.client.oauth2.AuthorizationRequest;
import za.co.sindi.oauth.client.oauth2.AuthorizationResponse;
import za.co.sindi.oauth.client.oauth2.BasicClientAuthentication;
import za.co.sindi.oauth.client.oauth2.PKCE;
import za.co.sindi.oauth.client.oauth2.ResponseType;
import za.co.sindi.oauth.client.oauth2.impl.AuthorizationCodeAccessTokenRequestClient;
import za.co.sindi.oauth.client.oauth2.impl.AuthorizationCodeAuthorizationRequestUrl;

/**
 * @author Buhake Sindi
 * @since 24 October 2024
 */
public class XOAuthTest {
	
	static {
		System.setProperty("jdk.internal.httpclient.debug", "true");
		System.setProperty("jdk.internal.httpclient.websocket.debug", "true");
		System.setProperty("jdk.internal.httpclient.hpack.debug", "true");
		System.setProperty("jdk.httpclient.HttpClient.log", "errors,requests,headers,frames[:control:data:window:all],content,ssl,trace,channel,all");
	}

	public static void main(String[] args) {
		
		PKCE pcke = new PKCE();
		za.co.sindi.oauth.client.oauth2.AuthorizationRequest.Builder builder = new za.co.sindi.oauth.client.oauth2.AuthorizationRequest.Builder("https://twitter.com/i/oauth2/authorize", ResponseType.CODE, "a")
				.setRedirectUri("https://www.oauth.com/playground/authorization-code.html")
				.setScope("tweet.read%20users.read%20follows.read%20follows.write")
				.setState("b")
				.setPkce(pcke);
		AuthorizationRequest authorizationRequest = builder.build();
		AuthorizationCodeAuthorizationRequestUrl authorizationUrl = new AuthorizationCodeAuthorizationRequestUrl();
		String url = authorizationUrl.generateUrl(authorizationRequest);
		System.out.println(url);
		
		AuthorizationResponse response = authorizationUrl.createResponse("https://www.oauth.com/playground/authorization-code.html?state=b&code=c");
		System.out.println(response.getCode());
		System.out.println(response.getState());
		
		za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest.Builder builder2 = new za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest.Builder("https://api.x.com/2/oauth2/token", response.getCode(), "https://www.oauth.com/playground/authorization-code.html")
				.setState("b")
				.setPkce(pcke);
		AuthorizationCodeAccessTokenRequest accessTokenRequest = builder2.build();
		AuthorizationCodeAccessTokenRequestClient accessTokenRequestClient = new AuthorizationCodeAccessTokenRequestClient();
		accessTokenRequestClient.setClientAuthentication(new BasicClientAuthentication("d", "e"));
		AccessTokenResponse accessToken = accessTokenRequestClient.send(accessTokenRequest);
		System.out.println(accessToken.getAccessToken());
	}
}
