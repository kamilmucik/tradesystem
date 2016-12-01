package com.gft.services.cucumber;

import com.gft.dto.model.Order;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @see 'https://github.com/cucumber/cucumber/wiki/Given-When-Then'
 * Created by kamu on 2016-09-16.
 */
public class StepDefs extends AbstractDefs {

    private static final Logger LOG = LoggerFactory.getLogger(StepDefs.class);

    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable {
        executeGet("http://localhost:8090/order/version");
    }

    @When("^user logs in using Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
    public void login(String username,String password) throws Throwable {
        executeGet("http://localhost:8090/user/login?login="+username+"&password="+password);
    }

    @Then("^the api receives status code of (\\d+)$")
    public void the_api_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : "+ latestResponse.getBody(), currentStatusCode.value(), is(statusCode) );
    }

    @Then("^login should be successful$")
    public void validate_login_success() {
        LOG.debug("Executing 2<sup>nd</sup> Then statement");
    }

    @And("^the client receives server response body '(.+)'$")
    public void the_client_receives_server_response_body(String body) throws Throwable {
        LOG.debug("body is:"+ body);
        assertThat(latestResponse.getBody(), is(body)) ;
    }

    @And("^the client receives server response param '(.+)' body '(.+)'$")
    public void the_client_receives_server_response_param_body(String param, String value) throws Throwable {
        LOG.debug("body is:"+ latestResponse.getBody());
        JSONObject jsonObj = new JSONObject(latestResponse.getBody());
        assertThat(jsonObj.getString(param), is(value)) ;
    }

    @And("^the client receives server response param '(.+)' body '(.+)': double")
    public void the_client_receives_server_response_param_body(String param, Double value) throws Throwable {
        JSONObject jsonObj = new JSONObject(latestResponse.getBody());
        assertThat(jsonObj.get(param), is(value)) ;
    }

    @And("^the client receives server response param '(.+)' body '(.+)': integer")
    public void the_client_receives_server_response_param_body(String param, Integer value) throws Throwable {
        JSONObject jsonObj = new JSONObject(latestResponse.getBody());
        assertThat(jsonObj.get(param), is(value)) ;
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        assertThat("status code is incorrect : "+ latestResponse.getBody(), currentStatusCode.value(), is(statusCode) );
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        assertThat(latestResponse.getBody(), is(version)) ;
    }

    @Given(".+list of assets '(.+)'")
    public void lift_of_assets(final String title) {
        LOG.debug("title: {}", title);
    }

    @Then("^get all product")
    public void get_all_product() throws Throwable {
        executeGet("http://localhost:8090/product/findall");
    }

    @Then("^list of all product$")
    public void list_of_all_product() {
        JSONObject jsonObj = new JSONObject(latestResponse.getBody());
        Object object = jsonObj.get("content");

        if (object instanceof Map) {
            JSONObject json = new JSONObject();
            Map map = (Map) object;
            for (Object key : map.keySet()) {
                LOG.debug("{} : {}", key.toString(), map.get(key));
            }
        } else if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable)object)) {
                JSONObject jobj = (JSONObject)value;
                LOG.debug("{}\t{}\t{}\t{}", jobj.get("id"), jobj.get("name"), jobj.get("price"), jobj.get("rate"));
            }
        } else {
            LOG.debug("{}", object);
        }
    }

    @Then("^get user detail '(.+)'")
    public void get_user_detail(String username) throws Throwable {
        executeGet("http://localhost:8090/user/details?login="+username);
    }

    @Then("^get request url '(.+)'")
    public void get_request_url(String url) throws Throwable {
        executeGet("http://localhost:8090" + url);
    }

    @When("^the client select product by param productId='(.+)'$")
    public void the_client_select_product_by_param_productId(Long productId) throws Throwable {
        executeGet("http://localhost:8090/product/details?id="+productId);
    }

    @Then("^the client prepare order request by params userId='(.+)', productId='(.+)', type'(.+)', price'(.+)', volume'(.+)'$")
    public void the_client_prepare_order_request_by_params(Long userId, Long productId, String type, Double price, Long volume) throws Throwable {
        executePost("http://localhost:8090/asset/create?userId="+userId+"&productId="+productId+"&type="+type+"&price="+price+"&volume="+volume);
    }

    @Then("^get all active assets")
    public void get_all_active_assets() throws Throwable {
        executeGet("http://localhost:8090/asset/active-orders");
    }

    @And("^the client receives server response body$")
    public void the_client_receives_server_response_body() throws Throwable {
        JSONObject jsonObj = new JSONObject(latestResponse.getBody());
        Object object = jsonObj.get("content");
        if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable)object)) {
                JSONObject jobj = (JSONObject)value;
                LOG.debug("{}\t{}\t{}\t{}\t{}\t{}", jobj.get("id"), jobj.get("name"), jobj.get("volumen"),jobj.get("price"), jobj.get("valuation"), jobj.get("type"));
            }
        } else {
            LOG.debug("body is: {}", latestResponse.getBody());
        }
    }

    @Then("^get all active user assets '(.+)'")
    public void get_all_active_user_assets(String username) throws Throwable {
        executeGet("http://localhost:8090/asset/user-held?login="+username);
    }
    @And("^the client receives all active user assets")
    public void the_client_receives_all_active_user_assets() throws Throwable {
        JSONObject jsonObj = new JSONObject(latestResponse.getBody());
        Object object = jsonObj.get("content");
        if (object instanceof Iterable) {
            JSONArray json = new JSONArray();
            for (Object value : ((Iterable)object)) {
                JSONObject jobj = (JSONObject)value;
                LOG.debug("{}\t{}\t{}\t{}\t{}", jobj.get("id"), jobj.get("name"), jobj.get("volume"),jobj.get("buyPrice"), jobj.get("buyValue"));
            }
        } else {
            LOG.debug("body is: {}", latestResponse.getBody());
        }
    }

}
