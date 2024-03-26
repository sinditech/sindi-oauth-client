/**
 * 
 */
package za.co.sindi.oauth.client.jsonb;

import jakarta.json.bind.adapter.JsonbAdapter;
import za.co.sindi.oauth.client.oauth2.Error;

/**
 * @author Buhake Sindi
 * @since 05 February 2024
 */
public class ErrorJsonbAdapter implements JsonbAdapter<Error, String> {

	@Override
	public String adaptToJson(Error error) throws Exception {
		// TODO Auto-generated method stub
		return error.toString();
	}

	@Override
	public Error adaptFromJson(String data) throws Exception {
		// TODO Auto-generated method stub
		return Error.of(data);
	}
}
