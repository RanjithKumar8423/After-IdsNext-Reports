package com.qa.reports.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.reports.base.BaseTest;
import com.qa.reports.pages.HomePage;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
	public void titleTest() {
		String title = loginpage.getLoginPageTitle();
		Assert.assertEquals(title, "Login");
	}
	@Test(priority = 2)
	public void urlTest() {
		String url = loginpage.getLoginPageurl();
		Assert.assertTrue(url.contains("idsnext.live"));
	}
	
	
	@Test(priority = 3)
	public void dologin () {
		homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homepage.homePageTiltle(), "Home");
	}
}
