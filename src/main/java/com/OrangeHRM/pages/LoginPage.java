package com.OrangeHRM.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.base.BaseClass;

public class LoginPage extends BaseClass{

	@FindBy(xpath = "//img[@alt='company-branding']") WebElement comLogo;
	@FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input") WebElement password;
	@FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input") WebElement userName;
	@FindBy(xpath = "//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button") WebElement loginBtn;
	
	public LoginPage() throws InterruptedException {
		Thread.sleep(2000);
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateLogo() throws InterruptedException {
		Thread.sleep(2000);
		boolean isDisplay = comLogo.isDisplayed();
		Thread.sleep(2000);
		return isDisplay;
	}
	public HomePage login(String uName,String pass) throws InterruptedException {
		Thread.sleep(2000);
		userName.sendKeys(uName);
		Thread.sleep(2000);
		password.sendKeys(pass);
		Thread.sleep(2000);
		loginBtn.click();
		Thread.sleep(2000);
		return new HomePage();
	}

}
