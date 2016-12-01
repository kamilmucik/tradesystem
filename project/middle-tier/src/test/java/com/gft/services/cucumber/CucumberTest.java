package com.gft.services.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by kamu on 2016-09-16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        monochrome = false,
        format = {"pretty"}
)
public class CucumberTest  {

}
