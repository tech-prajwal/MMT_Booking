package pages;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import baseClasses.PageBase;
import utilities.Screenshot;

public class CabsSearch extends PageBase {

	//constructor
	public CabsSearch(WebDriver driver) {
		super();	
	}
	
	private static final Logger logger = (Logger) LogManager.getLogger(CabsSearch.class);
	
	@FindBy(xpath="//div[@class='cabSorter']")
	public WebElement sortedBy;
	
	@FindBy(xpath="//body/div/div/div/div/div[1]/div[1]/div[1]/div[1]/div[1]")
	public WebElement sortedByDropdown;
	
	@FindBy(xpath="//p[text()='Cab Type']")
	public WebElement cabType;
	
	@FindBy(xpath="//div[@class='sortOptionUnit'][1]")
	public WebElement sortPrice;

	@FindBy(xpath="//div[@class='filterPanel']//div[2]//div[3]//span/label")
	public WebElement SUVCheckbox;
	
	@FindBy(xpath="//div[@id='List']/div[1]//div[contains(@class,'cabBookDetails')]//p[contains(@class,'latoBlack')]")
	public WebElement priceText;
	
	//clicking on sort by filter
	public void sortBy(){
		if(sortedBy.isDisplayed()) {
		sortedBy.click();
		logger.info("Sort by filter is present");
		}else {
			logger.error("Sort by filter is not present");
		}
	}
	
	//aplying sort by filter
	public void sortByFilter(){	
		if(sortedByDropdown.isDisplayed()) {
		sortPrice.click();
		logger.info("Filter applied: 'Lowest to Highest Price'");
		}else {
			logger.error("Unable to apply filter");
		}
	}
	
	//checking if cabType filter is present
	public void cabType(){	
		if(cabType.isDisplayed()) {
			logger.info("Cab Type Filter is available");
		}else {
			logger.error("Cab Type Filter is unavailable");
		}
	}
	
	//selecting SUV checkbox
	public void cabTypeCheckbox(){
		SUVCheckbox.click();
		if(SUVCheckbox.isEnabled()) {
			logger.info("Filter applied: 'SUV'");
		}else {
			logger.error("SUV checkbox is not clicked");
		}
	}
	
	//printing lowest price in console
	public void getlowsestPrice() throws UnsupportedEncodingException{
		try {
		//to get the rupee symbol converting to UTF-8
		PrintStream out = new PrintStream(System.out, true, "UTF-8");
		out.println("Lowest Price is:" +priceText.getText());
		}catch(Exception e) {
			logger.error("Unable to get lowest price");
		}
	}
	
}
