package testScenarioFour;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBase;
import pages.GiftCards;
import pages.GiftCardsCorporate;
import pages.Home;
import utilities.ExtentReport;

public class GiftCardVerify extends PageBase {

	GiftCards giftcards;
	GiftCardsCorporate giftcorporate;
	Home home;
	
	protected ExtentReports report=ExtentReport.getReportInstance();
	protected ExtentTest logger;
	
	@BeforeClass(groups= {"smoke","Regression"})
	public void invokeBrowser(){
		invokeBrowser("browserName");
		home=OpenApplication();
		home.closeAlerts();
		giftcards=home.clickGiftCard();
	}
	
	@Test(priority=0,groups= {"smoke","Regression"})
	public void test21(){
		giftcards.categoryRefine();	
		logger = report.createTest("GiftCards ");
	}
	
	@Test(priority=1,groups= {"smoke","Regression"})
	public void test22(){
		giftcards.categoryCorporate();	
		logger.log(Status.INFO, "Category -Filter Applied");
	}
	
	@Test(priority=2,groups= {"smoke","Regression"})
	public void test23(){
		giftcorporate=giftcards.corporateClick();	
		logger.log(Status.PASS, "Category -Filter Applied");
	}
	
	@AfterClass(groups= {"smoke","Regression"})
	public void closeBrowser(){
		tearDown();
		report.flush();
	}
	
}
