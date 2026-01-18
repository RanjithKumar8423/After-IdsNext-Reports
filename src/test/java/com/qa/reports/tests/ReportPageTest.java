package com.qa.reports.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.reports.base.BaseTest;

public class ReportPageTest extends BaseTest {

	@BeforeClass
	 public void accSetup() {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@Test(priority = 1)
	public void reportTabClick() {
		reportpage=homepage.reportClick();
	}
	@Test(priority = 2)
	public void sideReportSelect() {
		reportpage.reportSelect();
	}
	
	
	
}
