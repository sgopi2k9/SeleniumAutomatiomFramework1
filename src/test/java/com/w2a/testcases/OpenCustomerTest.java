package com.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class OpenCustomerTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp",priority=3)
	public void openCustomerTest(String customerName,String currency){
		clickElement(OR.getProperty("openaccountbtn"), OR.getProperty("openaccountbtn_locatorType"));
		Select s1 = new Select(getElement(OR.getProperty("customerdropdown"),
				OR.getProperty("customerdropdown_locatorType")));
		s1.selectByVisibleText(customerName);
		Select s2 = new Select(getElement(OR.getProperty("currency"),
				OR.getProperty("currency_locatorType")));
		s2.selectByVisibleText(currency);
		clickElement(OR.getProperty("submit"), OR.getProperty("submit_locatorType"));
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		verifyIsTrue(alert.getText().contains("Account created successfully with account Number"), "Account creation");
		alert.accept();
		verifyAll();
		if(testResult.equals("Fail"))
			Assert.fail();
		
	}

}
