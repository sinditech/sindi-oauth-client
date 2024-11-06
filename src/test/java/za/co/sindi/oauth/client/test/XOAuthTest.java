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
		za.co.sindi.oauth.client.oauth2.AuthorizationRequest.Builder builder = new za.co.sindi.oauth.client.oauth2.AuthorizationRequest.Builder("https://twitter.com/i/oauth2/authorize", ResponseType.CODE, "YOUR CLIENT ID HERE")
				.setRedirectUri("https://www.oauth.com/playground/authorization-code.html")
				.setScope("tweet.read%20users.read%20follows.read%20follows.write")
				.setState("PqGzynaZ42SVZwYU")
				.setPkce(pcke);
		AuthorizationRequest authorizationRequest = builder.build();
		AuthorizationCodeAuthorizationRequestUrl authorizationUrl = new AuthorizationCodeAuthorizationRequestUrl();
		String url = authorizationUrl.generateUrl(authorizationRequest);
		System.out.println(url);
		
		AuthorizationResponse response = authorizationUrl.createResponse("https://www.oauth.com/playground/authorization-code.html?state=PqGzynaZ42SVZwYU&code=eTc2NDIyMGtuOU9wam8xMmhfTElpbGlKbVV2WWl2OWY4aC02TnViV051VmlsOjE3Mjk3NDE4MDc3ODU6MToxOmFjOjE");
		System.out.println(response.getCode());
		System.out.println(response.getState());
		
		za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest.Builder builder2 = new za.co.sindi.oauth.client.oauth2.AuthorizationCodeAccessTokenRequest.Builder("https://api.x.com/2/oauth2/token", response.getCode(), "https://www.oauth.com/playground/authorization-code.html")
				.setState("PqGzynaZ42SVZwYU")
				.setPkce(pcke);
		AuthorizationCodeAccessTokenRequest accessTokenRequest = builder2.build();
		AuthorizationCodeAccessTokenRequestClient accessTokenRequestClient = new AuthorizationCodeAccessTokenRequestClient();
		accessTokenRequestClient.setClientAuthentication(new BasicClientAuthentication("YOUR CLIENT KEY HERE", "YOUR CLIENT SECRET HERE"));
		AccessTokenResponse accessToken = accessTokenRequestClient.send(accessTokenRequest);
		System.out.println(accessToken.getAccessToken());
	}
}
