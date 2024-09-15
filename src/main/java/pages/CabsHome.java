package pages;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import baseClasses.PageBase;
import utilities.ReadWriteExcel;
import utilities.Screenshot;

public class CabsHome extends PageBase {

	//constructor
	public CabsHome(WebDriver driver) {
		super();	
	}

	//Logger log = Logger.getLogger(CabsHome.class);
	private static final Logger logger = (Logger) LogManager.getLogger(CabsHome.class);

	ReadWriteExcel rwe=new ReadWriteExcel(driver);
	static String[] details=new String[10];

	//method to select excel sheet
	public void readDetailSheetValid() throws Exception {             //To read the data from GiftcardsSheet
		details=rwe.readExcelData("CabsSheet");
	}

	@FindBy(xpath="//li[@data-cy='outstationOneWay']")
	public WebElement oneWayRadio;

	@FindBy(xpath="//li[@class='selected']")
	public WebElement oneWayRadioSelected;

	@FindBy(xpath="//span[text()='From']")
	public WebElement fromCity;

	@FindBy(xpath="//div[@role='combobox']/input")
	public WebElement from_TextBox;

	@FindBy(xpath="//ul[@role='listbox']")
	public WebElement fromTextBoxListBox;

	@FindBy(xpath="//ul[@role='listbox']/li[1]")
	public WebElement fromTextBoxListFirstItem;

	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div[1]/div[2]")
	public WebElement toCity;

	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")
	public WebElement to_TextBox;

	@FindBy(xpath="//ul[@role='listbox']")
	public WebElement toTextBoxList;

	@FindBy(xpath="//ul[@role='listbox']/li[1]/div/p/span")
	public WebElement toTextBoxListFirstItem;

	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[3]")
	public WebElement depatureDateTab;

	@FindBy(xpath="//div[@lang='en']")
	public WebElement calendar;

	@FindBy(xpath="//div[@aria-label='Sun Apr 24 2022']")
	public WebElement depatureDateSelect;

	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div[4]/label/p")
	public WebElement returnTab;

	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div/div[2]/div/div[5]/label")
	public WebElement pickupTimeTab;

	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div[5]/ul")
	public WebElement timeselectDropdown;

	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div/div[2]/div[1]/div[5]/ul/li[73]")
	public WebElement pickupTimeSelectTime;

	@FindBy(xpath="/html/body/div[1]/div/div[2]/div/div/div[2]/p/a")
	public WebElement search;

	//clicking on radio button
	public void radioCheck() {
		oneWayRadio.click();
		if(oneWayRadioSelected.isEnabled()) {
			logger.info("Radio Button is Verified");
		}else {
			logger.error("Radio Button is not Verified");
		}	
	}

	//clicking on from city textBox 
	public void fromCity() throws InterruptedException {
		fromCity.click();
		logger.info("From City Clikcked");
		Thread.sleep(1000);
		if(from_TextBox.isDisplayed()) {
			logger.info("From Text Box is Active");
		}else {
			logger.error("From Text Box is not Active");
		}
	}

	//given input from excel to 'from city' textBox
	public void selectfromCity() throws InterruptedException {
		from_TextBox.sendKeys(details[0]);	
		Thread.sleep(1000);
		if(fromTextBoxListBox.isDisplayed()){
			fromTextBoxListFirstItem.click();
			logger.info("Selected city from suggessions");
		}else {
			logger.error("Unable to select city from suggessions");
		}
	}

	//clicking on to city textBox 
	public void toCity() throws InterruptedException {
		toCity.click();
		logger.info("To City Clikcked");
		Thread.sleep(1000);
		if(to_TextBox.isDisplayed()) {
			logger.info("To Text Box is Active");
		}else {
			logger.error("To Text Box is not Active");
		}
	}

	//given input from excel to 'to city' textBox
	public void selectToCity() throws InterruptedException {
		to_TextBox.sendKeys(details[1]);
		Thread.sleep(1000);
		if(to_TextBox.isDisplayed()) {
			toTextBoxListFirstItem.click();
			logger.info("Selected city from suggessions");
		}else {
			logger.error("Unable to select city from suggessions");
		}
	}

	//click on depature date 
	public void calendar() throws InterruptedException {
		depatureDateTab.click();
		Thread.sleep(1000);
		if(calendar.isDisplayed()) {
			logger.info("Calendar is Displayed");
		}else {
			logger.error("Unable to click on calendar");
		}
	}

	//selecting future date
	public void dateselect() {
		try {
			//dateTimePicker will select 10th day w.r.t to current
			dateTimePicker();
			logger.info("Date selected");
			Thread.sleep(5000);
		}catch(Exception e) {
			logger.error("Unable to select date");
		}
	}
	
	//verifing text from return tab
	public void returnText() {
		try {
			Assert.assertEquals(returnTab.getText(), "Tap to add a return date for bigger discounts");
			logger.info("Text under return menu is verified");
		}catch(Exception e){
			logger.error("Unable to get text");
		}
	}

	//clicking on select time 
	public void pickUpTime() throws InterruptedException {		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", pickupTimeTab);
		Thread.sleep(1000);
		if(timeselectDropdown.isDisplayed()) {
			logger.info("Time dropdown is displyed");
		}else {
			logger.error("Unable to click on Pick-Up time");
		}
	}

	//selecting time by scrolling upto specific element
	public void pickUpTimeSelect() {
		try {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView();", pickupTimeSelectTime);
			Thread.sleep(1000);
			String timeText=pickupTimeSelectTime.getText();
			js.executeScript("arguments[0].click()", pickupTimeSelectTime);
			logger.info("Time selected as:"+timeText);
			Thread.sleep(1000);
		}catch(Exception e){
			logger.error("Unable select time");
		}
	}

	//clicking on search button
	public CabsSearch searchClick(){
		search.click();
		logger.info("Search clicked");
		return PageFactory.initElements(driver, CabsSearch.class);
	}
}
