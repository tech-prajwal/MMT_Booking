package testScenarioTwoThree;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBase;
import pages.CabsHome;
import pages.CabsSearch;
import pages.Home;
import utilities.ExtentReport;

public class CabsDataValidation extends PageBase {

	Home home;
	CabsHome cabhome;
	CabsSearch cabSearch;
	
	protected ExtentReports report=ExtentReport.getReportInstance();
	protected ExtentTest logger;
	
	@BeforeClass(groups= {"smoke","Regression"})
	public void invokeBrowser(){
		invokeBrowser("browserName");
		home=OpenApplication();
		home.closeAlerts();
		cabhome=home.clickCab();
	}

	@Test(priority=0,groups= {"smoke","Regression"})
	public void test5(){
		logger = report.createTest("Cabs data Validation");
		cabhome.radioCheck();
		logger.log(Status.INFO, "Radio button is verified");
	}
	
	@Test(priority=1,groups= {"smoke","Regression"})
	public void test6() throws InterruptedException{
		cabhome.fromCity();
		logger.log(Status.INFO, "From -Textbox verified");
	}
	
	@Test(priority=2,groups= "Regression")
	public void test7() throws Exception{
		cabhome.readDetailSheetValid();
		cabhome.selectfromCity();
		
	}
	
	@Test(priority=3,groups= {"smoke","Regression"})
	public void test8() throws InterruptedException{
		cabhome.toCity();
		logger.log(Status.INFO, "From -Textbox verified");
	}	

	@Test(priority=4,groups= "Regression")
	public void test9() throws Exception{
		cabhome.readDetailSheetValid();
		cabhome.selectToCity();
	}

	@Test(priority=5,groups= {"smoke","Regression"})
	public void test10() throws InterruptedException{
		cabhome.calendar();
		logger.log(Status.INFO, "Calendar verified");
	}

	@Test(priority=6,groups= "Regression")
	public void test11(){
		cabhome.dateselect();
	}
	
	@Test(priority=7,groups= {"smoke","Regression"})
	public void test12(){
		cabhome.returnText();
	
	}
	
	@Test(priority=8,groups= {"smoke","Regression"})
	public void test13() throws InterruptedException{
		cabhome.pickUpTime();
		logger.log(Status.INFO, "Pick-up time button verified");
	}
	
	//
	@Test(priority=9,groups= {"smoke","Regression"})
	public void test14(){
		cabhome.pickUpTimeSelect();
	}
	
	//
	@Test(priority=10,groups= {"smoke","Regression"})     
	public void test15(){
		cabSearch=cabhome.searchClick();
		logger.log(Status.PASS, "Navigated to results page when clicked on search button");
	}
	
	@Test(priority=11,groups= {"smoke","Regression"})
	public void test16(){
		cabSearch.sortBy();
		logger = report.createTest("Cabs filter Check");
		
	}
	
	@Test(priority=12,groups= "Regression")
	public void test17(){
		cabSearch.sortByFilter();
		logger.log(Status.INFO, "Sorted by Price - Filter Applied");
	}
	
	@Test(priority=13,groups= {"smoke","Regression"})
	public void test18(){
		cabSearch.cabType();
	}
	
	@Test(priority=14,groups= "Regression")
	public void test19(){
		cabSearch.cabTypeCheckbox();
		logger.log(Status.INFO, "Sorted Cab Type - Filter Applied (SUV)");
	}
	
	@Test(priority=15,groups= "Regression")
	public void test20() throws UnsupportedEncodingException{
		cabSearch.getlowsestPrice();
		logger.log(Status.PASS, "Lowest Price is printed in console");
	}
	

	@AfterClass(groups= {"smoke","Regression"})
	public void closeBrowser(){
		tearDown();
		report.flush();
	}
}
