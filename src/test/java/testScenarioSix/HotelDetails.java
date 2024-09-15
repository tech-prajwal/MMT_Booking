package testScenarioSix;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import baseClasses.PageBase;
import pages.Home;
import pages.HotelsHome;
import utilities.ExtentReport;

public class HotelDetails extends PageBase {
	Home home;
	HotelsHome hotelshome;
	
	protected ExtentReports report=ExtentReport.getReportInstance();
	protected ExtentTest logger;
	
	@BeforeClass(groups= {"smoke","Regression"})
	public void invokeBrowser(){
		invokeBrowser("browserName");
		home=OpenApplication();
		home.closeAlerts();
		hotelshome=home.clickHotels();
	}
	
	@Test(priority=0,groups= {"smoke","Regression"})
	public void test30(){
		hotelshome.clickRoom();
		logger = report.createTest("Hotels");
	}
	
	@Test(priority=1,groups= {"smoke","Regression"})
	public void test31(){
		hotelshome.checkRoomDropdown();
	}
	
	@Test(priority=2,groups= "Regression")
	public void test32(){
		hotelshome.getCount();
		logger.log(Status.PASS, "Adults Count Displayed in console");
	}
	
	@AfterClass(groups= {"smoke","Regression"})
	public void closeBrowser(){
		report.flush();
		tearDown();
	}
}
