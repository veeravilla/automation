package com.pathway.codesearch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;


public class DFUtil{
	
	public static Selenium initSelenium(){
		Selenium selenium = null ;// = SeleniumBuilder.build(new SeleniumFactory_Impl_Remote_Windows_Grid(BSCConstants.SF_SANDBOX));
		//Selenium selenium = SeleniumBuilder.build(new SeleniumFactory_Impl_Local_Firefox(BSCConstants.SF_SANDBOX));
		return selenium;
	}
	
	public static String todaysDateString(){
		DateFormat df = new SimpleDateFormat("M/d/yyyy");
		return df.format(new Date());
	}
	
	
	public static void errorOutput(String message){
		System.err.println("****** " + message + "************************************************************************************");
	}

	//Increments the application name by a value (usually 1) to help find application clone records
	//Name is in the format: APP-##########
	public static String incApplicationName(String name, int value){
		String [] strings = name.split("-");
		int tempString = Integer.parseInt(strings[1]);
		tempString++;
		name = String.format("%010d", tempString);
		name = strings[0] + "-" + name;

		//System.err.println("****** " + name);
		return name;
	}
}