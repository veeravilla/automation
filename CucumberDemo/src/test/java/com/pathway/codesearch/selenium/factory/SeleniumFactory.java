package com.pathway.codesearch.selenium.factory;

import org.openqa.selenium.WebDriver;

/**
 * Selenium Factory Interface
 *
 */
public interface SeleniumFactory {
	/**
	 * @return Selenium
	 */
	public WebDriver getWebDriver();
}
