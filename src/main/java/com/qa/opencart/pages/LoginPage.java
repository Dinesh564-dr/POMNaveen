package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElemenrUtil;

public class LoginPage {
	private WebDriver driver;
	private ElemenrUtil eleutil;

	// 1.By Locators
	private By emailid = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotpwdLink = By.id("Forgotten Password");
	private By register = By.linkText("Register");

	// 2.page constants .....
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElemenrUtil(driver);
	}

	// 3 . page actions

	public String getLoginPageTitle() {
		String title = eleutil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("page title is " + title);
		return title;
	}

	public String getLoginPageUrl() {
		String url = eleutil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION);
		System.out.println("page url is " + url);
		return url;
	}

	public boolean isForgotPasswordLinkExists() {
		return eleutil.waitForElementPresence(forgotpwdLink, AppConstants.SMALL_DEFAULT_TIME_OUT).isDisplayed();
	}

	public AccountsPage doLogin(String email, String pwd) {
		System.out.println("username is  " + email + ":" + pwd);
		eleutil.doSendKeysWithWait(emailid, AppConstants.MEDIUM_DEFAULT_TIME_OUT, email);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		// return eleutil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE,
		// AppConstants.MEDIUM_DEFAULT_TIME_OUT);
		return new AccountsPage(driver);
	}

	public RegisterPage goToRegisterPage() {
		eleutil.doClick(register);
		return new RegisterPage(driver);
	}
}
