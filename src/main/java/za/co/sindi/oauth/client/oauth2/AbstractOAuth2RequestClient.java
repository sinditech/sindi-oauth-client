/**
 * 
 */
package za.co.sindi.oauth.client.oauth2;

import java.util.concurrent.CompletableFuture;

import za.co.sindi.commons.utils.Preconditions;
import za.co.sindi.oauth.client.JSONTransformer;
import za.co.sindi.oauth.client.JSONTransformerImpl;
import za.co.sindi.oauth.client.http.HttpClient;
import za.co.sindi.oauth.client.http.HttpRequest;
import za.co.sindi.oauth.client.http.HttpResponse;
import za.co.sindi.oauth.client.http.impl.HttpClientImpl;

/**
 * @author Buhake Sindi
 * @since 04 February 2024
 */
public abstract class AbstractOAuth2RequestClient<REQ extends TokenRequest, RES> implements OAuth2RequestClient<REQ, RES> {
	
	protected ClientAuthentication clientAuthentication;
	protected HttpClient httpClient;
	protected JSONTransformer jsonTransformer;
	
	/**
	 * 
	 */
	protected AbstractOAuth2RequestClient() {
//		this(new HttpClientImpl(), new JSONTransformerImpl());
		this(new HttpClientImpl());
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param httpClient
	 */
	protected AbstractOAuth2RequestClient(HttpClient httpClient) {
		this(httpClient, new JSONTransformerImpl());
		this.httpClient = httpClient;
	}
	
	/**
	 * @param httpClient
	 * @param jsonTransformer
	 */
	protected AbstractOAuth2RequestClient(HttpClient httpClient, JSONTransformer jsonTransformer) {
		super();
		this.httpClient = httpClient;
		this.jsonTransformer = jsonTransformer;
	}
	
	@Override
	public void setClientAuthentication(ClientAuthentication clientAuthentication) {
		// TODO Auto-generated method stub
		this.clientAuthentication = clientAuthentication;
	}

	@Override
	public void setHttpClient(HttpClient httpClient) {
		// TODO Auto-generated method stub
		this.httpClient = httpClient;
	}

	@Override
	public void setJSONTransformer(JSONTransformer jsonTransformer) {
		// TODO Auto-generated method stub
		this.jsonTransformer = jsonTransformer;
	}

	@Override
	public RES send(REQ request) {
		// TODO Auto-generated method stub
		HttpRequest httpRequest = handleRequest(request);
		HttpResponse httpResponse = httpClient.send(httpRequest);
		return handleHttpResponse(httpResponse);
	}
	
	@Override
	public CompletableFuture<RES> sendAsync(REQ request) {
		// TODO Auto-generated method stub
		HttpRequest httpRequest = handleRequest(request);
		CompletableFuture<HttpResponse> httpResponseFuture = httpClient.sendAsync(httpRequest);
		return httpResponseFuture.thenApply(httpResponse -> handleHttpResponse(httpResponse)).toCompletableFuture();
	}
	
	private HttpRequest handleRequest(REQ request) {
		// TODO Auto-generated method stub
		Preconditions.checkArgument(request != null, "No OAuth2 client request is provided.");
		Preconditions.checkState(httpClient != null, "HTTP client is not set, and is required.");
		Preconditions.checkState(jsonTransformer != null, "JSON Transformer is not set, and is required.");
		
		return createHttpRequest(request);
	}

	private RES handleHttpResponse(HttpResponse httpResponse) {
		int code = httpResponse.getStatusCode() / 100;
		if (code == 4 || code == 5) {
			OAuth2ErrorResponse errorResponse = jsonTransformer.transform(httpResponse.getBodyString(), OAuth2ErrorResponse.class);
			throw new OAuth2ClientException(httpResponse.getStatusCode(), errorResponse);
		}
		
		return createResponse(httpResponse);
	}
	
	protected abstract HttpRequest createHttpRequest(REQ request);
	protected abstract RES createResponse(HttpResponse httpResponse);
}
