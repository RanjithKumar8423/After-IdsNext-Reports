package com.qa.reports.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qa.reports.utils.ElementUtil;

public class HomePage {

	WebDriver driver;
	ElementUtil ele;
	
	By reportele=By.xpath("//div[@title='FX Reports (FX Reports)']/..//img[@alt='FX Reports']");
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtil(driver);
	}
	
	public String homePageTiltle() {
		String title = ele.waitForTitleContains(10, "Home");
		return title;
	}
	
	public ReportPage reportClick() {
	

		ele.doclick(reportele, 20);
		return new ReportPage(driver);
		
	}
//	public String reportTiltle() {
//		String reportTitle = ele.waitForTitleContains(10, "ids");
//		System.out.println("=====REPORT TITLE====>"+reportTitle);
//		return reportTitle;
//	}
	
	
}
