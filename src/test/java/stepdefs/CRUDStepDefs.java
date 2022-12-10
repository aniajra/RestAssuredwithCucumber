package stepdefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CRUDStepDefs {

	Response response;
	RequestSpecification request;
	Map<String, Object> mapObj;
	public static String emplId;

	@Given("The base URI is {string}")
	public void the_base_URI_is(String uri) {
		// Write code here that turns the phrase above into concrete actions

		request = RestAssured.given().baseUri(uri);

	}

	@When("I perform the Get Operation")
	public void i_perform_the_Get_Operation() {
		// Write code here that turns the phrase above into concrete actions
		response = request.get();
		RestAssured.given().log().uri();
	}

	@Then("Response code should be {int}")
	public void response_code_should_be(int expResponseCode) {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(expResponseCode, response.statusCode());
	}

	@When("I pass the name {string} and salary {string}")
	public void i_pass_the_name_and_salary(String name, String salary) {
		// Write code here that turns the phrase above into concrete actions
		mapObj = new HashMap<String, Object>();
		mapObj.put("name", name);
		mapObj.put("salary", salary);
	}

	@Then("I perform Post operation")
	public void i_perform_Post_operation() {

		response = request.contentType(ContentType.JSON).accept(ContentType.JSON).body(mapObj).post("/create");

		//Fetch the created Id and assign to static variable
		getCreatedEmpId();

	}

	private void getCreatedEmpId() {
		JsonPath jpath = response.jsonPath();

		System.out.println(response.getBody().asString());

		String id = jpath.get("id").toString();

		emplId = id;

		System.out.println("Employee with Id : " + emplId + "created Successfully");
	}

	@When("I delete the created employee")
	public void i_delete_the_employee_with_Id() {

		// this will delete the created employee
		response = request.delete(emplId);

	}

}
