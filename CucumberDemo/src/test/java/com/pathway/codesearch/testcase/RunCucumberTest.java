package com.pathway.codesearch.testcase;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report", "json:target/cucumber-report.json",
		"junit:target/cucumber-junit.xml" }, 
		features = "src/test/resources/com/pathway/codesearch/features",
		strict = false,monochrome = true) //tags={"@hello"}
public class RunCucumberTest {
}
