package com.qa.opencart.test;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void isLogoutLinkExist() {
		Assert.assertEquals(accPage.isLogoulLinkExist(), true);
	}

	@Test
	public void accPageTitleExist() {
		Assert.assertEquals(accPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accpageHeadersTest() {
		List<String> actSecHeadersList = accPage.getAccountSectionsHeaderList();
		Collections.sort(actSecHeadersList);
		System.out.println("actual headers " + actSecHeadersList);
		Collections.sort(AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
		Assert.assertEquals(actSecHeadersList, AppConstants.EXPECTED_ACCOUNTS_HEADERS_LIST);
	}
}
