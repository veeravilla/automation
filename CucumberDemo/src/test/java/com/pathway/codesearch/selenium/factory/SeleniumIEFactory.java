package com.pathway.codesearch.selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumIEFactory implements SeleniumFactory {

	@Override
	public WebDriver getWebDriver() {
		//IE Settings
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}
	
}
