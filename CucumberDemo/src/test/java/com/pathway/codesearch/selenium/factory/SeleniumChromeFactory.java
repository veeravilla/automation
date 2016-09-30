package com.pathway.codesearch.selenium.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumChromeFactory implements SeleniumFactory {

	@Override
	public WebDriver getWebDriver() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

}
