package com.qa.reports.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.reports.base.BaseTest;



public class HomePageTest extends BaseTest {
	
	
	@BeforeClass
	 public void accSetup() {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@Test(priority = 1)
	 public void acctitleTest() {
	  String hometile = homepage.homePageTiltle();
	  Assert.assertEquals(hometile, "Home");
	 }
	@Test(priority = 2)
	public void reportClickTest() {
		
		homepage.reportClick();
		
	}
	



}
