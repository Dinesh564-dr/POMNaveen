package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElemenrUtil;

public class LoginPageTest extends BaseTest {

	@Test
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();

		Assert.assertEquals(actUrl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION), true);
	}

	@Test()
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExists());
	}

	@Test
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);

	}
	
}
