package com.pathway.codesearch.sflogin;
 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.pathway.codesearch.constant.DFConstants;
import com.pathway.codesearch.selenium.factory.SeleniumBuilder;
import com.pathway.codesearch.selenium.factory.SeleniumFirefoxFactory;


public class SFLoginUtil {
		
	private static WebDriver webDriver;
	
	public static void userLogin(String username, String password){
		webDriver.findElement(By.id("username")).clear();
		webDriver.findElement(By.id("username")).sendKeys(username);
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("password")).sendKeys(password);
		webDriver.findElement(By.id("Login")).click();
		webDriver.findElement(By.cssSelector("a[title=\"Code Search (New Window)\"]")).click();
	}
	
	public static WebDriver getWebDriver(){
		WebDriver webDriver = SeleniumBuilder.build(new SeleniumFirefoxFactory());
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.get(DFConstants.SF_SANDBOX);
		return webDriver;
	}
		
}