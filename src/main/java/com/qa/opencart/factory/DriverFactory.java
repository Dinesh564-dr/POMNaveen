package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsmanager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	// Thread local is having 2 methods in it 1.get();2.set().

	/**
	 * used to intialize the driver on the basis of given browser name
	 * 
	 * @param prop
	 * @return this method returns driver
	 */
	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("browser name is     " + browserName);
		optionsmanager = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			// driver = new ChromeDriver(optionsmanager.getChromeOptions());
		} else if (browserName.equalsIgnoreCase("safari")) {
			tlDriver.set(new SafariDriver());
			// driver = new SafariDriver();
		} else {
			System.out.println("please pass thr correct browser  " + browserName);
		}

		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * 
	 * @return properties reference with all config properties
	 */
	public Properties initprop() {
		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
