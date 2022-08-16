package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElemenrUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElemenrUtil eleUtil;

	// 1. by locators
	private By productHeader = By.cssSelector("div#content h1");
	private By productMetaData = By.xpath("(//div[@id=\"content\"]//ul[@class=\"list-unstyled\"])[1]/li");
	private By productPriceData = By.xpath("(//div[@id=\"content\"]//ul[@class=\"list-unstyled\"])[2]/li");

	private Map<String, String> productMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElemenrUtil(driver);

	}

	public String getProductHeadervalue() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println(productHeaderVal);
		return productHeaderVal;
	}

	public Map<String, String> getProductInfo() {

		productMap = new HashMap<String, String>();// does not maintain order
		// productMap=new LinkedHashMap<String,String>();//maintains order
		// productMap=new TreeMap<String,String>();//maintains sorting order
		// add product name in map
		productMap.put("productName", getProductHeadervalue());
		getProductMetaData();
		getProductpricingData();
		System.out.println("+++++++++++++++++++++++actual product info+++++++++++++++");
		productMap.forEach((k, v) -> System.out.println(k + ":" + v));
		return productMap;

	}

	private void getProductMetaData() {
		// product meta data
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		List<WebElement> metadataList = eleUtil.getElements(productMetaData);
		for (WebElement e : metadataList) {
			String text = e.getText();
			String metaArray[] = text.split(":");
			String key = metaArray[0].trim();
			String value = metaArray[1].trim();

			productMap.put(key, value);
		}
	}

	private void getProductpricingData() {

		// price data
		List<WebElement> metapriceList = eleUtil.getElements(productPriceData);
		String productPrice = metapriceList.get(0).getText().trim();
		String productExTaxPrice = metapriceList.get(0).getText().trim();

		productMap.put("productPrice", productPrice);
		productMap.put("productExTaxPrice", productExTaxPrice);
	}
}
