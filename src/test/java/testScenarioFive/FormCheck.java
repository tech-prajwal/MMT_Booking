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
import pages.Home;
import pages.Payments;
import utilities.ExtentReport;

public class FormCheck extends PageBase {

	Home home;
	GiftCards giftcards;
	GiftCardsCorporate giftcorporate;
	Payments pay;
	
	protected ExtentReports report=ExtentReport.getReportInstance();
	protected ExtentTest logger;
	
	@BeforeClass(groups= {"smoke","Regression"})
	public void invokeBrowser(){
		invokeBrowser("browserName");
		home=OpenApplication();
		home.closeAlerts();
		giftcards=home.clickGiftCard();
		giftcards.categoryCorporate();
		giftcorporate=giftcards.corporateClick();	
	}
	
	@Test(priority=1,groups= {"smoke","Regression"})
	public void test24(){
		giftcorporate.emailselect();
		logger = report.createTest("Gift Card Form");
		logger.log(Status.INFO, "E-mail button selected");
	}
	
	@Test(priority=2,groups= "Regression")
	public void test25() throws Exception{
		giftcorporate.readDetailsSheetValid();
		giftcorporate.nameEnter();
		logger.log(Status.INFO, "Recipents Name entered ");
	}
	
	@Test(priority=3,groups= "Regression")
	public void test26() throws Exception{
		giftcorporate.senderName();
		logger.log(Status.INFO, "Senders Name entered ");
	}
	
	@Test(priority=4,groups= "Regression")
	public void test27() throws Exception{
		giftcorporate.mobileNo();
		logger.log(Status.INFO, "Recipents Mobile No entered ");
		logger.log(Status.INFO, "Senders Mobile No entered ");
	}
	
	@Test(priority=5,groups= "Regression")
	public void test28() throws Exception{
		pay=giftcorporate.emailMessage();
		pay.getTitle("MakeMyTrip - #1 Travel Website for Flight Booking, Airline Tickets");
		logger.log(Status.INFO, "Valid E-mail No entered ");
		logger.log(Status.PASS, "Buy Now Button Verified");
	}
	
	@AfterClass(groups= {"smoke","Regression"})
	public void closeBrowser(){
			tearDown();
			report.flush();
		}
	
}
