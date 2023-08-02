package com.OrangeHRM.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.OrangeHRM.base.BaseClass;
import com.OrangeHRM.pages.HomePage;
import com.OrangeHRM.pages.LoginPage;
import com.Utility.Log;

public class LoginPageTest extends BaseClass {
	
	public static WebDriver driver;
	HomePage hp;
	LoginPage lp;
	
	@Test(priority = 1)
	public void verifyLogo() throws InterruptedException {
		
		Log.startTestCase("verifyLogo");
		Log.info("Verifying Logo");
		test=extent.createTest("verifyLogo");
		Thread.sleep(1000);
		lp=new LoginPage();
		
		boolean isDisplayed = this.lp.validateLogo();
		Log.info("Logo verified");
		Thread.sleep(1000);
		Assert.assertFalse(isDisplayed);
		Log.info("Logo assertion passed");
		Thread.sleep(1000);
		Log.endTestCase("verifyLogo");
	}
	
	//@Parameters({"username","password"}) 
	@Test(priority = 2,alwaysRun = true)
	public void verifyLogin() throws InterruptedException {
		Log.startTestCase("verifyLogin");
		test=extent.createTest("verifyLogin");
		Thread.sleep(1000);
		lp=new LoginPage();
		Thread.sleep(1000);
		Log.info("sending credentials");
		hp=lp.login(prop.getProperty("username"),prop.getProperty("password"));
		Log.info("Logged in successfully");
		Thread.sleep(1000);
		String expectedUrl="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		
		hp=new HomePage();
		String actUrl=hp.getPageUrl();
		Log.info("Page url captured successfully");
		Thread.sleep(1000);
		Assert.assertEquals(actUrl, expectedUrl);
		Log.info("Validating actual url");
		Thread.sleep(1000);
		Log.info("Clicking on menu button and Logout button");
		hp.click();
		Log.info("Logged out successfully");
		Log.endTestCase("verifyLogin");
	}
	 @Test(priority = 3)
	 public void sampleCase() {
		Log.startTestCase("sampleCase");
	  test=extent.createTest("sampleCase");
	  test.createNode("Validation1");
	  Assert.assertTrue(true);
	  test.createNode("Validation2");
	  Assert.assertTrue(true);
	  test.createNode("Validation3");
	  Assert.assertTrue(true);
	  test.createNode("Validation4");
	  Assert.assertTrue(true);
	  Log.endTestCase("sampleCase");
	 }
	
}
