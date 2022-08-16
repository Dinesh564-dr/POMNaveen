package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElemenrUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElemenrUtil eleutil;

	// 1.By Locators
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By emailid = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpwd = By.id("input-confirm");
	private By agreeCheckBox = By.xpath("//input[@type='checkbox']");
	private By continueButton = By.xpath("//input[@value='Continue']");

	private By subscribeYes = By.xpath("(//label[@class=\"radio-inline\"])[1]");
	private By subscribeNo = By.xpath("(//label[@class=\"radio-inline\"])[2]");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	private By sucessMessg = By.xpath("");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElemenrUtil(driver);
	}

	public boolean userRegistration(String fn, String ln, String email, String phno, String pwd,
			String subscribe) {
		eleutil.waitForElementVisible(firstname, AppConstants.SMALL_DEFAULT_TIME_OUT).sendKeys(fn);
		eleutil.doSendKeys(lastname, ln);
		eleutil.doSendKeys(emailid, email);
		eleutil.doSendKeys(telephone, phno);
		eleutil.doSendKeys(password, pwd);
		eleutil.doSendKeys(confirmpwd, pwd);
		
		if (subscribe.equalsIgnoreCase("yes")) {
			eleutil.doClick(this.subscribeYes);
		} else {
			eleutil.doClick(this.subscribeNo);
		}

		eleutil.doClick(this.agreeCheckBox);
		eleutil.doClick(this.continueButton);

		String actSuccessMesg = eleutil.waitForElementVisible(this.sucessMessg, AppConstants.MEDIUM_DEFAULT_TIME_OUT)
				.getText();
		System.out.println("user reg success mesggg================= " + actSuccessMesg);
		if (actSuccessMesg.contains(AppConstants.REGISTER_SUCCESS_MESSAGE)) {
			
			return true;
		}
		return false;
	}
	
	public void goToRegisterPage() {
		eleutil.doClick(this.logoutLink);
		eleutil.doClick(this.registerLink);
	}
}
