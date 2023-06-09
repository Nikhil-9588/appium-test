package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.StringWriter;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.qa.BaseTest;
import com.qa.utils.TestUtils;

public class TestListener implements ITestListener {
	TestUtils utils = new TestUtils();
	
	
	
	public void onTestFailure(ITestResult result )
	{
		if(result.getThrowable()!=null)
		{
			StringWriter sw= new  StringWriter();
		    PrintWriter pw  = new PrintWriter(sw);
		    result.getThrowable().printStackTrace(pw);
		    utils.log(sw.toString());
		}
	
	
	BaseTest base = new BaseTest();
	File file =((BaseTest) base).getDriver().getScreenshotAs(OutputType.FILE);
	HashMap<String , String>  param = new HashMap<String,String>() ;
	param = (HashMap<String, String>) result.getTestContext().getCurrentXmlTest().getAllParameters();
	
	
	String imagePath = "Screenshot" + File.separator + param.get("platformName") + "-" + param.get("platformVersion") + "-" + param.get("deviceName")
	+ File.separator + base.getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator +result.getName() +"png";
	
	String CompleteImagePath = System.getProperty("user.dir") + File.separator + imagePath;
	
	try {
		FileUtils.copyFile( file , new File(imagePath));
		Reporter.log("This is sample image");
		Reporter.log("<a href= '"+ CompleteImagePath+"'> <img src ='" + CompleteImagePath + "' height='100' width='100' />  </a>");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	}
	
}
