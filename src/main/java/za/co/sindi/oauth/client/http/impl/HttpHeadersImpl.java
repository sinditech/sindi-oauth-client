/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import za.co.sindi.oauth.client.http.HttpHeaderName;
import za.co.sindi.oauth.client.http.HttpHeaders;

/**
 * @author Buhake Sindi
 * @since 01 February 2024
 */
public class HttpHeadersImpl implements HttpHeaders {
	
	private Map<String, HttpHeader> headers;
	
	/**
	 * 
	 */
	public HttpHeadersImpl() {
		super();
		// TODO Auto-generated constructor stub
		headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	}
	
	public void addHeader(final HttpHeaderName name, final String value) {
		Objects.requireNonNull(name, "Header name is required.");
		addHeader(name.toString(), value);
	}
	
	public void addHeader(final String name, final String value) {
		String lowerCaseName = Objects.requireNonNull(name, "Header name is required.").trim().toLowerCase();
		if (!headers.containsKey(lowerCaseName)) {
//			headers.put(lowerCaseName, new HttpHeader(name, value));
			setHeader(name, value);
		} else {
			List<String> values = new ArrayList<>(headers.get(lowerCaseName).getValueList());
			//Add
			values.add(value.trim());
			headers.put(lowerCaseName, new HttpHeader(name, values));
		}
	}
	
	public void setHeader(final HttpHeaderName name, final String value) {
		Objects.requireNonNull(name, "Header name is required.");
		setHeader(name.toString(), value);
	}
	
	public void setHeader(final String name, final String value) {
		String lowerCaseName = Objects.requireNonNull(name, "Header name is required.").trim().toLowerCase();
		headers.put(lowerCaseName, new HttpHeader(name, value));
	}

	@Override
	public boolean containsHeader(HttpHeaderName name) {
		// TODO Auto-generated method stub
		return headers.containsKey(toLowerCase(name));
	}

	@Override
	public String[] getValues(HttpHeaderName name) {
		// TODO Auto-generated method stub
		if (!containsHeader(name)) return null;
		return headers.get(toLowerCase(name)).getValues();
	}

	@Override
	public String[] getValues(String name) {
		// TODO Auto-generated method stub
		if (!containsHeader(name)) return null;
		return headers.get(Objects.requireNonNull(name, "A HTTP header 'name' is required.").trim().toLowerCase()).getValues();
	}

	@Override
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return headers.containsKey(Objects.requireNonNull(name, "A HTTP header 'name' is required.").trim().toLowerCase());
	}

	@Override
	public List<String> getValuesList(HttpHeaderName name) {
		// TODO Auto-generated method stub
		if (!containsHeader(name)) return null;
		return headers.get(toLowerCase(name)).getValueList();
	}

	@Override
	public List<String> getValuesList(String name) {
		// TODO Auto-generated method stub
		return headers.get(Objects.requireNonNull(name, "A HTTP header 'name' is required.").trim().toLowerCase()).getValueList();
	}

	@Override
	public Map<String, List<String>> getHeaders() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableMap(headers.values().stream().collect(Collectors.toMap(e -> e.getName(), e -> e.getValueList())));
	}
	
	private String toLowerCase(final HttpHeaderName name) {
		return name.toString().toLowerCase();
	}
}
