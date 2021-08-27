package api.transactions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

@SuppressWarnings("unchecked")
public class API_Transaction {

	private RestActions sessionObject;
	private String transactionApi = "https://devops-tutuka-trial.herokuapp.com/";

	public API_Transaction() {
		sessionObject = new RestActions(transactionApi);
	}

	public Response sendTransaction(String endPoint, JSONObject json) throws IOException, ParseException {

		JSONObject payloadJsonObject = json;

		return sessionObject.buildNewRequest(endPoint, RequestType.POST).setContentType(ContentType.JSON)
				.setTargetStatusCode(200).setRequestBody(payloadJsonObject).performRequest();
	}

	public JSONObject returnJsonObsject(String fileName) throws IOException, ParseException {
		String payload = new String(Files.readAllBytes(Paths.get(System.getProperty("testDataFolderPath") + fileName)));

		return (JSONObject) new JSONParser().parse(payload);
	}
}
