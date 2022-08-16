package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductSearchTest extends BaseTest {

	@BeforeClass
	public void productSearchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook", "MacBook Pro" }, { "Macbook", "MacBook Air" },
				{ "Samsung", "Samsung SyncMaster 941BW" } };
	}

	@Test(dataProvider = "getProductData")
	public void productSearchTest(String Searchkey, String Productvalue) throws InterruptedException {
		searchResPage = accPage.doSearch(Searchkey);
		productInfoPage = searchResPage.selectProduct(Productvalue);
		String actProductHeaderName = productInfoPage.getProductHeadervalue();
		Assert.assertEquals(actProductHeaderName, Productvalue);
	}

	@DataProvider
	public Object[][] getProductInfo() {
		return new Object[][] { { "Apple", "$1,202.00" } };
	}

	@Test(dataProvider = "getProductInfo")
	public void productInfoTest(String searchproduct, String prodprice) {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Air");

		Map<String, String> actProductInfo = productInfoPage.getProductInfo();

		softassert.assertEquals(actProductInfo.get("Brand"), searchproduct);
		softassert.assertEquals(actProductInfo.get("productPrice"), prodprice);
		softassert.assertAll();
	}
}
