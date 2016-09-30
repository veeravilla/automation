package com.salesforce.codesearch;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;


public class CodeSearch {
	
	private WebDriver driver;
	private String baseUrl;

	@Given("^navigate to \"([^\"]*)\"$")
	public void navigate_to(String arg1) throws Throwable {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");
	}

	@Given("^username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void username_and_password(String username, String password) throws Throwable {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
	}

	@Then("^select \"([^\"]*)\"$")
	public void select(String arg1) throws Throwable {
		driver.findElement(By.id("Account_Tab")).click();
		driver.findElement(By.name("new")).click();
		driver.findElement(By.id("acc2")).clear();
		driver.findElement(By.id("acc2")).sendKeys("Temp User");
		driver.findElement(By.name("save")).click();
	}

	@Then("^response should contain \"([^\"]*)\"$")
	public void response_should_contain(String arg1) throws Throwable {
		if (!driver.getTitle().contains("Temp User"))
			throw new AssertionError();
			driver.close();
	}
}
