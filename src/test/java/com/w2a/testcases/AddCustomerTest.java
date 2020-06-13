package com.w2a.testcases;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase{

		@Test(dataProviderClass=TestUtil.class,dataProvider="dp",priority=2)
		public void addCustomerTest(String sno,String firstName,String lastName,String postalCode) throws InterruptedException{
			clickElement(OR.getProperty("addCustomer"), OR.getProperty("addCustomer_locatorType"));
			typeText(OR.getProperty("firstname"), OR.getProperty("firstname_locatorType"), firstName);
			typeText(OR.getProperty("lastname"), OR.getProperty("lastname_locatorType"), lastName);
			typeText(OR.getProperty("postalcode"), OR.getProperty("postalcode_locatorType"), postalCode);
			clickElement(OR.getProperty("addcustomerbtn"),OR.getProperty("addcustomerbtn_locatorType"));
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			verifyIsTrue(alert.getText().contains("Customer added successfully"),"Customer add");
			alert.accept();
			verifyAll();
			if(testResult.equals("Fail"))
				Assert.fail();
			
			
			
		}
}
