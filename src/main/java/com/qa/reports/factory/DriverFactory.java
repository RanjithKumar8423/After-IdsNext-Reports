package com.qa.reports.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.reports.expections.BrowserExpection;
import com.qa.reports.expections.ElementExpection;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tLDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser");

		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution:
				init_remoteDriver("chrome");
			}

			else {
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tLDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
				break;
			}

		case "edge":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution:
				init_remoteDriver("edge");
			} else {

				// driver = new EdgeDriver(optionsManager.getEdgeOptions());
				tLDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
				break;
			}

		default:
			System.out.println("=======PLS PASS RIGHT BROWSER========");
			throw new BrowserExpection("NO BROWSER FOUND__" + browserName);

		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	private void init_remoteDriver(String browserName) {

		System.out.println("Running tests on Remote GRID on browser: " + browserName);

		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":

				tLDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;

			case "edge":
				tLDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
				break;

			default:
				System.out.println("plz pass thr right supported browser on GRID....");
				break;
			}

		}

		catch (MalformedURLException e) {
			e.printStackTrace();
		}

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