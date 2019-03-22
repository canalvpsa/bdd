package classesAuxiliaresJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import classesAuxiliaresGeral.Resposta;

public class API {
	String jsonPost, jsonPut;
	Resposta resposta = new Resposta();
	ObjectMapper mapper = new ObjectMapper();
	JsonFormatar formatar  = new JsonFormatar();
	
	
	public String efetuarPost(Object postFinal, String URL, String token, String API){
		try {
			jsonPost = mapper.writeValueAsString(postFinal);
			resposta.post(formatar.formatarJson(jsonPost), URL, token, API);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resposta.idRecurso;
	}
	
	
	public void efetuarPut(String URL, String token, String idRecurso, String API, Object putFinal){
		try {
			jsonPut = mapper.writeValueAsString(putFinal);
			resposta.put(jsonPut, URL, token, idRecurso, API);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
