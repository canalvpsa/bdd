package classesAuxiliaresJson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatar {
	String jsonProduto, prettyJson;

	public String formatarJson(String jsonFormatar) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			Object jsonObject = null;
			jsonObject = mapper.readValue(jsonFormatar, Object.class);
			prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prettyJson;
	}
}
