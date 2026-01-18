package com.qa.reports.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class OptionsManager {
	
	 private Properties prop;

	 private ChromeOptions co;
	 private EdgeOptions eo;
	 
	 public OptionsManager(Properties prop) {
		 this.prop=prop;
	 }
	 public ChromeOptions getChromeOptions() { 
		 co=new ChromeOptions();
		 
		// ===== Disable Chrome Password & Security Popups =====
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);

			co.setExperimentalOption("prefs", prefs);

			// Disable automation info bar & notifications
			co.addArguments("--disable-notifications");
			co.addArguments("--disable-infobars");
			co.addArguments("--disable-save-password-bubble");
			co.addArguments("--disable-features=PasswordLeakDetection");
			co.addArguments("--disable-features=AutofillServerCommunication");
		 
		 
		 
		 if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			 System.out.println("======RUNNING ON HEADLESS MODE=====");
			 co.addArguments("--headless");
		 }
		 if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			   //Log.info("Running chrome in incognito mode");
			   co.addArguments("--incognito");
			  }
		 return co;

	 }
	 public EdgeOptions getEdgeOptions() { 
		 eo=new EdgeOptions();
		 if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			 System.out.println("======RUNNING ON HEADLESS MODE=====");
			 eo.addArguments("--headless");
		 }
		 if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			   //Log.info("Running chrome in incognito mode");
			   eo.addArguments("--inprivate");
			  }
		 return eo;

	 }

	
	
}