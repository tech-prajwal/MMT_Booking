package testScenarioFive;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBase;
import pages.GiftCards;
import pages.GiftCardsCorporate;
import utilities.ExtentReport;

public class FormCheckInvalid extends PageBase {
	GiftCards giftcards;
	GiftCardsCorporate giftcorporate;
	
	protected ExtentReports report=ExtentReport.getReportInstance();
	protected ExtentTest logger;
	
	@BeforeClass(groups= "Regression")
	public void invokeBrowser(){
		invokeBrowser("browserName");
		OpenApplication3();
		giftcorporate=OpenApplication4();
	}
	
	@Test(priority=6,groups= "Regression")
	public void test29() throws Exception{
		giftcorporate.readDetailsSheetInvalid();
		giftcorporate.test28();
		logger = report.createTest("Gift Card Form Invalid");
		logger.log(Status.PASS, "Error message Captured successfully");	
	}	

	@AfterClass(groups= "Regression")
	public void closeBrowser(){
		report.flush();
		tearDown();
		}	
	}

