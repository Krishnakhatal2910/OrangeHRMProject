package com.OrangeHRM.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.OrangeHRM.pages.LoginPage;
import com.Utility.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {

	public static WebDriver driver;
	 LoginPage lp;
	 public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent;
	 public static ExtentTest test;
	 
	public static Properties prop;
	 
	public void readConfig()  {
		
		try {
			prop=new Properties();
			 Thread.sleep(2000);
			 FileInputStream fis=new FileInputStream(System.getProperty("user.dir")
					 +"/Configuraration/config.properties");
			 Thread.sleep(2000);
				prop.load(fis);
				 Thread.sleep(2000);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		} catch (InterruptedException e) {	
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		}	
	 } 
	 public static String screenShot(WebDriver driver,String filename) {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new java.util.Date());
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("user.dir")+"\\ScreenShot\\"+filename+"_"+dateName+".png";
		  File finalDestination= new File(destination);
		  try {
		   FileUtils.copyFile(source, finalDestination);
		  } catch (Exception e) {
		   e.getMessage();
		  }
		  return destination; 
		 } 
	 @BeforeSuite
	 public void setExtent() {
		 DOMConfigurator.configure("log4j.xml");
		 Log.startTestCase("setExtent");
		  Log.info("This is beforeSuite Method");
	  htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReport/MyReport.html");
	  
	  htmlReporter.config().setDocumentTitle("Automation Test Report");
	  htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
	  htmlReporter.config().setTheme(Theme.DARK);	  
	  extent = new ExtentReports();
	  extent.attachReporter(htmlReporter);	  
	  extent.setSystemInfo("HostName", "MyHost");
	  extent.setSystemInfo("ProjectName", "OrangeHRM");
	  extent.setSystemInfo("Tester", "Kishor Markad");
	  extent.setSystemInfo("OS", "Win10");
	  extent.setSystemInfo("Browser", "Chrome");	  
	 }	 
	 @AfterSuite
	 public void endReport() {
	  extent.flush();
	 }
	@BeforeMethod 
	public void setup() throws InterruptedException, IOException {
		Thread.sleep(2000);
		readConfig();
		Thread.sleep(2000);
		System.setProperty("webdriver.chrome.driver", "F:\\ChromeDriver\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		Thread.sleep(2000);
		this.lp=new LoginPage();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.get(prop.getProperty("url"));
		Thread.sleep(2000);
	} 
	@AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
		  if(result.getStatus()==ITestResult.FAILURE) {
			 
		   test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		   test.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		   
		   String pathString=BaseClass.screenShot(driver, result.getName());
		   test.addScreenCaptureFromPath(pathString);
		   
		  } else if(result.getStatus()==ITestResult.SKIP) {
		   test.log(Status.SKIP, "Skipped Test case is: "+result.getName());
		  } else if(result.getStatus()==ITestResult.SUCCESS) {
		   test.log(Status.PASS, "Pass Test case is: "+result.getName());
		  }
		  driver.close();
	} 
}	
		 
		
		 


