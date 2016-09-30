package com.pathway.codesearch.testcase;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pathway.codesearch.constant.DFConstants;
import com.pathway.codesearch.data.CodeSearchData;
import com.pathway.codesearch.selenium.factory.SeleniumBuilder;
import com.pathway.codesearch.selenium.factory.SeleniumFirefoxFactory;
import com.thoughtworks.selenium.SeleniumException;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CodeSearchTest {

	private static WebDriver webDriver;
	private static String masterWindow;
	private static WebDriverWait wait;// = new WebDriverWait(webDriver, 3);
	private boolean isFeatureEnd = false;
	private List<CodeSearchData> searchDataList =  new ArrayList<CodeSearchData>();

	/**
	 * Create Selenium instance here
	 */

	@Before
	public void initSelenium() throws Exception {
		// Selenium calling test.salesforce.com
		if (webDriver == null) {
			webDriver = SeleniumBuilder.build(new SeleniumFirefoxFactory());
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			webDriver.get(DFConstants.SF_SANDBOX);
			masterWindow = webDriver.getWindowHandle();
			wait = new WebDriverWait(webDriver, 30);
		}
	}

	@Given("^username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void username_and_password(String username, String password) throws Throwable {
		webDriver.findElement(By.id("username")).clear();
		webDriver.findElement(By.id("username")).sendKeys(username);
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("password")).sendKeys(password);
		webDriver.findElement(By.id("Login")).click();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Then("^select \"([^\"]*)\" from left navigation$")
	public void select(String navName) throws Throwable {
		webDriver.findElement(By.cssSelector("a[title=\"" + navName + " (New Window)\"]")).click();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		for (String activeHandle : webDriver.getWindowHandles()) {
	        if (!activeHandle.equals(masterWindow)) {
	        	webDriver.switchTo().window(activeHandle);
	        }
	    }
		wait.until(ExpectedConditions.titleContains(navName));
	}

	@Then("^\"([^\"]*)\" should open in new Window$")
	public void should_open_in_new_Window(String navName) throws Throwable {
		assertEquals(webDriver.getTitle(),navName);
	}
	
	@Given("^search text \"([^\"]*)\"$")
	public void search_text(String searchText) throws Throwable {
		
		// XPATH : html/body/div[4]/div[2]/section/div[2]/div[1]/div/div/div/input
		//ID : 
		
		/**
		   webDriver.findElement(By.id("52:2;a")).clear();
		   webDriver.findElement(By.id("52:2;a")).sendKeys("DM 2");
		**/  

		webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[2]/div[1]/div/div/div/input")).clear();
		webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[2]/div[1]/div/div/div/input")).sendKeys(searchText);
		webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[4]/div[2]/section/div[3]/div[1]/table/thead/tr/th[1]/span[1]/img")));
	}
	
	@Then("^expected ICD codes are \"([^\"]*)\" and \"([^\"]*)\" in search results\\.$")
	public void expected_ICD_codes_are_and_in_search_results(String code1, String code2) throws Throwable {
		assertThat(webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[3]/div[1]/table/tbody/tr[1]/td[1]/div/span[2]/span")).getText(),containsString(code1));
		assertThat(webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[3]/div[1]/table/tbody/tr[2]/td[1]/div/span[2]/span")).getText(),containsString(code2));
		//isFeatureEnd=true;
	}
	
	@Given("^Code Search input data:$")
	public void code_Search_input_data(DataTable dataTable) throws Throwable {
		searchDataList = dataTable.asList(CodeSearchData.class);
	}
	

	@Then("^verify all combinations of searchInput$")
	public void verify_all_combinations_of_searchInput() throws Throwable {
		for(CodeSearchData codeSearchData:searchDataList){
			webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[2]/div[1]/div/div/div/input")).clear();
			webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[2]/div[1]/div/div/div/input")).sendKeys(codeSearchData.getInputText());
			webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[2]/div[1]/div/div/div/input")).sendKeys(Keys.ENTER);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/div[4]/div[2]/section/div[3]/div[1]/table/thead/tr/th[1]/span[1]/img")));
		
			for(int i=0;i<codeSearchData.getIcdList().size();i++){
				assertThat(webDriver.findElement(By.xpath("//html/body/div[4]/div[2]/section/div[3]/div[1]/table/tbody/tr["+(i+1)+"]/td[1]/div/span[2]/span")).getText(),containsString(codeSearchData.getIcdList().get(i)));
			}
		}
		Thread.sleep(1000*20);
		isFeatureEnd=true;
	}
	
	@After
	public void tearDown() throws Exception {
			
		if(isFeatureEnd){
			try{
				webDriver.switchTo().window(masterWindow);
				webDriver.findElement(By.id("userNavLabel")).click();
				webDriver.findElement(By.linkText("Logout")).click();
				webDriver.quit();
			}catch (SeleniumException se){
				
			}
		}
	}
}
