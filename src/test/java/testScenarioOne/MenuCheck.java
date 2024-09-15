package testScenarioOne;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBase;
import pages.GiftCards;
import pages.Home;
import utilities.ExtentReport;


public class MenuCheck extends PageBase{

	Home home;
	GiftCards giftcards;
	
	protected ExtentReports report=ExtentReport.getReportInstance();
	protected ExtentTest logger;
	
	@BeforeClass(groups= {"smoke","Regression"})
	public void invokeBrowser(){
		invokeBrowser("browserName");
		home=OpenApplication();
		home.closeAlerts();
	}
	
	@Test(priority=0,groups= {"smoke","Regression"})
	public void test1(){
		logger = report.createTest("Menu Check");
		home.getTitle("MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday");
		logger.log(Status.PASS, "Web appliation opened successfully");
	}
	
	@Test(priority=1,groups= {"smoke","Regression"})
	public void test2(){
		home.clickHotels();	
		getTitle("MakeMyTrip.com: Save upto 60% on Hotel Booking 4,442,00+ Hotels Worldwide");
		logger.log(Status.PASS, "Hotels Menu Verified");
	}
	
	@Test(priority=2,groups= {"smoke","Regression"})
	public void test3(){
		home.clickCab();	
		getTitle("Online Cab Booking - Book Outstation Cabs at Lowest Fare @ MakeMyTrip");
		logger.log(Status.PASS, "Cabs Menu Verified");
	}
	
	@Test(priority=3,groups= {"smoke","Regression"})
	public void test4(){
		giftcards=home.clickGiftCard();
		giftcards.getTitle("Buy Gift Cards & Vouchers Online from MakeMyTrip");
		logger.log(Status.PASS, "Gift Cards Menu Verified");
	}
	
	@AfterClass(groups= {"smoke","Regression"})
	public void closeBrowser(){
		report.flush();
		tearDown();
	}

}
