/**
 * 
 */
package za.co.sindi.oauth.client;

import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import za.co.sindi.oauth.client.jsonb.ErrorJsonbAdapter;

/**
 * 
 */
public class JSONTransformerImpl implements JSONTransformer {

	private JsonbConfig newJsonbConfig() {
		JsonbConfig config = new JsonbConfig();
		config.withAdapters(new ErrorJsonbAdapter());
		return config;
	}
	
	@Override
	public String transform(Object object) {
		// TODO Auto-generated method stub
		return JsonbBuilder.create(newJsonbConfig()).toJson(object);
	}

	@Override
	public <T> T transform(String data, Class<T> objectType) {
		// TODO Auto-generated method stub
		return JsonbBuilder.create(newJsonbConfig()).fromJson(data, objectType);
	}
}
