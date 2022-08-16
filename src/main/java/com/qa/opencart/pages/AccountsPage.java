package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElemenrUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElemenrUtil eleutil;

	// 1. by locators
	private By logoutLink = By.xpath("//a[text()=\"Logout\" and @class=\"list-group-item\"]");
	private By searchField = By.name("search");
	private By accPageHeaders = By.cssSelector("div#content h2");
	private By searchIcon = By.cssSelector("div#search button");

	// 2.create constructor

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElemenrUtil(driver);
	}

	// 3.page actions
	public String getAccountPageTitle() {
		String title = eleutil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SMALL_DEFAULT_TIME_OUT);
		System.out.println("page title is " + title);
		return title;
	}

	public String getAccountPageUrl() {
		String url = eleutil.waitForUrl(AppConstants.SMALL_DEFAULT_TIME_OUT, AppConstants.ACCOUNTS_PAGE_URL_FRACTION);
		System.out.println("page url is " + url);
		return url;
	}

	public boolean isLogoulLinkExist() {
		return eleutil.waitForElementPresence(logoutLink, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}

	public boolean isSearchFieldExist() {
		return eleutil.waitForElementPresence(searchField, AppConstants.MEDIUM_DEFAULT_TIME_OUT).isDisplayed();
	}

	public List<String> getAccountSectionsHeaderList() {
		return eleutil.getAllElementsTextList(accPageHeaders, AppConstants.SMALL_DEFAULT_TIME_OUT);
	}

//common page actions
	public SearchResultsPage doSearch(String productName) {
		System.out.println("search for product " + productName);
		eleutil.doSendKeysWithWait(searchField, 10, productName);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);

	}
}
