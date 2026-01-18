package com.qa.reports.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.reports.utils.ElementUtil;



public class LoginPage {

	WebDriver driver;
	ElementUtil ele;
	
	private By userName=By.xpath("//input[@type='text']");
	private By password=By.xpath("//input[@type='password']");
	private By loginBtn=By.xpath("//button[@type='button']");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	public String getLoginPageTitle() {
		String title=ele.waitForTitleContains(3, "Login");
		System.out.println("These is Login Page Title:  ==>"+title);
		return title;
	}
	public String getLoginPageurl() {
		String url = ele.waitForURLIs(0, "idsnext.live");
		return url;
	}
	
	
	public HomePage doLogin(String userid, String pwd) {
		ele.doSendKey(userName, userid, 10);
		ele.doSendKey(password, pwd);
		ele.doclick(loginBtn);
		return new HomePage(driver);
	}

}
