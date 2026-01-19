package com.qa.reports.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qa.reports.utils.ElementUtil;

import io.qameta.allure.internal.shadowed.jackson.databind.annotation.JsonAppend.Prop;

public class ReportPage {

	WebDriver driver;
	ElementUtil ele;
	
	private By mousingEle=By.xpath("//div[@id='mySidenav']/aside");
	private By reportClick=By.xpath("//label[text()='Reports']");
	private By fxClick=By.xpath("//span[text()='Front Office Reports']");
	private By selectReport=By.xpath("//span[text()='Consolidated Arrival Report']");
	private By generateReport=By.xpath("//button[text()='Generate Report']");
	private By tscid=By.xpath("//i[@class='fa fa-chevron-down']");
	private By logout=By.xpath("//a[contains(text(),'Logout')]");
	private By backBtn=By.xpath("//button[text()='Back']");

	
	public ReportPage(WebDriver driver) {
		this.driver=driver;
		ele=new ElementUtil(driver);
		
	}
	
	public String ReportPageTiltle() {
		System.out.println(driver.getTitle());
		String title = ele.waitForTitleContains(10, "eport");
		return title;
	}
	
	public void reportSelect() {
		try {
			//ele.handleMenuSubMenuLevel2(mousingEle, fxClick, reportClick);
			ele.handleMenuSubMenuLevel4(mousingEle, reportClick, fxClick, selectReport, generateReport);
			ele.waitForElementVisible(backBtn, 10);
			ele.doclick(tscid, 5);
			ele.doclick(logout, 5);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
