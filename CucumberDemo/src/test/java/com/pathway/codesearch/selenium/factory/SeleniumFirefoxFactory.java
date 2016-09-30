package com.pathway.codesearch.selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumFirefoxFactory implements SeleniumFactory {

	@Override
	public WebDriver getWebDriver() {
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
}
