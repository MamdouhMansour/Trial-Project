package api.transactions.tests;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.tools.io.JSONFileManager;
import com.shaft.validation.Assertions;

import api.transactions.API_Transaction;
import io.restassured.response.Response;

public class TransactionTest {
	private JSONFileManager testDataJSON;

//	Integration tests

	@Test
	public void testSuccessTransaction() throws IOException, ParseException {

		Response response = new API_Transaction().sendTransaction(testDataJSON.getTestData("CHANNEL"),
				new API_Transaction().returnJsonObsject("sample_transaction.json"));

		Assertions.assertEquals(RestActions.getResponseJSONValue(response, "status"), "ok");
	}

//	Unit tests for the payload sent, these are sample and we can write test for each property in the payload

	@Test
	public void testChannelIsNotEmpty() {
		Assertions.assertTrue(!(testDataJSON.getTestData("CHANNEL").isEmpty()));
	}

	@Test
	public void testMerchantCategoryCodeIsNotEmpty() {
		Assertions.assertTrue(!(testDataJSON.getTestData("PAYLOAD.merchant_category_code").isEmpty()),
				"merchant_category_code Shouldn't be null");
	}

	@Test
	public void testTransactionIdIsNotEmpty() {
		Assertions.assertTrue(!(testDataJSON.getTestData("PAYLOAD.transaction_id").isEmpty()),
				"transaction_id Shouldn't be null");
	}

	@Test
	public void testTypeIsNotEmpty() {
		Assertions.assertTrue(!(testDataJSON.getTestData("PAYLOAD.type").isEmpty()), "Type Shouldn't be null");
	}

	@BeforeClass
	public void beforeClass() {
		testDataJSON = new JSONFileManager(System.getProperty("testDataFolderPath") + "sample_transaction.json");

	}

}
