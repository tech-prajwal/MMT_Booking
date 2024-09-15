package pages;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import baseClasses.PageBase;
import utilities.Screenshot;

public class Home extends PageBase{

	//constructor
	public Home(WebDriver driver) {
		super();	
	}
	
	
	private static final Logger logger = (Logger) LogManager.getLogger(Home.class);
	
	@FindBy(xpath="//div[@data-cy='landingContainer']") 
	public WebElement adLogin;  
 
	@FindBy(xpath="//li[@data-cy='menu_Cabs']/a")
	public WebElement cabLink;

	@FindBy(xpath="//li[@data-cy='menu_Hotels']/a")
	public WebElement hotelLink;

	@FindBy(xpath="//p[@data-cy='tertiaryRowTitle_Gift Cards']")
	public WebElement giftCardLink;

	//closes all alerts at the welcome page
	public void closeAlerts() {
		adLogin.click();
		logger.info("Alerts closed successfully");
	}

	//clicking on cabs link
	public CabsHome clickCab() {
		cabLink.click();
		logger.info("Clicked on Cabs");
		//ext.test1.log(Status.PASS,"Cabs link verified");
		//ext.rep.flush();
		return PageFactory.initElements(driver, CabsHome.class);
	}

	//clicking on hotels link
	public HotelsHome clickHotels() {
		hotelLink.click();
		logger.info("Clicked on Hotels");
		return PageFactory.initElements(driver, HotelsHome.class);
	}
	
	//clicking on giftcards link
	public GiftCards clickGiftCard() {
		giftCardLink.click();
		logger.info("Clicked on GiftCards");
		//Switching to newly opened Tab
		ArrayList<String> wid = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(wid.get(1));
		getTitle("Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com");
		return PageFactory.initElements(driver, GiftCards.class);
	}
}

