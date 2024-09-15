package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClasses.PageBase;
import utilities.ReadWriteExcel;
import utilities.Screenshot;

public class GiftCardsCorporate extends PageBase{
	
	//constructor
	public GiftCardsCorporate(WebDriver driver) {
		super();	
	}
	
	Screenshot screenst=new Screenshot(driver);
	private static final Logger logger = (Logger) LogManager.getLogger(GiftCardsCorporate.class);
	ReadWriteExcel rwe=new ReadWriteExcel(driver);
	static String[] details=new String[10];
	
	//method to select excel sheet or valid data
	public void readDetailsSheetValid() throws Exception {             //To read the data from GiftcardsSheet
		details=rwe.readExcelData("GiftcardsSheet");
	}
	
	//method to select excel sheet for invalid data
	public void readDetailsSheetInvalid() throws Exception {             //To read the data from GiftcardsSheet
		details=rwe.readExcelData("GiftcardsInvalid");
	}
	
	@FindBy(xpath="//li[normalize-space()='E-Mail']")
	WebElement emailSelection;
	
	@FindBy(name= "name")
	WebElement name;
	
	@FindBy(name = "senderName")
	WebElement senderName;

	@FindBy(name ="mobileNo")
	WebElement mobileNumber;

	@FindBy(name= "senderMobileNo")
	WebElement senderMobileNo;

	@FindBy(name= "emailId")
	WebElement emailId;

	@FindBy(name= "senderEmailId")
	WebElement senderEmailId;

	@FindBy(name= "giftMessage")
	WebElement giftMessage;

	@FindBy(xpath ="//button[@class='prime__btn font16 prime__btn__text']")
	WebElement buyNow;
	
	@FindBy(xpath ="//*[text()='Send Via']")
	WebElement scrollView;
	
	@FindBy(xpath ="//*[@id=\"deliveredSection\"]/p")
	WebElement scrollView1;
	
	@FindBy(xpath ="//*[@id=\"deliveredSection\"]/div/div/div/div[1]/div/div[3]/p")
	WebElement errorMsg;
	
	//selecting e-mail button
	public void emailselect() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		emailSelection.click();
		js.executeScript("arguments[0].scrollIntoView()", scrollView);
		if(emailSelection.isEnabled()) {
			logger.info("Clicked on E-mail button");
		}else {
			logger.error("Unable to click on E-mail button");
		}
	}
	
	//entering name from excel sheet
	public void nameEnter() throws InterruptedException {
		try {
		name.sendKeys(details[0]);
		logger.info("Entered detail in name textbox");
		Thread.sleep(3000);
		}catch(Exception e) {
			logger.error("Unable to enter detail in name textbox");
		}
	}
	
	//entering sender name from excel sheet
	public void senderName() throws InterruptedException {
		try {
		senderName.sendKeys(details[1]);
		logger.info("Entered detail in sender name textbox");
		Thread.sleep(1000);
		}catch(Exception e) {
			logger.error("Unable to enter detail in sender name textbox");
		}
	}
	
	//entering mobile No. from excel sheet
	public void mobileNo() throws InterruptedException {
		try {
		mobileNumber.sendKeys(details[2]);
		logger.info("Entered detail in mobile no textbox");
		Thread.sleep(1000);
		senderMobileNo.sendKeys(details[3]);
		logger.info("Entered detail in sender mobile no textbox");
		Thread.sleep(1000);
		}catch(Exception e) {
			logger.error("Unable to enter detail in mobile no textbox");
		}
	}
	
	//entering e-mail, message and verifying buy now button
	public Payments emailMessage() throws InterruptedException {
		try {
		emailId.sendKeys(details[4]);
		logger.info("Entered detail in email textbox");
		Thread.sleep(1000);
		senderEmailId.sendKeys(details[5]);
		logger.info("Entered detail in senders email textbox");
		Thread.sleep(1000);
		giftMessage.sendKeys(details[6]);
		logger.info("Entered detail in Gift message textbox");
		Thread.sleep(1000);
		buyNow.click();
		logger.info("Clicked on Buy Now");
		Thread.sleep(3000);
		}catch(Exception e) {
			logger.error("Unable to enter detail in email and gift message textbox");
		}
		return PageFactory.initElements(driver, Payments.class);
	}
	
	//entering invalid details and verifying buy now button
	public void test28() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		emailSelection.click();
		js.executeScript("arguments[0].scrollIntoView()", scrollView);
		name.sendKeys(details[0]);
		Thread.sleep(1000);
		senderName.sendKeys(details[1]);
		Thread.sleep(1000);
		mobileNumber.sendKeys(details[2]);
		Thread.sleep(1000);
		senderMobileNo.sendKeys(details[3]);
		Thread.sleep(1000);
		emailId.sendKeys(details[4]);
		Thread.sleep(1000);
		senderEmailId.sendKeys(details[5]);
		Thread.sleep(1000);
		giftMessage.sendKeys(details[6]);
		Thread.sleep(1000);
		buyNow.click();
		js.executeScript("arguments[0].scrollIntoView();", scrollView1);
	    Screenshot.takeScreenshot();
	    logger.info("Error message:"+errorMsg.getText());
	}
	
	
}


