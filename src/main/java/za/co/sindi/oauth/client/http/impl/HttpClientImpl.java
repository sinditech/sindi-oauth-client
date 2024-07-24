/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import za.co.sindi.commons.io.UncheckedException;
import za.co.sindi.commons.net.http.BasicAuthenticator;
import za.co.sindi.commons.utils.Strings;
import za.co.sindi.oauth.client.http.HttpClient;
import za.co.sindi.oauth.client.http.HttpHeaderName;
import za.co.sindi.oauth.client.http.HttpMethod;
import za.co.sindi.oauth.client.http.HttpRequest;
import za.co.sindi.oauth.client.http.HttpResponse;

/**
 * @author Buhake Sindi
 * @since 01 February 2024
 */
public class HttpClientImpl implements HttpClient {
	
	private static final Pattern DOUBLE_QUOTES_PATTERN = Pattern.compile("\"(.*?)\"");
	private java.net.http.HttpClient.Builder httpClientBuilder = java.net.http.HttpClient.newBuilder();

	@Override
	public HttpResponse send(HttpRequest request) {
		// TODO Auto-generated method stub
		try {
			java.net.http.HttpRequest jdkHttpRequest = toJdkHttpRequest(request);
			java.net.http.HttpClient jdkHttpClient = httpClientBuilder.followRedirects(Redirect.ALWAYS).build();
			return toInternalHttpResponse(jdkHttpClient.send(jdkHttpRequest, BodyHandlers.ofString()));
		} catch (URISyntaxException | InterruptedException e) {
			throw new UncheckedException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new UncheckedIOException(e);
		}
	}

	@Override
	public void authenticate(String userName, String password) {
		// TODO Auto-generated method stub
		httpClientBuilder.authenticator(new BasicAuthenticator(Objects.requireNonNull(userName, "Username is required."), Objects.requireNonNull(password, "Password is required.")));
	}

	@Override
	public CompletableFuture<HttpResponse> sendAsync(HttpRequest request) {
		// TODO Auto-generated method stub
		try {
			java.net.http.HttpRequest jdkHttpRequest = toJdkHttpRequest(request);
			java.net.http.HttpClient jdkHttpClient = httpClientBuilder.followRedirects(Redirect.ALWAYS).build();
			return jdkHttpClient.sendAsync(jdkHttpRequest, BodyHandlers.ofString()).thenApplyAsync(httpResponse -> toInternalHttpResponse(httpResponse)).toCompletableFuture();
		} catch (URISyntaxException e) {
			throw new UncheckedException(e);
		}
	}
	
	private java.net.http.HttpRequest toJdkHttpRequest(final HttpRequest request) throws URISyntaxException {
		java.net.http.HttpRequest.Builder httpRequestBuilder = java.net.http.HttpRequest.newBuilder(request.getURL().toURI());
		
		//Put Header
		for(Entry<String, List<String>> headers: request.getHeaders().getHeaders().entrySet()) {
			String headerName = headers.getKey();
			if (headerName.equalsIgnoreCase(HttpHeaderName.CONTENT_LENGTH.toString())) continue; //This is restricted header...
			List<String> values = headers.getValue();
			if (values.size() == 1) httpRequestBuilder.setHeader(headerName, values.get(0));
			else values.stream().forEach(value -> httpRequestBuilder.header(headerName, value));
		}
		
		//Put Body
		httpRequestBuilder.method(request.getMethod().toString(), request.getContentLength() == 0 ? BodyPublishers.noBody() : BodyPublishers.ofByteArray(request.getBody().array(), 0, (int)request.getContentLength()));
		return httpRequestBuilder.build();
	}
	
	private HttpRequest toInternalHttpRequest(final java.net.http.HttpRequest httpRequest) {
		
		//Get headers
		HttpHeadersImpl requestHeaders = new HttpHeadersImpl();
		for(Entry<String, List<String>> headers: httpRequest.headers().map().entrySet()) {
			String headerName = headers.getKey();
			List<String> values = headers.getValue();
			if (values.size() == 1) requestHeaders.setHeader(headerName, values.get(0));
			else values.stream().forEach(value -> requestHeaders.addHeader(headerName, value));
		}
		
		try {
			return new HttpRequestImpl(HttpMethod.valueOf(httpRequest.method()), httpRequest.uri().toURL(), requestHeaders);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new UncheckedException(e);
		}
	}
	
	private HttpResponse toInternalHttpResponse(final java.net.http.HttpResponse<String> httpResponse) {
		
		//Get headers
		HttpHeadersImpl responseHeaders = new HttpHeadersImpl();
		for(Entry<String, List<String>> headers: httpResponse.headers().map().entrySet()) {
			String headerName = headers.getKey();
			List<String> values = headers.getValue();
			if (values.size() == 1) responseHeaders.setHeader(headerName, values.get(0));
			else values.stream().forEach(value -> responseHeaders.addHeader(headerName, value));
		}
		
		String contentType = httpResponse.headers().firstValue(HttpHeaderName.CONTENT_TYPE.toString()).orElse(null);
		Charset charset = StandardCharsets.UTF_8;
		if (!Strings.isNullOrEmpty(contentType)) {
			String split[] = contentType.split(";");
			if (split.length == 2) {
				contentType = split[0];
				String charsetName = split[1].split("=")[1];
				Matcher matcher = DOUBLE_QUOTES_PATTERN.matcher(charsetName);
				while (matcher.find()) {
					charsetName = matcher.group(1);
				}
				
				charset = Charset.forName(charsetName);
			}
		}
		
		return new HttpResponseImpl(httpResponse.statusCode(), toInternalHttpRequest(httpResponse.request()), responseHeaders, new StringBodyContent(contentType, httpResponse.body(), charset));
	}
}
