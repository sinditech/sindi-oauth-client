/**
 * 
 */
package za.co.sindi.oauth.client.http.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import za.co.sindi.oauth.client.http.Header;

/**
 * 
 */
public class HttpHeader implements Header {
	
	private final String name;
	private String value;
	private List<String> values;
	
	/**
	 * @param name
	 * @param values
	 */
	public HttpHeader(final String name, final String value) {
		super();
		this.name = Objects.requireNonNull(name, "'name' is required.");
		this.value = Objects.requireNonNull(value, "'value' is required.");
	}
	
	/**
	 * @param name
	 * @param values
	 */
	public HttpHeader(final String name, final String... values) {
		super();
		this.name = Objects.requireNonNull(name, "'name' is required.");
		this.values =  Arrays.asList(Objects.requireNonNull(values, "'values' is required."));
	}
	
	/**
	 * @param name
	 * @param values
	 */
	public HttpHeader(final String name, final List<String> values) {
		super();
		this.name = Objects.requireNonNull(name, "'name' is required.");
		this.values = Objects.requireNonNull(values, "'values' is required.");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String[] getValues() {
		// TODO Auto-generated method stub
		if (value != null) return new String[] { value };
		return values.toArray(new String[values.size()]);
	}

	@Override
	public List<String> getValueList() {
		// TODO Auto-generated method stub
		if (value != null) return Collections.singletonList(value);
		return Collections.unmodifiableList(values);
	}
}
