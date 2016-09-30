package com.pathway.codesearch.selenium.factory;

import org.openqa.selenium.WebDriver;

/**
 * SeleniumBuilder
 */
public class SeleniumBuilder {
	public static WebDriver build(SeleniumFactory sf) {
		return sf.getWebDriver();
	}
}
