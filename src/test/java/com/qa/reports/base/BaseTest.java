package com.qa.reports.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.reports.factory.DriverFactory;
import com.qa.reports.pages.HomePage;
import com.qa.reports.pages.LoginPage;
import com.qa.reports.pages.ReportPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected LoginPage loginpage;
	protected Properties prop;
	protected HomePage homepage;
	protected ReportPage reportpage;
	
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup (String browserName) {
		df=new DriverFactory();
		prop=df.intiprop();
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		driver=df.initDriver(prop);
		loginpage=new LoginPage(driver);
	}
		
	@AfterTest
	 public void tearDown() {
	  driver.quit();
	 }

	
}