package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElemenrUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElemenrUtil eleUtil;

	// 1.by locator
	By productCount = By.cssSelector("div.product-thumb");

	// 2.const

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElemenrUtil(driver);

	}

	// page actions

	public int getSeachProductCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, AppConstants.MEDIUM_DEFAULT_TIME_OUT).size();

	}
	public ProductInfoPage selectProduct(String SearchProductName) {
		By product=By.linkText(SearchProductName);
		eleUtil.doClick(product);
		return new ProductInfoPage(driver);
		
	}

}
