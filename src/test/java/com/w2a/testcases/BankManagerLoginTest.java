package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test(priority=1)
	public void loginAsBankManager(){
		clickElement(OR.getProperty("bmlBtn"), OR.getProperty("bmlBtn_locatorType"));
		Assert.assertTrue(isElementPresent(OR.getProperty("addCustomer"), OR.getProperty("addCustomer_locatorType")));
	}

}
