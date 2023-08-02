package com.OrangeHRM.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.OrangeHRM.base.BaseClass;

public class HomePage extends BaseClass{

	@FindBy (xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']") WebElement menu;
	@FindBy (xpath = "//a[normalize-space()='Logout']") WebElement logoutBtn;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void click() throws InterruptedException {
		Thread.sleep(2000);
		menu.click();
		Thread.sleep(2000);
		logoutBtn.click();
	}
	

	public String getPageUrl() {
		String url=driver.getCurrentUrl();
		return url;
	}
}
