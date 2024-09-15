package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import baseClasses.PageBase;
import utilities.Screenshot;

public class HotelsHome extends PageBase{

	//comstructor
	public HotelsHome(WebDriver driver) {
		super();	
	}

	private static final Logger logger = (Logger) LogManager.getLogger(HotelsHome.class);
	@FindBy(xpath="//input[@id='guest']")
	public WebElement roomsGuest;

	@FindBy(xpath="//div[@class='roomsGuests']")
	public WebElement roomsGuestDrp;

	//clicking on rooms tab
	public void clickRoom() {
		roomsGuest.click();
		logger.info("Clicked on room");
	}

	//verifying the dropdown
	public void checkRoomDropdown() {
		if(roomsGuestDrp.isDisplayed()) {
			logger.info("Drop down verified");
		}else {
			logger.info("Drop down is not verified");
		}
	}

	//geting the count for adults
	public void getCount() {
		List<WebElement> adultCount = driver.findElements(By.xpath("//div[@class='roomsGuests']//ul[1]"));
		for (WebElement e : adultCount) {
			System.out.print(e.getText());
		}
	}
}
