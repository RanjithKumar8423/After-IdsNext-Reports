package com.qa.reports.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.qa.reports.expections.BrowserExpection;
import com.qa.reports.expections.ElementExpection;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tLDriver=new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tLDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tLDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		default:
			System.out.println("=======PLS PASS RIGHT BROWSER========");
			throw new BrowserExpection("NO BROWSER FOUND__" + browserName);

		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}
	public static WebDriver getDriver() {
		 return tLDriver.get();
		 }


	public Properties intiprop() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("=======Running tests on Env========:  " + envName);
		try {
			if (envName == null) {

				System.out.println("======NO ENV IS GIVEN,, HENCE RUNNING ON QA production==========");
				ip = new FileInputStream("./src/test/resourcesreports/config/config.qa.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa1":
					ip = new FileInputStream("./src/test/resourcesreports/config/config.qa1.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resourcesreports/config/config.stage.properties");
					break;
				case "qa":
					ip = new FileInputStream("./src/test/resourcesreports/config/config.qa.properties");
					break;

				default:
					System.out.println("======PLS RIGHT BROWSER=======" + envName);
					throw new ElementExpection("=====WRONG ENV=======" + envName);

				}
			}
		}

		catch (IOException e) {
			System.out.println("=========GIVEN PATH IS WRONG===============");
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			System.out.println("====GIVEN IP IS INVALID=====");
			e.printStackTrace();
		}

		return prop;
	}

}