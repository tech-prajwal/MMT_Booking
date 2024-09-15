package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseClasses.PageBase;
import utilities.Screenshot;

public class GiftCards extends PageBase {
	
	//constructor
	public GiftCards(WebDriver driver) {
		super();	
	}
	
	private static final Logger logger = (Logger) LogManager.getLogger(GiftCards.class);
	
	@FindBy(xpath="//p[text()='Category']")
	public WebElement category;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[2]/div/div[1]/div/div[1]/div/ul/li[4]/label")
	public WebElement corporateBox;
	
	@FindBy(xpath="/html/body/div/div/div[2]/div/div[1]/div/div[2]/div[2]/ul/li[2]/div/img")
	public WebElement corporateImgLink;
	
	//detecting presence of filter
	public void categoryRefine() {
		if(category.isDisplayed()) {
			logger.info("Category filter is available");
		}else {
			logger.error("Category filter is not available");
		}
	}
	
	//selecting 'Corporate' as category
	public void categoryCorporate() {
		corporateBox.click();
		if(corporateBox.isEnabled()) {
			logger.info("Filter applied: Corporate");
		}else {
			logger.error("Unable to apply corporate filter");
		}
	}
	
	//verifying image link
	public GiftCardsCorporate corporateClick() {
		try {
		corporateImgLink.click();
		getTitle("Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com");
		}
		catch(Exception e) {
			logger.error("Error occoured while clicking image link");
		}
		return PageFactory.initElements(driver, GiftCardsCorporate.class);
	}
	
}
